package com.jsp.ContentManagementSystem.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.ContentManagementSystem.exception.ContributionPanelNotFoundByIdException;
import com.jsp.ContentManagementSystem.exception.IllegalAccessRequestException;
import com.jsp.ContentManagementSystem.exception.UserNotFoundByIdException;
import com.jsp.ContentManagementSystem.model.ContributionPanel;
import com.jsp.ContentManagementSystem.repository.BlogRepository;
import com.jsp.ContentManagementSystem.repository.ContributionPanelRepository;
import com.jsp.ContentManagementSystem.repository.UserRepository;
import com.jsp.ContentManagementSystem.responsedto.ContributionPanelResponse;
import com.jsp.ContentManagementSystem.service.ContributionPanelService;
import com.jsp.ContentManagementSystem.util.ResponseStructure;

@Service
public class ContributionPanelServiceImpl implements ContributionPanelService {
	
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private ContributionPanelRepository contributionPanelRepository;
	private ResponseStructure<ContributionPanelResponse> responseStructure;
	
	public ContributionPanelServiceImpl(UserRepository userRepository, BlogRepository blogRepository,
			ContributionPanelRepository contributionPanelRepository,
			ResponseStructure<ContributionPanelResponse> responseStructure) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
		this.contributionPanelRepository = contributionPanelRepository;
		this.responseStructure = responseStructure;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(int userId, int panelId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).map(owner -> {
			return contributionPanelRepository.findById(panelId).map(panel -> {
				if(!blogRepository.existsByUserAndContributionPanel(owner, panel))
					throw new IllegalAccessRequestException("Failed to add Contributor");
				return userRepository.findById(userId).map(conributor -> {
					if(!panel.getUsers().contains(conributor)){
						panel.getUsers().add(conributor);
						contributionPanelRepository.save(panel);
					}
					return ResponseEntity.ok(responseStructure
							.setStatus(HttpStatus.OK.value())
							.setMessage("Contributor added successfully")
							.setBody(mapToContributionPanelResponse(panel)));
				}).orElseThrow(()-> new UserNotFoundByIdException("Failed to add Contributor"));
			}).orElseThrow(()-> new ContributionPanelNotFoundByIdException("Failed to add Contributor"));
		}).get();
	}

	private ContributionPanelResponse mapToContributionPanelResponse(ContributionPanel panel) {
		return new ContributionPanelResponse(panel.getPanelId(), panel.getUsers());
	}

	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> removeContributor(int userId, int panelId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).map(owner -> {
			return contributionPanelRepository.findById(panelId).map(panel -> {
				if(!blogRepository.existsByUserAndContributionPanel(owner, panel))
					throw new IllegalAccessRequestException("Failed to remove Contributor");
				return userRepository.findById(userId).map(conributor -> {
					panel.getUsers().remove(conributor);
					contributionPanelRepository.save(panel);
					return ResponseEntity.ok(responseStructure
							.setStatus(HttpStatus.OK.value())
							.setMessage("Contributor removed successfully")
							.setBody(mapToContributionPanelResponse(panel)));
				}).orElseThrow(()-> new UserNotFoundByIdException("Failed to remove Contributor"));
			}).orElseThrow(()-> new ContributionPanelNotFoundByIdException("Failed to remove Contributor"));
		}).get();
	}

}
