package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class DictionaryWordTest {

	@Test
	void test1() {
		DictionaryWord word = new DictionaryWord("sword");
		word.guessLetter('s');
		word.guessLetter('w');
		word.guessLetter('o');
		word.guessLetter('r');
		word.guessLetter('d');

		// 5 mistakes
		word.guessLetter('x');
		word.guessLetter('y');
		word.guessLetter('z');
		word.guessLetter('a');
		word.guessLetter('b');

		assertTrue(word.isGameOver());

		DictionaryWord word1 = new DictionaryWord("sword");
		word1.guessLetter('w');
		word1.guessLetter('o');
		word1.guessLetter('r');
		word1.guessLetter('d');

		// 6 mistakes
		word1.guessLetter('x');
		word1.guessLetter('y');
		word1.guessLetter('z');
		word1.guessLetter('a');
		word1.guessLetter('b');
		word1.guessLetter('c');
		
		assertTrue(word1.isGameOver());

		DictionaryWord word2 = new DictionaryWord("sword");
		word2.guessLetter('w');
		word2.guessLetter('o');
		word2.guessLetter('r');
		word2.guessLetter('d');

		// 5 mistakes
		word2.guessLetter('x');
		word2.guessLetter('y');
		word2.guessLetter('z');
		word2.guessLetter('a');
		word2.guessLetter('b');
		
		assertFalse(word2.isGameOver());

		DictionaryWord word3 = new DictionaryWord("sword");
		word3.guessLetter('s');
		word3.guessLetter('w');
		word3.guessLetter('o');
		word3.guessLetter('r');
		word3.guessLetter('d');

		// 6 mistakes
		word3.guessLetter('x');
		word3.guessLetter('y');
		word3.guessLetter('z');
		word3.guessLetter('a');
		word3.guessLetter('b');
		word3.guessLetter('v');
		
		assertTrue(word3.isGameOver());
		
	}
	
	@Test
	void test2() {
		DictionaryWord word = new DictionaryWord("sword");
		word.guessLetter('s');
		word.guessLetter('w');
		word.guessLetter('o');
		word.guessLetter('r');
		word.guessLetter('d');

		// 5 mistakes
		word.guessLetter('x');
		word.guessLetter('y');
		word.guessLetter('z');
		word.guessLetter('a');
		word.guessLetter('b');

		assertTrue(word.isWin());

		DictionaryWord word1 = new DictionaryWord("sword");
		word1.guessLetter('w');
		word1.guessLetter('o');
		word1.guessLetter('r');
		word1.guessLetter('d');

		// 6 mistakes
		word1.guessLetter('x');
		word1.guessLetter('y');
		word1.guessLetter('z');
		word1.guessLetter('a');
		word1.guessLetter('b');
		word1.guessLetter('c');
		
		assertFalse(word1.isWin());

		DictionaryWord word2 = new DictionaryWord("sword");
		word2.guessLetter('s');
		word2.guessLetter('w');
		word2.guessLetter('o');
		word2.guessLetter('r');
		word2.guessLetter('d');

		// 6 mistakes
		word2.guessLetter('x');
		word2.guessLetter('y');
		word2.guessLetter('z');
		word2.guessLetter('a');
		word2.guessLetter('b');
		word2.guessLetter('v');
		
		assertFalse(word2.isWin());
		
	}

}
