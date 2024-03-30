package com.jsp.ContentManagementSystem.exception;

@SuppressWarnings("serial")
public class ContributionPanelNotFoundByIdException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public ContributionPanelNotFoundByIdException(String message) {
		this.message = message;
	}
	
}
