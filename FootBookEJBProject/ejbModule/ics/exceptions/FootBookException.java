package ics.exceptions;

public class FootBookException extends Exception {
	private static final long serialVersionUID = 1L;

	public FootBookException(String message) {
		super(message);
	}

	public FootBookException(String message, Throwable cause) {
		super(message, cause);
	}
}
