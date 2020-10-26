package hangman;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import linked_data_structures.SinglyLinkedList;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class HangmanFrame extends JFrame {

	private JPanel contentPane;
	private JPanel welcomePanel;
	private JPanel newGamePanel;
	private final JLayeredPane layeredPane = new JLayeredPane();
	
	private static DictonaryWordsRepo words;
	private static DictionaryWord word;
	private static HangmanGame game;
	
	private int currentImg;

	public void displayHangmanImage() {

	}

	public void changeHangmanImage() {
		
	}

	public void displayGuessedLetters() {

	}

	public void updateGuessedLetters() {

	}

	public void displayHint() {
		
	}

	public static void main(String[] args) {

//		HangmanFrame frame = new HangmanFrame();
//		frame.setVisible(true);
		SinglyLinkedList<Object> gameData = new SinglyLinkedList<Object>();
		words = new DictonaryWordsRepo();
		word = new DictionaryWord(words.sendRandomWord());
		word.printLists();
		game = new HangmanGame(word);
	}

	public HangmanFrame() {
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		layeredPane.setBounds(0, 0, 1048, 670);
		contentPane.add(layeredPane);

		welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, 1048, 670);
		layeredPane.add(welcomePanel);
		welcomePanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome To Hangman!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(357, 76, 375, 36);
		welcomePanel.add(lblNewLabel);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(newGamePanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				setTitle("New Game");
			}
		});
		btnNewGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewGame.setBounds(436, 245, 212, 57);
		welcomePanel.add(btnNewGame);

		JButton btnResumeGame = new JButton("Resume Game");
		btnResumeGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnResumeGame.setBounds(436, 314, 212, 57);
		welcomePanel.add(btnResumeGame);

		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnExitGame.setBounds(436, 382, 212, 57);
		welcomePanel.add(btnExitGame);

		newGamePanel = new JPanel();
		newGamePanel.setBounds(0, 0, 1048, 670);
		layeredPane.add(newGamePanel);
		newGamePanel.setLayout(null);

		JLabel lblhangman = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/0.png")).getImage();
		lblhangman.setIcon(new ImageIcon(img));
		lblhangman.setBounds(22, 72, 400, 400);
		newGamePanel.add(lblhangman);
	}
}
