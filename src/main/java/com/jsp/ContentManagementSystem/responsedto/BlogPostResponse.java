package com.jsp.ContentManagementSystem.responsedto;

import java.time.LocalDateTime;

import com.jsp.ContentManagementSystem.enums.PostType;

public class BlogPostResponse {

	private int blogPostId;
	private String title;
	private String subTitle;
	private String summary;
	private PostType postType;
	private String seoTitle;
	private String seoDescription;
	private String[] seoTopics;
	private String createdBy;
	private LocalDateTime createAt;
	private String lastModifiedBy;
	private LocalDateTime lastModifiedAt;
	
	public BlogPostResponse(int blogPostId, String title, String subTitle, String summary, PostType postType,
			String seoTitle, String seoDescription, String[] seoTopics) {
		super();
		this.blogPostId = blogPostId;
		this.title = title;
		this.subTitle = subTitle;
		this.summary = summary;
		this.postType = postType;
		this.seoTitle = seoTitle;
		this.seoDescription = seoDescription;
		this.seoTopics = seoTopics;
	}
	
	public PostType getPostType() {
		return postType;
	}
	public void setPostType(PostType postType) {
		this.postType = postType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public LocalDateTime getLastModifiedAt() {
		return lastModifiedAt;
	}
	public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	public int getBlogPostId() {
		return blogPostId;
	}
	public void setBlogPostId(int blogPostId) {
		this.blogPostId = blogPostId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSeoTitle() {
		return seoTitle;
	}
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	public String getSeoDescription() {
		return seoDescription;
	}
	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}
	public String[] getSeoTopics() {
		return seoTopics;
	}
	public void setSeoTopics(String[] seoTopics) {
		this.seoTopics = seoTopics;
	}	
}
