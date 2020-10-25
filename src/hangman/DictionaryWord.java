package hangman;

import linked_data_structures.SinglyLinkedList;

public class DictionaryWord {
	private String solution;
	private SinglyLinkedList<Character> guessedLetters;
	private SinglyLinkedList<Character> solutionInLetters;
	private SinglyLinkedList<Character> wrongGuesses;
	private int numberOfMistakes;
	
	public DictionaryWord() {
		solution = "unknown";
		guessedLetters = null;
		solutionInLetters = null;
		wrongGuesses = null;
		numberOfMistakes = 0;
	}
	
	public DictionaryWord(String randomWord) {
		solution = randomWord;
		guessedLetters = new SinglyLinkedList<Character>();
		solutionInLetters = new SinglyLinkedList<Character>();
		wrongGuesses = new SinglyLinkedList<Character>();
		
		toChars(solution);
		hideSolution(solution);
		
		numberOfMistakes = 0;
	}
	
	public SinglyLinkedList<Character> getGuessedLetters() {
		return guessedLetters;
	}
	
	public SinglyLinkedList<Character> getSolutionInLetters() {
		return solutionInLetters;
	}
	
	public SinglyLinkedList<Character> getWrongGuesses() {
		return wrongGuesses;
	}
	
	public void printLists() {
		System.out.println("Guessed Letters: ");
		for (int i = 0; i < guessedLetters.getLength(); i++) {
			System.out.print(guessedLetters.getElementAt(i) + " ");
		}
		System.out.println("\n");
		System.out.println("Solution In Letters");
		for (int i = 0; i < solutionInLetters.getLength(); i++) {
			System.out.print(solutionInLetters.getElementAt(i) + " ");
		}
//		System.out.println("\n");
//		System.out.println(solution);
	}
	
	public int getNumberOfMistakes() {
		return numberOfMistakes;
	}
	
	public boolean isGameOver() {
//		if (numberOfMistakes > )
		return false;
	}
	
	public String isWin() {
		return null;
	}
	
	public boolean guessLetter(char letter) {
		for (int i = 0; i < solutionInLetters.getLength(); i++) {
			
		}
		if ()
		
		
		return false;
	}
	
	
	public boolean hint() {
		return false;
	}
	
	// separate letters 
	private void toChars(String word) {
		for (int i = word.length()-1; i >= 0; i--) {
			solutionInLetters.add(word.charAt(i));
		}
	}
	
	// Leave special characters unhidden
	private void hideSolution(String word) {
		for (int i = word.length()-1; i >= 0; i--) {
			if (Character.isLetter(word.charAt(i))) {
				guessedLetters.add('_');				
			}
			else {
				guessedLetters.add(word.charAt(i));
			}
		}
	}
}
