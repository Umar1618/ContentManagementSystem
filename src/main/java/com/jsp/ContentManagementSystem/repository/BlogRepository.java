package com.jsp.ContentManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ContentManagementSystem.model.Blog;
import com.jsp.ContentManagementSystem.model.ContributionPanel;
import com.jsp.ContentManagementSystem.model.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	boolean existsByTitle(String title);

	boolean existsByUserAndContributionPanel(User owner, ContributionPanel panel);

}
