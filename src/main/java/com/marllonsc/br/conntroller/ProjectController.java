package com.marllonsc.br.conntroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.marllonsc.br.entity.Project;
import com.marllonsc.br.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	private final ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable Long id) {
		return projectService.getProjectById(id).orElse(null);
	}

	@PostMapping
	public Project createProject(@RequestBody Project project) {
		return projectService.saveProject(project);
	}

	@PutMapping("/{id}")
	public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
		// Ensure that the ID in the path and the project body match
		if (!id.equals(project.getId())) {
			throw new IllegalArgumentException("ID in the path does not match ID in the request body");
		}

		return projectService.saveProject(project);
	}

	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable Long id) {
		projectService.deleteProject(id);
	}
}
