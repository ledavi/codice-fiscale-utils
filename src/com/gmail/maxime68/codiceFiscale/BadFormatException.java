package com.gmail.maxime68.codiceFiscale;

@SuppressWarnings("serial")
public class BadFormatException extends Exception {

	public BadFormatException() {
		
	}

	public BadFormatException(String message) {
		super(message);
		
	}

	public BadFormatException(Throwable cause) {
		super(cause);
		
	}

	public BadFormatException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
