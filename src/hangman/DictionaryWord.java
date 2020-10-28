package hangman;

import java.io.Serializable;
import java.util.Random;
import linked_data_structures.SinglyLinkedList;

public class DictionaryWord implements Serializable {
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
	
	// Temporary
	public void printLists() {
		System.out.println("Guessed Letters: ");
		for (int i = 0; i < solutionInLetters.getLength(); i++) {
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
		if (hasEmptySlots() == false && numberOfMistakes < 6) 		return true;
		else if (hasEmptySlots() == true && numberOfMistakes == 6)  return true;
		else if (hasEmptySlots() == false && numberOfMistakes == 6) return true;
		else return false;
	}
	
	public boolean isWin() {
		if (isGameOver() == true) {
			if (hasEmptySlots() == false && numberOfMistakes < 6) 	    return true;
			else if (hasEmptySlots() == true && numberOfMistakes == 6)  return false;
			else if (hasEmptySlots() == false && numberOfMistakes == 6) return false;
			else return false;
		}
		return false;
	}
	
	public boolean guessLetter(char letter) {
		if (Character.isLetter(letter)) {
			int i = 0;
			int done = 0;
			char current;
			while(i < solutionInLetters.getLength()) {
				current = solutionInLetters.getElementAt(i);
				if (letter == current) {
					guessedLetters.add(current, i);
					done++;
				}
				else {
					wrongGuesses.add(current);
				}
				i++;
			}
			if (done == 0) return false;
			else return true;
		}
		return false;
	}
	
	public boolean hasEmptySlots() {
		int i = 0;
		while(i < solutionInLetters.getLength()) {
			if (guessedLetters.getElementAt(i) == '_') {
				return true;
			}
			i++;
		}
		return false;
	}
	
	public boolean hint() {
			if (hasEmptySlots() == true) {
				// Create a list with all the indices with '_'	
				SinglyLinkedList<Integer> emptySlotIndices = new SinglyLinkedList<Integer>();
				int i = 0;
				while(i < solutionInLetters.getLength()) {
					if (guessedLetters.getElementAt(i) == '_') {
						emptySlotIndices.add(i);
					}
					i++;
				}
				// Random Index of an empty slot
				Random rand = new Random(); 
				int randomLetterIndex = rand.nextInt(emptySlotIndices.getLength());
				char randomLetter = solutionInLetters.getElementAt(randomLetterIndex);
				
				// Reveal the letter
				guessLetter(randomLetter);
				return true;
			}
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
