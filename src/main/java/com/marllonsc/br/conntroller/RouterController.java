package com.marllonsc.br.conntroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.marllonsc.br.entity.Project;
import com.marllonsc.br.service.ProjectService;

@Controller
public class RouterController {
	
	private final ProjectService projectService;

	@Autowired
	public RouterController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping("/")
	public String home(Model model) {	
		List<Project> list = new ArrayList<Project>();
		list = projectService.getAllProjects();
		model.addAttribute("list",list);
		return "index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("message","Welcome!!!");
		return "form";
	}
	
	@PostMapping("/create_project")
    public String createProject(@ModelAttribute Project project) {
        projectService.saveProject(project);
        return "redirect:/";
    }
	
	@GetMapping("/init_project/{id}")
    public String initProject(@PathVariable Long id) {
        projectService.initProject(id);
        return "redirect:/";
    }

}
