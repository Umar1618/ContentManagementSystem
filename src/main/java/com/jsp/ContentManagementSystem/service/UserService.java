package com.jsp.ContentManagementSystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.ContentManagementSystem.requestdto.UserRequest;
import com.jsp.ContentManagementSystem.responsedto.UserResponse;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

public interface UserService {

	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest);


	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(int userId);

	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

}
