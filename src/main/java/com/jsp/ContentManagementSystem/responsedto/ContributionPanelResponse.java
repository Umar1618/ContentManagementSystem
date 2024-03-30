package com.jsp.ContentManagementSystem.responsedto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jsp.ContentManagementSystem.model.User;

public class ContributionPanelResponse {
	
	private int panelId;
	@JsonBackReference
	private List<User> contributors;
	
	public ContributionPanelResponse() {}
	
	public ContributionPanelResponse(int panelId, List<User> contributors) {
		this.panelId = panelId;
		this.contributors = contributors;
	}
	
	public int getPanelId() {
		return panelId;
	}
	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}
	public List<User> getContributors() {
		return contributors;
	}
	public void setContributors(List<User> contributors) {
		this.contributors = contributors;
	}

}
