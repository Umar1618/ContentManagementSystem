package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class UserNotFoundByIdException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public UserNotFoundByIdException(String message) {
		this.message = message;
	}

}
