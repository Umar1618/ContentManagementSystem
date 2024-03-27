package com.jsp.ContentManagementSystem.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ContentManagementSystem.exception.UserAlreadyExistByEmailException;
import com.jsp.ContentManagementSystem.exception.UserNotFoundByIdException;
import com.jsp.ContentManagementSystem.model.User;
import com.jsp.ContentManagementSystem.repository.UserRepository;
import com.jsp.ContentManagementSystem.requestdto.UserRequest;
import com.jsp.ContentManagementSystem.responsedto.UserResponse;
import com.jsp.ContentManagementSystem.service.UserService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private ResponseStructure<UserResponse> structure;
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, ResponseStructure<UserResponse> structure, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.structure = structure;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {
		if(userRepository.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistByEmailException("Faild to register user");
		
		User user = userRepository.save(mapToUserEntity(userRequest, new User()));
		return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
				.setMessage("User registered successfully")
				.setBody(mapToUserResponse(user)));
	}
	
//	private UserResponse mapToUserResponse(User user) {
//		return UserResponse.builder()
//				.userId(user.getUserId())
//				.username(user.getUsername())
//				.email(user.getEmail())
//				.build();
//	}
	
	private UserResponse mapToUserResponse(User user) {
	    return new UserResponse(user.getUserId(), 
	    		user.getUsername(),
	    		user.getEmail(),
	    		user.getCreatedAt(),
	    		user.getLastModifiedAt());
	}
	
	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setUsername(userRequest.getUsername());
		user.setDeleted(false);
		return user;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {
		return userRepository.findById(userId).map(user -> {
			user.setDeleted(true);
			userRepository.save(user);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("User Deleted Temporaryly")
					.setBody(mapToUserResponse(user))
					);})
		.orElseThrow(()-> new UserNotFoundByIdException("User Not Found"));
	}
}
