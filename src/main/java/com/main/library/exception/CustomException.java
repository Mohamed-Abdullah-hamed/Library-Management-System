package com.main.library.exception;

public class CustomException extends RuntimeException {
	private String message;

	public CustomException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}