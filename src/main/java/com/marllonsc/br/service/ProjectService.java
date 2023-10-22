package com.marllonsc.br.service;

import java.lang.reflect.Executable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marllonsc.br.entity.Project;
import com.marllonsc.br.repository.ProjectRepository;
import com.marllonsc.br.util.ExecuteCommand;
import com.marllonsc.br.util.ExecuteSh;
import com.marllonsc.br.util.FileActions;

@Service
public class ProjectService {
	
 private final ProjectRepository projectRepository;

 @Autowired
 public ProjectService(ProjectRepository projectRepository) {
     this.projectRepository = projectRepository;
 }

 public List<Project> getAllProjects() {
     return projectRepository.findAll();
 }

 public Optional<Project> getProjectById(Long id) {
     return projectRepository.findById(id);
 }

 public Project saveProject(Project project) {
     return projectRepository.save(project);
 }

 public void deleteProject(Long id) {
     projectRepository.deleteById(id);
 }
 
 public void initProject(Long id) {
	 
	 Optional<Project> project = getProjectById(id);
	 String path = "/home/wolf/deploy/";
	 
	 String pathCode = "";
	 
	 if(project.get().getPath().isEmpty()){
		 pathCode = path+project.get().getName();
	 }else {
		 pathCode = path+project.get().getPath(); 
	 }
	
	 project.get().setPath(pathCode);
	 
	 
	 String fileInit = "/home/wolf/deploy/int_"+project.get().getName()+".sh";
	 
	 FileActions.createFile(fileInit);
	 
	 FileActions.writeFille(fileInit, FileActions.createInit(project.get()));
	 ExecuteCommand.execute("chmod a+x "+fileInit);
	 ExecuteSh.execute(fileInit);
	 Project p = project.get();
	 p.setInit(1);
	 saveProject(p);
 }
 
}
