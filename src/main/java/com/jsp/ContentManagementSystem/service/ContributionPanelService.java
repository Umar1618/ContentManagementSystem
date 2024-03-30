package com.jsp.ContentManagementSystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.ContentManagementSystem.responsedto.ContributionPanelResponse;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

public interface ContributionPanelService {

	ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(int userId, int panelId);

	ResponseEntity<ResponseStructure<ContributionPanelResponse>> removeContributor(int userId, int panelId);

}
