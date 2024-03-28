package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class TopicsNotSpecifiedException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public TopicsNotSpecifiedException(String message) {
		this.message = message;
	}
	
}
