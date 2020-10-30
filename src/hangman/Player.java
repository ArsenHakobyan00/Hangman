package hangman;

import java.io.Serializable;

public class Player implements Serializable {
	private String playerName;
	private int gamesWon;
	private int gamesLost;
	private int gamesPlayed;

	public Player() {
		setPlayerName("Unknown");
		setGamesWon(0);
		setGamesLost(0);
		setGamesPlayed(0);
	}

	public Player(String playerName) {
		setPlayerName(playerName);
		setGamesWon(0);
		setGamesLost(0);
		setGamesPlayed(0);
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getGamesLost() {
		return gamesLost;
	}

	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public void win() {
		this.gamesPlayed++;
		this.gamesWon++;
	}

	public void loss() {
		this.gamesPlayed++;
		this.gamesWon++;
	}

//	public boolean hasValidName() {
//		int i = 0;
//		while(i < playerName.length()) {
//			if (!Character.isLetter(playerName.charAt(i))) return false;
//			i++;
//			
//		}
//		return true;
//	}
}
