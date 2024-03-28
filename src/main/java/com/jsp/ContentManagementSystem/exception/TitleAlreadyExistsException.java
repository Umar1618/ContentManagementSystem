package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class TitleAlreadyExistsException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public TitleAlreadyExistsException(String message) {
		this.message = message;
	}

}
