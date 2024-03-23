package com.jsp.ContentManagementSystem.serviceimpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ContentManagementSystem.exception.UserAlreadyExistByEmailException;
import com.jsp.ContentManagementSystem.model.User;
import com.jsp.ContentManagementSystem.repository.UserRepository;
import com.jsp.ContentManagementSystem.requestdto.UserRequest;
import com.jsp.ContentManagementSystem.responsedto.UserResponse;
import com.jsp.ContentManagementSystem.service.UserService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private ResponseEntity<ResponseStructure<UserResponse>> structure;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {
		if(userRepository.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistByEmailException("Faild to register user");
		
		User user = userRepository.save(null)
		
	}

}
