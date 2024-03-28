package com.jsp.ContentManagementSystem.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ContentManagementSystem.exception.*;
import com.jsp.ContentManagementSystem.model.Blog;
import com.jsp.ContentManagementSystem.repository.BlogRepository;
import com.jsp.ContentManagementSystem.repository.UserRepository;
import com.jsp.ContentManagementSystem.requestdto.BlogRequest;
import com.jsp.ContentManagementSystem.responsedto.BlogResponse;
import com.jsp.ContentManagementSystem.service.BlogService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

@Service
public class BlogServiceImpl implements BlogService{
	
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private ResponseStructure<BlogResponse> responseStructure;

	public BlogServiceImpl(UserRepository userRepository, BlogRepository blogRepository,
			ResponseStructure<BlogResponse> responseStructure) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
		this.responseStructure = responseStructure;
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(int userId, BlogRequest blogRequest) {
		return userRepository.findById(userId).map(user -> {
			if(!blogRequest.getTitle().matches("[a-zA-Z ]+") || blogRequest.getTitle()==null)
				throw new TitleAlphabetsOnlyException("Faild to create blog");
				
			if(blogRepository.existsByTitle(blogRequest.getTitle()))
				throw new TitleAlreadyExistsException("Faild to create blog");
			
			if(blogRequest.getTopics().length<1)
				throw new TopicsNotSpecifiedException("Faild to create blog");
			
			Blog blog = mapToBlogEntity(blogRequest, new Blog());
			blog.getUsers().add(user);
			blog = blogRepository.save(blog);
			return ResponseEntity.ok(responseStructure
					.setStatus(HttpStatus.OK.value())
					.setMessage("Blog created successfully")
					.setBody(mapToBlogResponse(blog)));
		}).orElseThrow(()-> new UserNotFoundByIdException("Faild to create blog"));
	}

	private Blog mapToBlogEntity(BlogRequest blogRequest, Blog blog) {
		blog.setTitle(blogRequest.getTitle());
		blog.setTopics(blogRequest.getTopics());
		blog.setAbout(blogRequest.getAbout());
		return blog;
	}

	private BlogResponse mapToBlogResponse(Blog blog) {
		return new BlogResponse(
			blog.getBlogId(),
			blog.getTitle(),
			blog.getTopics(),
			blog.getAbout()
		);
	}

	@Override
	public ResponseEntity<ResponseStructure<Boolean>> checkForBlog(String title) {
		return ResponseEntity.ok(new ResponseStructure<Boolean>()
			.setStatus(HttpStatus.OK.value())
			.setMessage("Blog title details fetched successfully")
			.setBody(blogRepository.existsByTitle(title))
		);
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findByBlogId(int blogId) {
		return blogRepository.findById(blogId).map(blog -> {
			return ResponseEntity.ok(responseStructure
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("Blog found successfully")
				.setBody(mapToBlogResponse(blog))
			);
		}).orElseThrow(()->new BlogNotFoundByIdException("Blog not Found"));
	}
}
