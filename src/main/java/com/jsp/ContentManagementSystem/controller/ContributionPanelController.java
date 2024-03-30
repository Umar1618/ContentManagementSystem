package com.jsp.ContentManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ContentManagementSystem.responsedto.ContributionPanelResponse;
import com.jsp.ContentManagementSystem.service.ContributionPanelService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class ContributionPanelController {
	
	private ContributionPanelService contributionPanelService;

	public ContributionPanelController(ContributionPanelService contributionPanelService) {
		this.contributionPanelService = contributionPanelService;
	}
	
	@Operation(description = "This endpoint is used to add contributors to blog in the database", responses = {
			@ApiResponse(responseCode = "200", description = "Contributors added successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input")
	})
	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(@PathVariable int userId, @PathVariable int panelId){
		return contributionPanelService.addContributor(userId, panelId);
	}
	
	@Operation(description = "This endpoint is used to remove user form Contribution panel", responses = {
			@ApiResponse(responseCode = "200", description = "User removed successfully"),
			@ApiResponse(responseCode = "404", description = "Invalid input")
	})
	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> removeContributor(@PathVariable int userId, @PathVariable int panelId){
		return contributionPanelService.removeContributor(userId, panelId);
	}
}
