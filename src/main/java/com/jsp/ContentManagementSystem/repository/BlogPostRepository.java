package com.jsp.ContentManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ContentManagementSystem.model.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

}
