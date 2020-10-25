package hangman;

import linked_data_structures.SinglyLinkedList;

public class HangmanGame extends HangmanFrame {
	private SinglyLinkedList<String> previousNames;
	
	
	public HangmanGame() {
		previousNames = new SinglyLinkedList<String>();
	}
	
	public HangmanGame(String randomWord) {
		
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
	
	public boolean saveGame() {
		return false;
	}
	
	public boolean saveNewName() {
		return false;
	}
	
	public boolean returnToMainMenu() {
		return false;
	}
	
	public boolean exitGame() {
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
