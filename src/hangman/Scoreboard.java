package hangman;

import linked_data_structures.DoublyLinkedList;

public class Scoreboard {
	
	private DoublyLinkedList<Player> players;
	private int numPlayers;
	
	public Scoreboard() {
		players = new DoublyLinkedList<Player>();
		numPlayers = 0;		
	}
	
	public boolean addPlayer(String playerName) {
		return false;
	}
	
	public boolean addGamePlayed(String playerName, String winOrLoss) {
		return false;
	}
}
