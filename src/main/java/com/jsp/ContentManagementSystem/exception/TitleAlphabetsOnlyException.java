package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class TitleAlphabetsOnlyException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public TitleAlphabetsOnlyException(String message) {
		this.message = message;
	}
	
}
