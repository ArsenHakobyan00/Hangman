package hangman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import linked_data_structures.SinglyLinkedList;

public class HangmanGame extends HangmanFrame {
	private SinglyLinkedList<String> previousNames;

	public HangmanGame() {
		previousNames = new SinglyLinkedList<String>();
	}

	public HangmanGame(DictionaryWord randomWord) {
		if (randomWord instanceof DictionaryWord && randomWord != null) {
//			newGame((DictionaryWord) randomWord))
			// TODO Figure out what to do with this constructor
		}
	}

	public String selectWord() {
		return null;
	}

	public boolean newGame() {
		return false;
	}

	/*
	 * 0 - current word object 1 -  words object 2 - scoreboard
	 */
	@SuppressWarnings("unchecked")
	public SinglyLinkedList<Object> resumeGame() {
		SinglyLinkedList<Object> gameData = null;
		try {
			FileInputStream fileIn = new FileInputStream("./gameData.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			gameData = (SinglyLinkedList<Object>) in.readObject();

			Object word = gameData.getElementAt(0);
			gameData.add(word);

			Object words = gameData.getElementAt(1);
			gameData.add(words);
			// TODO Add scoreboard;
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.err.println("There was a problem when trying to read-in your game file...");
		} catch (ClassNotFoundException c) {
			System.err.println("There was a problem finding your game file...");
		}
		return gameData;
	}

	/*
	 * 0 - current word object 1 -  words object 2 - scoreboard
	 */
	public boolean saveGame(SinglyLinkedList<Object> gameData) {
		try {
			FileOutputStream fileOut = new FileOutputStream("./gameData.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(gameData);
			out.close();
			fileOut.close();
			System.out.println("\n\nSerialized data is saved in gameData.ser");
			return true;
		} catch (IOException i) {
	         System.err.println("There was a problem saving your game...");
	         return false;
	    }
	}

//	public ArrayList<Object> resumeGame() {
//		ArrayList<Object> gameData = null;
//		try {
//			FileInputStream fileIn = new FileInputStream("./gameData.ser");
//			ObjectInputStream in = new ObjectInputStream(fileIn);
//			gameData = (ArrayList<Object>) in.readObject();
//
//			DictionaryWordsRepo words = (DictionaryWordsRepo) gameData.get(0);
//			gameData.add(words);
//
//			DictionaryWord word = (DictionaryWord) gameData.get(1);
//			gameData.add(word);
//			// TODO Add scoreboard;
//			in.close();
//			fileIn.close();
//		} catch (IOException i) {
//			System.err.println("There was a problem when trying to read-in your game file...");
//		} catch (ClassNotFoundException c) {
//			System.err.println("There was a problem finding your game file...");
//		}
//		return gameData;
//	}

//	public boolean saveGame(ArrayList<Object> gameData) {
//		try {
//			FileOutputStream fileOut = new FileOutputStream("./gameData.ser");
//			ObjectOutputStream out = new ObjectOutputStream(fileOut);
//			out.writeObject(gameData);
//			out.close();
//			fileOut.close();
//			System.out.println("\n\nSerialized data is saved in gameData.ser");
//			return true;
//		} catch (IOException i) {
//			System.err.println("There was a problem saving your game...");
//			return false;
//		}
//	}

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
