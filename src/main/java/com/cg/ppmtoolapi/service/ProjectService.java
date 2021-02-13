package com.cg.ppmtoolapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ppmtoolapi.domain.Project;
import com.cg.ppmtoolapi.exception.ProjectIdException;
import com.cg.ppmtoolapi.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	 private ProjectRepository projectRepository;
	
	public Project saveorUpdate(Project project ) {
		
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
			
		}catch(Exception e) {
			throw new ProjectIdException("project Id :"+project.getProjectIdentifier().toUpperCase()+" available");
			
		}
		
		
		
	}
	public Project findProjectByProjectIdentifier(String projectIdentifier) {
		Project project=projectRepository.findbyProjectIdentifier(projectIdentifier.toUpperCase());
		if(project!=null) {
			throw new ProjectIdException("project id"+project.getProjectIdentifier()+" not found");
		}
		
		return project;
		
	}
	

}
