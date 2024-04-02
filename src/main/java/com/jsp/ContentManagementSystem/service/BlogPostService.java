package com.jsp.ContentManagementSystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.ContentManagementSystem.requestdto.BlogPostRequest;
import com.jsp.ContentManagementSystem.responsedto.BlogPostResponse;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

public interface BlogPostService {

	ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPostDraft(int blogId, BlogPostRequest blogPost);

	ResponseEntity<ResponseStructure<BlogPostResponse>> updateBlogPostDraft(int postId, BlogPostRequest blogPost);

	ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(int postId);

}
