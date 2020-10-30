package hangman;

import java.io.Serializable;

import linked_data_structures.DoublyLinkedList;

public class Scoreboard implements Serializable {

	private DoublyLinkedList<Player> scoreboard;
	private String[] previousNames = { "" };
	private int numPlayers;

	public Scoreboard() {
		scoreboard = new DoublyLinkedList<Player>();

		numPlayers = 0;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public String[] getPreviousNames() {
		return previousNames;
	}

	public DoublyLinkedList<Player> getPlayers() {
		return scoreboard;
	}

	public boolean addPlayer(String playerName) {
		if (hasValidName(playerName)) {
			Player player = new Player(playerName);
			scoreboard.add(player);
			previousNames[previousNames.length] = playerName;
			numPlayers++;
			return true;
		}
		return false;
	}

	public boolean addGamePlayed(String playerName, boolean win) {
//		Player player = new Player(playerName);
//		if (player.hasValidName()) { 
//			if (winOrLoss == true) {
//				player.win();
//				scoreboard.add(player);
//			}
//			else {
//				player.loss();
//				scoreboard.add(player);
//			}
//			return true;
//		}
//		return false;

		if (hasValidName(playerName)) {
			Player player = new Player(playerName);
			if (win == true) {
				player.win();
				scoreboard.add(player);
				numPlayers++;
			} else {
				player.loss();
				scoreboard.add(player);
				numPlayers++;
			}
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
