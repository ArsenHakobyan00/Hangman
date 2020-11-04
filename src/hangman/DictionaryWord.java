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
	private int hintsAvailable;

	public DictionaryWord() {
		solution = "unk	wn";
		guessedLetters = null;
		solutionInLetters = null;
		wrongGuesses = null;
		numberOfMistakes = 0;
		hintsAvailable = 1;
	}

	public DictionaryWord(String randomWord) {
		solution = randomWord;
		guessedLetters = new SinglyLinkedList<Character>();
		solutionInLetters = new SinglyLinkedList<Character>();
		wrongGuesses = new SinglyLinkedList<Character>();
		toChars(solution);
		hideSolution(solution);
		numberOfMistakes = 0;
		hintsAvailable = 1;
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

	public int getHintsAvailable() {
		return hintsAvailable;
	}
	
	public int getNumberOfMistakes() {
		return numberOfMistakes;
	}

	public boolean isGameOver() {
		if (hasEmptySlots() == false && numberOfMistakes < 6)
			return true;
		else if (hasEmptySlots() == true && numberOfMistakes == 6)
			return true;
		else if (hasEmptySlots() == false && numberOfMistakes == 6)
			return true;
		else
			return false;
	}

	public boolean isWin() {
		if (isGameOver() == true) {
			if (hasEmptySlots() == false && numberOfMistakes < 6)
				return true;
			else if (hasEmptySlots() == true && numberOfMistakes == 6)
				return false;
			else if (hasEmptySlots() == false && numberOfMistakes == 6)
				return false;
			else
				return false;
		}
		return false;
	}

	public boolean guessLetter(char letter) {
		letter = Character.toLowerCase(letter);
		if (Character.isLetter(letter)) {
			int i = 0;
			char current;
			if (solution.indexOf(letter) != -1) {
				while (i < solutionInLetters.getLength()) {
					current = solutionInLetters.getElementAt(i);
					if (letter == current) {
						guessedLetters.remove(i);
						guessedLetters.add(current, i);
					}
					i++;
				}
			} else {
				addWrongGuess(letter);
				numberOfMistakes++;
				return false;
			}
			return true;
		}
		return false;
	}

	public void addWrongGuess(char letter) {
		if (wrongGuesses.getLength() != 0) {
			int i = 0;
			int duplicates = 0;
			while (i < wrongGuesses.getLength()) {
				if (letter == wrongGuesses.getElementAt(i)) {
					duplicates++;
				}
				i++;
			}
			if (duplicates == 0) { 
				wrongGuesses.add(letter);
			}
		} else {
			wrongGuesses.add(letter);
		}
	}

	public boolean hasEmptySlots() {
		int i = 0;
		while (i < solutionInLetters.getLength()) {
			if (guessedLetters.getElementAt(i) == '_') {
				return true;
			}
			i++;
		}
		return false;
	}

	public boolean hint() {
		if (hasEmptySlots() == true && hintsAvailable != 0) {
			// Create a list with all the indices with '_'
			SinglyLinkedList<Integer> emptySlotIndices = new SinglyLinkedList<Integer>();
			int i = 0;
			while (i < solutionInLetters.getLength()) {
				if (guessedLetters.getElementAt(i) == '_') {
					emptySlotIndices.add(i);
				}
				i++;
			}
			
			// Random Index of an empty slot
			Random rand = new Random();
			int randomLetterIndex = rand.nextInt(emptySlotIndices.getLength());
			int emptySlotIndex = emptySlotIndices.getElementAt(randomLetterIndex);
			char randomLetter = solutionInLetters.getElementAt(emptySlotIndex);
			
			// Reveal the letter
			guessLetter(randomLetter);
			
			// Decrease hints available 
			hintsAvailable--;
			numberOfMistakes++;
			
			return true;
		}
		return false;
	}

	// separate letters
	private void toChars(String word) {
		for (int i = word.length() - 1; i >= 0; i--) {
			solutionInLetters.add(word.charAt(i));
		}
	}

	// Leave special characters unhidden
	private void hideSolution(String word) {
		for (int i = word.length() - 1; i >= 0; i--) {
			if (Character.isLetter(word.charAt(i))) {
				guessedLetters.add('_');
			} else {
				guessedLetters.add(word.charAt(i));
			}
		}
	}
}
