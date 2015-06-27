package com.icetea.manager.pagodiario.exception;

public class IncorrectUserLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectUserLoginException() {
		super();
	}

	public IncorrectUserLoginException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IncorrectUserLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectUserLoginException(String message) {
		super(message);
	}

	public IncorrectUserLoginException(Throwable cause) {
		super(cause);
	}

}
