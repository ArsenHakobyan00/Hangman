package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import linked_data_structures.SinglyLinkedList;

public class DictonaryWordsRepo {
	private SinglyLinkedList<String> dictionaryWords = new SinglyLinkedList<String>();
	
	public DictonaryWordsRepo() {
		// TODO Auto-generated constructor stub
		readFile();
	}
	
	public SinglyLinkedList<String> getDictionaryWordsList() {
		return dictionaryWords;
	}
	
	public boolean addWordToList(String word) {
		if (word.length() > 2) {
			dictionaryWords.add(word);			
			return true;
		}
		return false;
	}
	
	public boolean removeWordFromList(String word) {
		for (int i = 0; i < dictionaryWords.getLength(); i++) {
			if (dictionaryWords.getElementAt(i).equals(word)) {
				dictionaryWords.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	public String sendRandomWord() {
		Random rand = new Random(); 
		int randomItemNumber = rand.nextInt(dictionaryWords.getLength());
		return dictionaryWords.getElementAt(randomItemNumber);
	}
	
	public boolean readFile() {
		try {
			File file = new File("dictionary.txt");
			if (file.length() > 0) {
				Scanner dictionaryReader = new Scanner(file);
				String currentWord;
				while (dictionaryReader.hasNext()) {
					currentWord = dictionaryReader.nextLine();
					addWordToList(currentWord);
				}
				dictionaryReader.close();
			} else {
				throw new EmptyFileException("The text file that contains all the words is empty... Please find the file at " + file.getAbsolutePath() + " and add some words in it");
			}
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			return false;
		} catch (EmptyFileException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.exit(1);
			return false;
		}
	}
	
}
