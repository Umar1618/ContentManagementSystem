package com.jsp.ContentManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ContentManagementSystem.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);

}
