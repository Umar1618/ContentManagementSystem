package com.jsp.ContentManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ContentManagementSystem.requestdto.BlogRequest;
import com.jsp.ContentManagementSystem.responsedto.BlogResponse;
import com.jsp.ContentManagementSystem.service.BlogService;
import com.jsp.ContentManagementSystem.util.ErrorStructure;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class BlogController {
	
	private BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@Operation(description = "This endpoint is used to add new blog to the database", responses = {
			@ApiResponse(responseCode = "200", description = "Blog created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input")
	})
	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@PathVariable int userId, @RequestBody BlogRequest blog){
		return blogService.createBlog(userId, blog);
	}
	
	@Operation(description = "This endpoint is used to check the blog title availability", responses = {
			@ApiResponse(responseCode = "200", description = "Blog title details fetched successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input")
	})
	@GetMapping("/titles/{title}/blogs")
	public ResponseEntity<ResponseStructure<Boolean>> checkForBlog(@PathVariable String title){
		return blogService.checkForBlog(title);
	}
	
	@Operation(description = "This endpoint will fetch blog from the database based on id", responses = {
			@ApiResponse(responseCode = "200", description = "Blog found successfully"),
			@ApiResponse(responseCode = "404", description = "Blog not exist by the given id", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))	
			})
	})
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> findByBlogId(@PathVariable int blogId){
		return blogService.findByBlogId(blogId);
	}
}
