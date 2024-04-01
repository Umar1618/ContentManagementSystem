package com.jsp.ContentManagementSystem.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ContentManagementSystem.enums.PostType;
import com.jsp.ContentManagementSystem.exception.BlogNotFoundByIdException;
import com.jsp.ContentManagementSystem.model.BlogPost;
import com.jsp.ContentManagementSystem.repository.BlogPostRepository;
import com.jsp.ContentManagementSystem.repository.BlogRepository;
import com.jsp.ContentManagementSystem.requestdto.BlogPostRequest;
import com.jsp.ContentManagementSystem.responsedto.BlogPostResponse;
import com.jsp.ContentManagementSystem.service.BlogPostService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

@Service
public class BlogPostServiceImpl implements BlogPostService {
	
	private BlogRepository blogRepository;
	private ResponseStructure<BlogPostResponse> structure;
	private BlogPostRepository blogPostRepository;
	
	public BlogPostServiceImpl(BlogRepository blogRepository, ResponseStructure<BlogPostResponse> structure, BlogPostRepository blogPostRepository) {
		this.blogRepository = blogRepository;
		this.structure = structure;
		this.blogPostRepository = blogPostRepository;
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPostDraft(int blogId, BlogPostRequest blogPostRequest) {
		return blogRepository.findById(blogId).map(blog -> {
			BlogPost blogPost = mapToBlogPostEntity(blogPostRequest, new BlogPost());
			blogPost.setBlog(blog);
			blogPost = blogPostRepository.save(blogPost);
			blog.getBlogPosts().add(blogPost);
			blogRepository.save(blog);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("BlogPost drafted successfully")
					.setBody(mapToBlogPostResponse(blogPost)));
		}).orElseThrow(()-> new BlogNotFoundByIdException("Faild to create blogpost"));
	}
	
	private BlogPostResponse mapToBlogPostResponse(BlogPost blogPost) {
		return new BlogPostResponse(
				blogPost.getBlogPostId(),
				blogPost.getTitle(),
				blogPost.getSubTitle(),
				blogPost.getSummary(),
				blogPost.getPostType(),
				blogPost.getCreatedBy(),
				blogPost.getCreateAt(),
				blogPost.getLastModifiedBy(),
				blogPost.getLastModifiedAt()
		);
	}

	private BlogPost mapToBlogPostEntity(BlogPostRequest blogPostRequest, BlogPost blogPost) {
		blogPost.setTitle(blogPostRequest.getTitle());
		blogPost.setSubTitle(blogPostRequest.getSubTitle());
		blogPost.setSummary(blogPostRequest.getSummary());
		blogPost.setPostType(PostType.DRAFT);
		return blogPost;
	}
}
