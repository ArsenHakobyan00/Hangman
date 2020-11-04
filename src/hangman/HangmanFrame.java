package hangman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import linked_data_structures.DoublyLinkedList;
import linked_data_structures.SinglyLinkedList;

public class HangmanFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel welcomePanel;
	private JPanel newGamePanel;
	private JPanel newPlayerPanel;
	private JLayeredPane layeredPane;
	private JPanel scoreboardPanel;

	static JLabel lblNewLabel;
	static JButton btnNewGame;
	static JButton btnResumeGame;
	static JButton btnExitGame;
	static JLabel lblhangman;

	private static DictionaryWordsRepo words;
	private static DictionaryWord word;
	private static Scoreboard scoreboard;
	private static DoublyLinkedList<Player> players;
	private static SinglyLinkedList<Character> guessedLetters;
	private static SinglyLinkedList<Character> wrongGuesses;
	private static HangmanGame game;
	private static SinglyLinkedList<Object> gameData;
	private static SinglyLinkedList<Object> savedData;

	private int currentImg;
	private Image img;

	private JFormattedTextField txtFieldUserName;
	private JFormattedTextField lettersSpace;
	private JFormattedTextField enterLetterTxtField;
	private JComboBox comboBox;
	private JTextArea txtWrongGuesses;
	private JLabel lblOr;
	private JLabel lblUserInput;
	private JLabel lblWrongGuesses;
	private JLabel lblNewGame;
	private JLabel lblPreviousNames;
	private JButton btnHint;
	private JButton btnPlay;
	private JButton btnEnter;
	private JPanel lettersSpacePanel;
	private JPanel padPanel;
	private JPanel wrongGuessesPanel;
	private JLabel lblCurrentPlayer;
	private JTextArea txtCurrentPlayer;
	private JLabel lblScoreboard;
	private JTextArea scoreboardArea;
	private JButton btnCloseScoreboard;
	private JMenuBar menuBar;
	private JMenu gameMenu;
	private JMenuItem menuItemReturnToMainMenu;
	private JMenuItem menuItemNewGame;
	private JMenuItem menuItemSaveGame;
	private JMenuItem menuItemScoreboard;
	private JMenuItem menuItemExitGame;

	public static void main(String[] args) {
		// Start Game
		HangmanFrame frame = new HangmanFrame();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// Enter Letter
		if (e.getSource() == btnEnter) {
			String input = enterLetterTxtField.getText() + " ";
			char inputChar = input.charAt(0);
			enterLetterTxtField.setText("");

			if (Character.isLetter(inputChar)) {
				boolean guessedLettersDuplicate = isDuplicate(inputChar, guessedLetters);
				boolean wrongGuessedDuplicate = isDuplicate(inputChar, wrongGuesses);

				if (!guessedLettersDuplicate) {
					boolean isCorrect = word.guessLetter(inputChar);
					if (isCorrect) {
						displayGuessedLetters();
					} else {
						if (!wrongGuessedDuplicate) {
							changeHangmanImage();
						}
						displayWrongGuesses();
					}
				}
			} else {
				if (!Character.isSpaceChar(inputChar)) {
					String message = inputChar + " is not a letter";
					JOptionPane.showMessageDialog(newGamePanel, message, "Wrong input", JOptionPane.ERROR_MESSAGE);
				}
			}
			checkGameOver();

		} else if (e.getSource() == btnHint) {
			displayHint();
			checkGameOver();

			// New Game
		} else if (e.getSource() == btnNewGame) {
			layeredPane.removeAll();
			layeredPane.add(newPlayerPanel);

			layeredPane.repaint();
			layeredPane.revalidate();
			setTitle("New Game");

			// Exit Game
		} else if (e.getSource() == btnExitGame || e.getSource() == menuItemExitGame) {
			int answer = JOptionPane.showConfirmDialog(welcomePanel, "Would you like to save your game?");
			switch (answer) {
			case 0:
				saveGame();
				System.exit(1);
				break;
			case 1:
				System.exit(2);
				break;
			case 2:
				break;
			}
			
		} else if (e.getSource() == btnPlay) {
			String username = txtFieldUserName.getText();
			int previousUser = comboBox.getSelectedIndex();
			if (username.isEmpty() && previousUser == 0) {
				JOptionPane.showMessageDialog(newPlayerPanel,
						"You must enter a name, either by typing it or by choosing one from the previous name list (if it's not empty)",
						"Enter name", JOptionPane.ERROR_MESSAGE);
			} else if (!username.isEmpty() && previousUser == 0) {
				if (scoreboard.hasValidName(username)) {
					playNewGame();
					scoreboard.addPlayer(username);
					scoreboard.savePlayer();
					txtCurrentPlayer.setText(username);
					txtFieldUserName.setText("");
				} else {
					JOptionPane.showMessageDialog(newGamePanel, "Invalid Name", "Wrong input",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (username.isEmpty() && previousUser != 0) {
				playNewGame();
				txtCurrentPlayer.setText(scoreboard.getPreviousNames().getElementAt(previousUser));
				comboBox.setSelectedIndex(0);
			}
		} else if (e.getSource() == btnResumeGame) {
			resumeGame();
		} else if (e.getSource() == menuItemSaveGame) {
			saveGame();
		} else if (e.getSource() == menuItemReturnToMainMenu) {
			returnToMainMenu();
		} else if (e.getSource() == menuItemScoreboard) {
			displayScoreboard();
		} else if (e.getSource() == btnCloseScoreboard) {
			scoreboardPanel.setVisible(false);
		}

	}

	public HangmanFrame() {
		game = new HangmanGame();
		gameData = new SinglyLinkedList<Object>();
		words = new DictionaryWordsRepo();
		word = new DictionaryWord(words.sendRandomWord());
		guessedLetters = word.getGuessedLetters();
		wrongGuesses = word.getWrongGuesses();
		lblhangman = new JLabel("");
		scoreboard = new Scoreboard();

		init();
		canResume();
	}

	public void init() {
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 692);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1048, 670);
		contentPane.add(layeredPane);

		newGamePanel = new JPanel();
		newGamePanel.setBackground(Color.ORANGE);
		layeredPane.setLayer(newGamePanel, 0);
		newGamePanel.setBounds(0, 0, 1048, 670);
		layeredPane.add(newGamePanel);
		newGamePanel.setLayout(null);

		scoreboardPanel = new JPanel();
		scoreboardPanel.setVisible(false);
		scoreboardPanel.setForeground(Color.WHITE);
		scoreboardPanel.setBounds(133, 112, 781, 445);
		newGamePanel.add(scoreboardPanel);
		scoreboardPanel.setLayout(null);

		lblScoreboard = new JLabel("Scoreboard");
		lblScoreboard.setFont(new Font("Arial", Font.PLAIN, 25));
		lblScoreboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreboard.setBounds(313, 12, 152, 31);
		scoreboardPanel.add(lblScoreboard);

		scoreboardArea = new JTextArea();
		scoreboardArea.setWrapStyleWord(true);
		scoreboardArea.setEditable(false);
		scoreboardArea.setFont(new Font("Arial", Font.PLAIN, 20));
		scoreboardArea.setBounds(70, 64, 641, 339);
		scoreboardPanel.add(scoreboardArea);

		btnCloseScoreboard = new JButton("X");
		btnCloseScoreboard.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCloseScoreboard.setBackground(Color.RED);
		btnCloseScoreboard.setForeground(Color.WHITE);
		btnCloseScoreboard.setBounds(669, 10, 42, 41);
		scoreboardPanel.add(btnCloseScoreboard);

		padPanel = new JPanel();
		padPanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		padPanel.setBackground(Color.ORANGE);
		padPanel.setBounds(398, 463, 327, 181);
		newGamePanel.add(padPanel);
		padPanel.setLayout(null);

		enterLetterTxtField = new JFormattedTextField();
		enterLetterTxtField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (enterLetterTxtField.getText().length() >= 1) // limit character input to 1 character
					e.consume();
			}
		});
		enterLetterTxtField.setBounds(52, 26, 157, 58);
		padPanel.add(enterLetterTxtField);
		enterLetterTxtField.setHorizontalAlignment(SwingConstants.CENTER);
		enterLetterTxtField.setFont(new Font("Arial", Font.PLAIN, 20));

		btnHint = new JButton("Hint");
		btnHint.setForeground(Color.WHITE);
		btnHint.setBounds(232, 26, 83, 58);
		padPanel.add(btnHint);
		btnHint.setBackground(Color.RED);
		btnHint.setFont(new Font("Arial", Font.PLAIN, 20));

		btnEnter = new JButton("Enter Letter");
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setBackground(Color.BLACK);
		btnEnter.setBounds(52, 108, 157, 47);
		padPanel.add(btnEnter);
		btnEnter.setFont(new Font("Arial", Font.PLAIN, 20));

		btnEnter.addActionListener(this);

		wrongGuessesPanel = new JPanel();
		wrongGuessesPanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		wrongGuessesPanel.setBackground(Color.ORANGE);
		wrongGuessesPanel.setBounds(752, 463, 256, 181);
		newGamePanel.add(wrongGuessesPanel);
		wrongGuessesPanel.setLayout(null);

		txtWrongGuesses = new JTextArea();
		txtWrongGuesses.setWrapStyleWord(true);
		txtWrongGuesses.setBounds(12, 38, 232, 131);
		wrongGuessesPanel.add(txtWrongGuesses);
		txtWrongGuesses.setForeground(Color.BLACK);
		txtWrongGuesses.setLineWrap(true);
		txtWrongGuesses.setDropMode(DropMode.INSERT);
		txtWrongGuesses.setBackground(Color.ORANGE);
		txtWrongGuesses.setTabSize(0);
		txtWrongGuesses.setFont(new Font("Arial", Font.PLAIN, 20));
		txtWrongGuesses.setEditable(false);

		lblWrongGuesses = new JLabel("Wrong Guesses: ");
		lblWrongGuesses.setBounds(12, 5, 165, 37);
		wrongGuessesPanel.add(lblWrongGuesses);
		lblWrongGuesses.setFont(new Font("Arial", Font.BOLD, 20));

		lettersSpacePanel = new JPanel();
		lettersSpacePanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lettersSpacePanel.setBounds(451, 231, 510, 106);
		newGamePanel.add(lettersSpacePanel);
		lettersSpacePanel.setLayout(null);

		lettersSpace = new JFormattedTextField();
		lettersSpace.setEditable(false);
		lettersSpace.setBounds(29, 24, 451, 58);
		lettersSpace.setHorizontalAlignment(SwingConstants.CENTER);
		lettersSpace.setFont(new Font("Arial", Font.PLAIN, 30));
		displayGuessedLetters();
		lettersSpacePanel.add(lettersSpace);

		lblCurrentPlayer = new JLabel("Current Player:");
		lblCurrentPlayer.setFont(new Font("Arial", Font.BOLD, 20));
		lblCurrentPlayer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentPlayer.setBounds(451, 34, 197, 41);
		newGamePanel.add(lblCurrentPlayer);

		txtCurrentPlayer = new JTextArea();
		txtCurrentPlayer.setWrapStyleWord(true);
		txtCurrentPlayer.setTabSize(0);
		txtCurrentPlayer.setForeground(Color.BLACK);
		txtCurrentPlayer.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 25));
		txtCurrentPlayer.setEditable(false);
		txtCurrentPlayer.setDropMode(DropMode.INSERT);
		txtCurrentPlayer.setBackground(Color.ORANGE);
		txtCurrentPlayer.setBounds(666, 40, 295, 29);
		newGamePanel.add(txtCurrentPlayer);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1048, 21);
		newGamePanel.add(menuBar);

		welcomePanel = new JPanel();
		welcomePanel.setBackground(Color.ORANGE);
		layeredPane.setLayer(welcomePanel, 1);
		welcomePanel.setBounds(0, 0, 1048, 670);
		welcomePanel.setLayout(null);
		layeredPane.add(welcomePanel);

		lblNewLabel = new JLabel("Welcome To Hangman!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(357, 76, 375, 36);
		welcomePanel.add(lblNewLabel);

		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewGame.setBounds(436, 309, 212, 57);
		welcomePanel.add(btnNewGame);

		btnResumeGame = new JButton("Resume Game");
		btnResumeGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnResumeGame.setBounds(436, 240, 212, 57);
		btnResumeGame.setEnabled(false);
		welcomePanel.add(btnResumeGame);

		btnExitGame = new JButton("Exit Game");
		btnExitGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnExitGame.setBounds(436, 382, 212, 57);
		welcomePanel.add(btnExitGame);

		newPlayerPanel = new JPanel();
		newPlayerPanel.setBackground(Color.ORANGE);
		layeredPane.setLayer(newPlayerPanel, 0);
		newPlayerPanel.setBounds(0, 0, 1048, 670);
		newPlayerPanel.setLayout(null);
		layeredPane.add(newPlayerPanel);

		lblUserInput = new JLabel("Please enter your name:");
		lblUserInput.setForeground(Color.BLACK);
		lblUserInput.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUserInput.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserInput.setBounds(224, 268, 294, 31);
		newPlayerPanel.add(lblUserInput);

		lblNewGame = new JLabel("New Game");
		lblNewGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewGame.setFont(new Font("Arial", Font.PLAIN, 35));
		lblNewGame.setBounds(375, 136, 307, 51);
		newPlayerPanel.add(lblNewGame);

		txtFieldUserName = new JFormattedTextField();
		txtFieldUserName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldUserName.setBounds(536, 270, 256, 31);
		newPlayerPanel.add(txtFieldUserName);

		lblOr = new JLabel("OR");
		lblOr.setForeground(Color.BLACK);
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setFont(new Font("Arial", Font.BOLD, 20));
		lblOr.setBounds(381, 338, 294, 16);
		newPlayerPanel.add(lblOr);

		btnPlay = new JButton("PLAY");
		btnPlay.setBackground(new Color(255, 102, 0));
		btnPlay.setForeground(Color.BLACK);
		btnPlay.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPlay.setBounds(487, 486, 101, 38);
		newPlayerPanel.add(btnPlay);

		lblPreviousNames = new JLabel("Choose from previous names:");
		lblPreviousNames.setForeground(Color.BLACK);
		lblPreviousNames.setBounds(224, 402, 294, 31);
		newPlayerPanel.add(lblPreviousNames);
		lblPreviousNames.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreviousNames.setFont(new Font("Arial", Font.PLAIN, 20));

		comboBox = new JComboBox(getPreviousNames());
		comboBox.setBounds(536, 407, 256, 27);
		newPlayerPanel.add(comboBox);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
		comboBox.setMaximumRowCount(100);

		gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);

		menuItemNewGame = new JMenuItem("New Game");
		gameMenu.add(menuItemNewGame);

		menuItemSaveGame = new JMenuItem("Save Game");
		gameMenu.add(menuItemSaveGame);

		menuItemScoreboard = new JMenuItem("Scoreboard");
		gameMenu.add(menuItemScoreboard);

		menuItemReturnToMainMenu = new JMenuItem("Return to Main Menu");
		gameMenu.add(menuItemReturnToMainMenu);

		menuItemExitGame = new JMenuItem("Exit Game");
		gameMenu.add(menuItemExitGame);

		btnHint.addActionListener(this);
		btnNewGame.addActionListener(this);
		btnExitGame.addActionListener(this);
		btnPlay.addActionListener(this);
		txtFieldUserName.addActionListener(this);
		comboBox.addActionListener(this);
		btnResumeGame.addActionListener(this);
		menuItemExitGame.addActionListener(this);
		menuItemReturnToMainMenu.addActionListener(this);
		menuItemScoreboard.addActionListener(this);
		btnCloseScoreboard.addActionListener(this);
		menuItemSaveGame.addActionListener(this);
	}

	public void checkGameOver() {
		if (word.isGameOver()) {
			if (word.isWin()) {
				JOptionPane.showMessageDialog(newGamePanel, "You won!");
				scoreboard.addGamePlayed(txtCurrentPlayer.getText(), true);
			} else {
				JOptionPane.showMessageDialog(newGamePanel, "You lost");
				scoreboard.addGamePlayed(txtCurrentPlayer.getText(), false);
			}

			int choice = JOptionPane.showConfirmDialog(newGamePanel, "Would you like to play another game?",
					"Game Over", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				playNewGame();
			} else {
				returnToMainMenu();
			}
		}
	}

	public String[] getPreviousNames() {
		SinglyLinkedList<String> previousNamesList = scoreboard.getPreviousNames();
		String[] previousNames = new String[previousNamesList.getLength()];
		for (int i = 0; i < previousNamesList.getLength(); i++) {
			previousNames[i] = previousNamesList.getElementAt(i);
		}
		return previousNames;
	}

	public void returnToMainMenu() {
		txtCurrentPlayer.setText("");
		layeredPane.removeAll();
		layeredPane.add(welcomePanel);
		layeredPane.repaint();
		layeredPane.revalidate();
		setTitle("Welcome");
	}

	public void playNewGame() {
		game = new HangmanGame();
		gameData = new SinglyLinkedList<Object>();
		words = new DictionaryWordsRepo();
		word = new DictionaryWord(words.sendRandomWord());
		guessedLetters = word.getGuessedLetters();
		wrongGuesses = word.getWrongGuesses();
		btnHint.setEnabled(true);

		layeredPane.removeAll();
		layeredPane.repaint();
		layeredPane.revalidate();
		layeredPane.add(newGamePanel);
		menuBar.setVisible(true);

		setTitle("New Game");
		displayHangmanImage();
		displayGuessedLetters();
		displayWrongGuesses();
	}

	public void saveGame() {
		gameData.add((DictionaryWord) word);
		gameData.add((DictionaryWordsRepo) words);
		game.saveGame(gameData);
		scoreboard.savePlayer();
		JOptionPane.showMessageDialog(newGamePanel, "Your game has been saved", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
	}

	public void resumeGame() {
		savedData = game.resumeGame();
		word = (DictionaryWord) savedData.getElementAt(0);
		words = (DictionaryWordsRepo) savedData.getElementAt(1);
		players = scoreboard.readPlayers();

		guessedLetters = word.getGuessedLetters();
		wrongGuesses = word.getWrongGuesses();
		btnHint.setEnabled(true);

		layeredPane.removeAll();
		layeredPane.repaint();
		layeredPane.revalidate();
		layeredPane.add(newGamePanel);
		menuBar.setVisible(true);

		txtCurrentPlayer.setText(scoreboard.getPreviousNames().getElementAt(0));

		setTitle("New Game");
		displayHangmanImage();
		displayGuessedLetters();
		displayWrongGuesses();
	}

	public void canResume() {
		if (game.canResume()) {
			btnResumeGame.setEnabled(true);
		} else {
			btnResumeGame.setEnabled(false);
		}

	}

	public void displayHangmanImage() {
		currentImg = word.getNumberOfMistakes();
		img = new ImageIcon(this.getClass().getResource("/" + currentImg + ".png")).getImage();
		lblhangman.setIcon(new ImageIcon(img));
		lblhangman.setBounds(22, 72, 400, 400);
		newGamePanel.add(lblhangman);
	}

	public void changeHangmanImage() {
		currentImg = word.getNumberOfMistakes();
		img = new ImageIcon(this.getClass().getResource("/" + currentImg + ".png")).getImage();
		lblhangman.setIcon(new ImageIcon(img));
		lblhangman.setBounds(22, 72, 400, 400);
		newGamePanel.add(lblhangman);
	}

	public void displayGuessedLetters() {
		guessedLetters = word.getGuessedLetters();
		String letters = "";
		for (int i = 0; i < guessedLetters.getLength(); i++) {
			letters += guessedLetters.getElementAt(i) + "  ";
		}
		lettersSpace.setText(letters);
	}

	public void displayWrongGuesses() {
		wrongGuesses = word.getWrongGuesses();
		String wrongLetters = "";
		for (int i = 0; i < wrongGuesses.getLength(); i++) {
			wrongLetters += wrongGuesses.getElementAt(i) + ", ";
		}
		txtWrongGuesses.setText(wrongLetters);
	}

	private boolean isDuplicate(char inputChar, SinglyLinkedList<Character> list) {
		if (list.getLength() != 0) {
			int i = 0;
			while (i < list.getLength()) {
				if (inputChar == list.getElementAt(i)) {
					JOptionPane.showMessageDialog(newGamePanel, "Letter already guessed", "Duplicate",
							JOptionPane.WARNING_MESSAGE);
					return true;
				}
				i++;
			}
			return false;
		} else {
			return false;
		}
	}

	public void displayHint() {
		boolean hint = word.hint();
		if (hint) {
			displayGuessedLetters();
			changeHangmanImage();
			btnHint.setEnabled(false);
		}
	}

	public void displayScoreboard() {
		scoreboardArea
				.setText(String.format("%20s%15s%15s%15s%n", "Games Lost", "Games Won", "Games Played", "Player Name"));
		players = scoreboard.getPlayers();
		for (int i = 0; i < players.getLength(); i++) {
			Player currentPlayer = players.getElementAt(i);
			scoreboardArea.append(String.format("%20s%23s%23s%25s%n", currentPlayer.getGamesLost(),
					currentPlayer.getGamesWon(), currentPlayer.getGamesPlayed(), currentPlayer.getPlayerName()));
		}
		
		scoreboardPanel.setVisible(true);
	}
}