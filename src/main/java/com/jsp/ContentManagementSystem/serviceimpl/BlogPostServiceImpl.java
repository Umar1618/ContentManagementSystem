package com.jsp.ContentManagementSystem.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.ContentManagementSystem.enums.PostType;
import com.jsp.ContentManagementSystem.exception.BlogNotFoundByIdException;
import com.jsp.ContentManagementSystem.exception.BlogPostNotFoundByIdException;
import com.jsp.ContentManagementSystem.exception.IllegalAccessRequestException;
import com.jsp.ContentManagementSystem.model.Blog;
import com.jsp.ContentManagementSystem.model.BlogPost;
import com.jsp.ContentManagementSystem.repository.BlogPostRepository;
import com.jsp.ContentManagementSystem.repository.BlogRepository;
import com.jsp.ContentManagementSystem.repository.ContributionPanelRepository;
import com.jsp.ContentManagementSystem.repository.UserRepository;
import com.jsp.ContentManagementSystem.requestdto.BlogPostRequest;
import com.jsp.ContentManagementSystem.responsedto.BlogPostResponse;
import com.jsp.ContentManagementSystem.service.BlogPostService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

@Service
public class BlogPostServiceImpl implements BlogPostService {
	
	private BlogRepository blogRepository;
	private ResponseStructure<BlogPostResponse> structure;
	private BlogPostRepository blogPostRepository;
	private ContributionPanelRepository contributionPanelRepository;
	private UserRepository userRepository;
	
	public BlogPostServiceImpl(BlogRepository blogRepository, ResponseStructure<BlogPostResponse> structure, 
			BlogPostRepository blogPostRepository, ContributionPanelRepository contributionPanelRepository, UserRepository userRepository) {
		this.blogRepository = blogRepository;
		this.structure = structure;
		this.blogPostRepository = blogPostRepository;
		this.contributionPanelRepository = contributionPanelRepository;
		this.userRepository = userRepository;
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
	
	private boolean validateUser(Blog blog) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).map(user -> 
			(email.equals(blog.getUser().getEmail()) || contributionPanelRepository
					.existsByPanelIdAndContributors(blog.getContributionPanel().getPanelId(),user))
		).orElse(false);
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPostDraft(int blogId, BlogPostRequest blogPostRequest) {
		return blogRepository.findById(blogId).map(blog -> {
			if(!validateUser(blog))
				throw new IllegalAccessRequestException("Faild to create blogpost");
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
	
	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateBlogPostDraft(int postId,
			BlogPostRequest blogPostRequest) {
		return blogPostRepository.findById(postId).map(blogPost -> {
			if(validateUser(blogPost.getBlog()))
				throw new IllegalAccessRequestException("Faild to create blogpost");
			blogPost = blogPostRepository.save(mapToBlogPostEntity(blogPostRequest, blogPost));
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("BlogPost updated successfully")
					.setBody(mapToBlogPostResponse(blogPost)));
		}).orElseThrow(()-> new BlogPostNotFoundByIdException("Faild to update blogpost"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return blogPostRepository.findById(postId).map(blogPost -> {
			if(!email.equals(blogPost.getCreatedBy()) && !email.equals(blogPost.getBlog().getUser().getEmail()))
				throw new IllegalAccessRequestException("Faild to delete blogpost");
			blogPostRepository.delete(blogPost);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("BlogPost deleted successfully")
					.setBody(mapToBlogPostResponse(blogPost)));
		}).orElseThrow(()-> new BlogPostNotFoundByIdException("Faild to delete blogpost"));
	}
}
