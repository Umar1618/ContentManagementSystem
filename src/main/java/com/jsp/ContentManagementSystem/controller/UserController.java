package com.jsp.ContentManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ContentManagementSystem.requestdto.UserRequest;
import com.jsp.ContentManagementSystem.responsedto.UserResponse;
import com.jsp.ContentManagementSystem.service.UserService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest){
		return userService.registerUser(userRequest);
	}
	
	@GetMapping("/test")
	public String test() {
		return "Hello from cms";
	}
	@Operation(description = "This endpoint is fetch user from the database based on id", responses = {
			@ApiResponse(responseCode = "200", description = "User found successfully"),
			@ApiResponse(responseCode = "404", description = "User not exist by the given id")
	})
	@GetMapping("users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(@PathVariable int userId){
		return userService.findByUserId(userId);
	}
}
