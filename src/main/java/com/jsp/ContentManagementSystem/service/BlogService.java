package com.jsp.ContentManagementSystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.ContentManagementSystem.requestdto.BlogRequest;
import com.jsp.ContentManagementSystem.responsedto.BlogResponse;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

public interface BlogService {

	ResponseEntity<ResponseStructure<BlogResponse>> createBlog(int userId, BlogRequest blog);

	ResponseEntity<ResponseStructure<Boolean>> checkForBlog(String title);

	ResponseEntity<ResponseStructure<BlogResponse>> findByBlogId(int blogId);

	ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(int blogId, BlogRequest blog);

}
