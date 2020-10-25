package hangman;

public class Player {
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
	
	public boolean checkName(String name) {
		return false;
	}
}
