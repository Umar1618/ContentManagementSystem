package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class BlogPostNotFoundByIdException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public BlogPostNotFoundByIdException(String message) {
		this.message = message;
	}
	
}
