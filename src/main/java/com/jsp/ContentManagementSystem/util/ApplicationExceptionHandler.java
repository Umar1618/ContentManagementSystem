package com.jsp.ContentManagementSystem.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ContentManagementSystem.exception.UserAlreadyExistByEmailException;

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

}
