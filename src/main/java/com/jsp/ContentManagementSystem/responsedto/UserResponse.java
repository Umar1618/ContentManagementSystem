package com.jsp.ContentManagementSystem.responsedto;

import java.time.LocalDateTime;

public class UserResponse {
	
	private int userId;
	private String username;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	
	public UserResponse(int userId, String username, String email, LocalDateTime createdAt,
			LocalDateTime lastModifiedAt) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.createdAt = createdAt;
		this.lastModifiedAt = lastModifiedAt;
	}

	public UserResponse() {}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	

}
