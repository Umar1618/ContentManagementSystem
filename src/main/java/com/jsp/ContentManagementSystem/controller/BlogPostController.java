package com.jsp.ContentManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ContentManagementSystem.requestdto.BlogPostRequest;
import com.jsp.ContentManagementSystem.responsedto.BlogPostResponse;
import com.jsp.ContentManagementSystem.service.BlogPostService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class BlogPostController {

	private BlogPostService blogPostService;

	public BlogPostController(BlogPostService blogPostService) {
		this.blogPostService = blogPostService;
	}
	
	@Operation(description = "This endpoint is used to add new blog post to the database", responses = {
			@ApiResponse(responseCode = "200", description = "BlogPost created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input")
	})
	@PostMapping("/blogs/{blogId}/blog-posts")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPostDraft(@PathVariable int blogId, @RequestBody BlogPostRequest blogPost){
		return blogPostService.createBlogPostDraft(blogId, blogPost);
	}
	
	@Operation(description = "This endpoint is used to update blog post to the database", responses = {
			@ApiResponse(responseCode = "200", description = "BlogPost updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input")
	})
	@PutMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateBlogPostDraft(@PathVariable int postId, @RequestBody BlogPostRequest blogPost){
		return blogPostService.updateBlogPostDraft(postId, blogPost);
	}
}