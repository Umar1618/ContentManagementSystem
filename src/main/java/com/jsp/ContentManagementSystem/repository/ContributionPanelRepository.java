package com.jsp.ContentManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ContentManagementSystem.model.ContributionPanel;
import com.jsp.ContentManagementSystem.model.User;

public interface ContributionPanelRepository extends JpaRepository<ContributionPanel, Integer> {

	boolean existsByPanelIdAndContributors(int panelId, User user);
	
}
