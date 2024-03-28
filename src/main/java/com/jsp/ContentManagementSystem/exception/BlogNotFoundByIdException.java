package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class BlogNotFoundByIdException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public BlogNotFoundByIdException(String message) {
		this.message = message;
	}

}
