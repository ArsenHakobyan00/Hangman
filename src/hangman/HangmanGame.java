package hangman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import linked_data_structures.SinglyLinkedList;

public class HangmanGame {

	public boolean canResume() {
		if (resumeGame() != null) {
			System.out.println(("ok"));
			return true;
		}
		System.out.println("no");
		return false;
	}

	@SuppressWarnings("unchecked")
	public SinglyLinkedList<Object> resumeGame() {
		SinglyLinkedList<Object> gameData = new SinglyLinkedList<Object>();
		try {
			FileInputStream fileIn = new FileInputStream("./gameData.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			Object scoreboard = in.readObject();;
			gameData.add(scoreboard);
			
			Object words = in.readObject();;
			gameData.add(words);
			
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

	public boolean saveGame(SinglyLinkedList<Object> gameData) {
		try {
			FileOutputStream fileOut = new FileOutputStream("./gameData.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(gameData.getElementAt(0));
			out.writeObject(gameData.getElementAt(1));
			out.close();
			fileOut.close();
			System.out.println("\n\nSerialized data is saved in gameData.ser");
			return true;
		} catch (IOException i) {
			System.err.println("There was a problem saving your game...");
			i.printStackTrace();
			return false;
		}
	}
}
