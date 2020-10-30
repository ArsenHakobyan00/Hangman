package hangman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import linked_data_structures.SinglyLinkedList;

public class HangmanGame {

	public HangmanGame() {
//		previousNames[0] = "";
//		previousNames.add()
	}

	public HangmanGame(DictionaryWord randomWord) {
		if (randomWord instanceof DictionaryWord && randomWord != null) {
//			newGame((DictionaryWord) randomWord))
			// TODO HangmanGame(word) constructor
		}
	}

	public String selectWord() {
		// TODO selectWord()
		return null;
	}

	public boolean canResume() {
		if (resumeGame() != null) {
			System.out.println(("ok"));
			return true;
		}
		System.out.println("no");
		return false;
	}

	/*
	 * 0 - current word object, 1 - words object, 2 - scoreboard
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
			// TODO Add scoreboard
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.err.println("There was a problem when trying to read-in your game file...");
			return null;
		} catch (ClassNotFoundException c) {
			System.err.println("There was a problem finding your game file...");
			return null;
		}
		return gameData;
	}

	/*
	 * 0 - current word object, 1 - words object, 2 - scoreboard
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

//	public void updatePreviousNames() {
//		String filePath = "./";
//		File file = new File(filePath);
//		FileWriter writer;
//		
//		try {
//			writer = new FileWriter(file);
//			
//			for (int i = 0; i < previousNames.length; i++) {
//				writer.write(previousNames[i]);
//			}
//			writer.flush();
//			writer.close();
//		}catch (IOException e) {
//			System.out.println("Exception: " + e);
//		}
//	}

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
