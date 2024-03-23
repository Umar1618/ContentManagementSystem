package com.jsp.ContentManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ContentManagementSystem.requestdto.UserRequest;
import com.jsp.ContentManagementSystem.responsedto.UserResponse;
import com.jsp.ContentManagementSystem.service.UserService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private UserService userService;
	
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest){
		return userService.registerUser(userRequest);
	}
	
}
