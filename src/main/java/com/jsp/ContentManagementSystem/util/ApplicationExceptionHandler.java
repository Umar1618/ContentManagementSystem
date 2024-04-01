package com.jsp.ContentManagementSystem.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ContentManagementSystem.exception.BlogNotFoundByIdException;
import com.jsp.ContentManagementSystem.exception.BlogPostNotFoundByIdException;
import com.jsp.ContentManagementSystem.exception.ContributionPanelNotFoundByIdException;
import com.jsp.ContentManagementSystem.exception.IllegalAccessRequestException;
import com.jsp.ContentManagementSystem.exception.TitleAlphabetsOnlyException;
import com.jsp.ContentManagementSystem.exception.TitleAlreadyExistsException;
import com.jsp.ContentManagementSystem.exception.TopicsNotSpecifiedException;
import com.jsp.ContentManagementSystem.exception.UserAlreadyExistByEmailException;
import com.jsp.ContentManagementSystem.exception.UserNotFoundByIdException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	private ErrorStructure<String> structure;
	
	public ApplicationExceptionHandler(ErrorStructure<String> structure) {
		super();
		this.structure = structure;
	}

	private ResponseEntity<ErrorStructure<String>> errorResponse(
			HttpStatus status, String message, String rootCause){
		return new ResponseEntity<ErrorStructure<String>>(structure
				.setStatus(status.value())
				.setMessage(message)
				.setRootCause(rootCause), status);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistsByEmail(
			UserAlreadyExistByEmailException e){
		return errorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), 
				"User Already exists with the given email Id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(
			UserNotFoundByIdException e){
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), 
				"User not exists with the given Id");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTitleAlphabetsOnly(
			TitleAlphabetsOnlyException e){
		return errorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), 
				"Title should only contain alphabets");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTitleAlreadyExists(
			TitleAlreadyExistsException e){
		return errorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), 
				"The title should be unique");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTopicsNotSpecified(
			TopicsNotSpecifiedException e){
		return errorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), 
				"At list one topic has to be specified");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogNotFoundById(
			BlogNotFoundByIdException e){
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), 
				"Blog not exists with the given Id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleIllegalAccessRequest(
			IllegalAccessRequestException e){
		return errorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), 
				"User not allowed to Access");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleContributionPanelNotFoundById(
			ContributionPanelNotFoundByIdException e){
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), 
				"Contribution Panel not exists with the given Id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogPostNotFoundById(
			BlogPostNotFoundByIdException e){
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), 
				"BlogPost not exists with the given Id");
	}
}
