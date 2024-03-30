package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class IllegalAccessRequestException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public IllegalAccessRequestException(String message) {
		this.message = message;
	}
	
}
