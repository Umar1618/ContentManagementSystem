package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class UserNotABlogOwnerException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public UserNotABlogOwnerException(String message) {
		this.message = message;
	}
	
}
