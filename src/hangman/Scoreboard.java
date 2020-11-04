package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import linked_data_structures.DoublyLinkedList;
import linked_data_structures.SinglyLinkedList;

public class Scoreboard implements Serializable {

	private DoublyLinkedList<Player> scoreboard;
	private SinglyLinkedList<String> previousNames;
	private int numPlayers;

	public Scoreboard() {
		scoreboard = readPlayers();
		setPreviousNames();
		previousNames.add(" ");
		numPlayers = scoreboard.getLength();
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public void setPreviousNames() {
		previousNames = new SinglyLinkedList<String>();
		Player player;
		String name;
		for(int i = 0; i < scoreboard.getLength(); i++) {
			player = scoreboard.getElementAt(i);
			name = player.getPlayerName();
			previousNames.add(name);
		}
	}
	public SinglyLinkedList<String> getPreviousNames() {
		return previousNames;
	}
	public DoublyLinkedList<Player> getPlayers() {
		return scoreboard;
	}

	public DoublyLinkedList<Player> readPlayers() {
		DoublyLinkedList<Player> savedPlayers = new DoublyLinkedList<Player>();
		try {
			File file = new File("players.txt");
			if (file.length() > 0) {
				Scanner dictionaryReader = new Scanner(file);
				dictionaryReader.useDelimiter("~|\r|\n");
				String currentPlayer;
				int gamesPlayed;
				int gamesWon;
				int gamesLost;
				
				Player player=null;
				while (dictionaryReader.hasNext()) {
					currentPlayer = dictionaryReader.next();
					gamesPlayed = dictionaryReader.nextInt();
					gamesWon = dictionaryReader.nextInt();
					gamesLost = dictionaryReader.nextInt();
					
					player = new Player(currentPlayer);
					player.setGamesPlayed(gamesPlayed);
					player.setGamesWon(gamesWon);
					player.setGamesLost(gamesLost);
					
					savedPlayers.add(player);
				}
				dictionaryReader.close();
			} else {
				throw new EmptyFileException(
						"The text file is empty...");
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (EmptyFileException e) {
			System.out.println(e);
		}
		return savedPlayers;
	}

	public boolean savePlayer() {
		File file = new File("players.txt");
		FileWriter fw;

		try {
			fw = new FileWriter(file);
			for (int i = 0; i < scoreboard.getLength(); i++) {
				Player currentPlayer = scoreboard.getElementAt(i);
				
				fw.write(currentPlayer.getPlayerName() + "~" + currentPlayer.getGamesPlayed() + "~"
						+ currentPlayer.getGamesWon() + "~" + currentPlayer.getGamesLost() + "\n");
			}
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			System.out.println("Exception: " + e);
			return false;
		}
	}

	public boolean addPlayer(String playerName) {
		if (hasValidName(playerName)) {
			Player player = new Player(playerName);
			scoreboard.add(player);
			previousNames.add(playerName);
			numPlayers++;
			return true;
		}
		return false;
	}

	public boolean addGamePlayed(String playerName, boolean win) {
		Player player = null;
		Player current;
		int playerIndex = 0;
		for (int i = 0; i < scoreboard.getLength(); i++) {
			current = scoreboard.getElementAt(i);
			if (playerName.equals(current.getPlayerName())) {
				player = current;
				playerIndex = i;
			}
		}
		if (hasValidName(playerName)) {
			if (player == null) {
				player = new Player(playerName);
				if (win == true) {
					player.win();
					numPlayers++;
				} else {
					player.loss();
					numPlayers++;
				}
				scoreboard.add(player);
			} else {
				if (win == true) {
					player.win();
					numPlayers++;
				} else {
					player.loss();
					numPlayers++;
				}
				scoreboard.remove(playerIndex);
				scoreboard.add(player, playerIndex);
			}
			
			savePlayer();
			return true;
		}
		return false;

	}

	public boolean hasValidName(String name) {
		int i = 0;
		while (i < name.length()) {
			if (!Character.isLetter(name.charAt(i)))
				return false;
			i++;
		}
		return true;
	}
}
