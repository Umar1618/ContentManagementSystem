package com.jsp.ContentManagementSystem.responsedto;

public class BlogResponse {
	
	private int blogId;
	private String title;
	private String[] topics;
	private String about;
//	private List<User> users;

	public int getBlogId() {
		return blogId;
	}
	public BlogResponse(int blogId, String title, String[] topics, String about) {
	super();
	this.blogId = blogId;
	this.title = title;
	this.topics = topics;
	this.about = about;
}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getTopics() {
		return topics;
	}
	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
//	public List<User> getUsers() {
//		return users;
//	}
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
}
