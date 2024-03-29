package com.jsp.ContentManagementSystem.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jsp.ContentManagementSystem.service.ContributionPanelService;

@RestController
public class ContributionPanelController {
	
	private ContributionPanelService contributionPanelService;

	public ContributionPanelController(ContributionPanelService contributionPanelService) {
		this.contributionPanelService = contributionPanelService;
	}

}
