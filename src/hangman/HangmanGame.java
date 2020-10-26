package hangman;

import linked_data_structures.SinglyLinkedList;

public class HangmanGame extends HangmanFrame {
	private SinglyLinkedList<String> previousNames;
	
	
	public HangmanGame() {
		previousNames = new SinglyLinkedList<String>();
	}
	
	public HangmanGame(DictionaryWord randomWord) {
		if (randomWord instanceof DictionaryWord && randomWord != null) { 
			
		}
	}
	
	public String selectWord() {
		return null;
	}
	
	public boolean newGame() {
		return false;
	}
	
	public boolean resumeGame() {
		return false;
	}
	
	/*
	 *  0 - words object
	 *	1 - current word object
	 *	2 - scoreboard
	 *	3 - 
	 */
	public boolean saveGame(SinglyLinkedList<Object> gameData) {
		
		
//		try {
//			gameData
//		}
		return false;
	}
	
	public void saveNewName(String newName) {
		previousNames.add(newName);
	}
	
	public boolean returnToMainMenu() {
		return false;
	}
	
	public boolean exitGame() {
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
