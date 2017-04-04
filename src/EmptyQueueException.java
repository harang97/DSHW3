package hw3;

@SuppressWarnings("serial")
public class EmptyQueueException extends Exception { // Class for Queue's empty exception
	public EmptyQueueException(String S) {
		super(S);
	}
}