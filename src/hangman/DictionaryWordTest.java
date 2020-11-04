package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hangman.DictionaryWord;
import linked_data_structures.SinglyLinkedList;

class DictionaryWordTest {

	@Test
	void test1() {// isGameOver
		DictionaryWord word = new DictionaryWord("sword");
		word.guessLetter('s');
		word.guessLetter('w');
		word.guessLetter('o');
		word.guessLetter('r');
		word.guessLetter('d');
		
		System.out.println(word.getSolutionInLetters());
		System.out.println(word.getNumberOfMistakes());
		System.out.println(word.getGuessedLetters());
		
		assertTrue(word.isGameOver());
	}

}
