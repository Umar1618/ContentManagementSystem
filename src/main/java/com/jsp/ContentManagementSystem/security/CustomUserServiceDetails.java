package com.jsp.ContentManagementSystem.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.ContentManagementSystem.repository.UserRepository;

@Service
public class CustomUserServiceDetails implements UserDetailsService {

	private UserRepository userRepository;
	
	public CustomUserServiceDetails(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username)
				.map(user -> new CustomUserDetails(user))
				.orElseThrow(()-> new UsernameNotFoundException("User name not exist"));
	}
}
