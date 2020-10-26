package hangman;
//TODO Create A separate package for exceptions
public class EmptyFileException extends Exception {
	public EmptyFileException(String msg) {
		super(msg);
	}
}
