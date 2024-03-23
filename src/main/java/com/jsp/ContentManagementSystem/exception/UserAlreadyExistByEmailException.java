package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistByEmailException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public UserAlreadyExistByEmailException(String message) {
		this.message = message;
	}
}
