package com.banking.exception;

public class NonZeroAmountException extends Exception{
	/**
	 * Generated UUID
	 */
	private static final long serialVersionUID = 7464231368110092382L;

	public NonZeroAmountException(String message) {
		super(message);
	}
}
