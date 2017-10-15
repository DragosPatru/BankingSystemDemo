package com.banking.exception;

public class InvalidAmountException extends Exception{

	/**
	 * Generated UUID
	 */
	private static final long serialVersionUID = 7464231368111692382L;

	public InvalidAmountException(String message) {
		super(message);
	}
}
