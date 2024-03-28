package com.jsp.ContentManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ContentManagementSystem.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	boolean existsByTitle(String title);

}
