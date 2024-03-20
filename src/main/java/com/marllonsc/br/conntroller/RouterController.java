package com.marllonsc.br.conntroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.marllonsc.br.config.AppConfig;
import com.marllonsc.br.dto.Message;
import com.marllonsc.br.entity.Actions;
import com.marllonsc.br.entity.Project;
import com.marllonsc.br.entity.RegistryAction;
import com.marllonsc.br.service.ProjectService;
import com.marllonsc.br.service.RegistryActionService;
import com.marllonsc.br.util.ExecuteCommand;

import ch.qos.logback.core.joran.action.Action;

@Controller
public class RouterController {

	private final ProjectService projectService;

	private final RegistryActionService registryActionService;

	private final AppConfig appConfig;

	private static String message;
	private static int status;

	@Autowired
	public RouterController(ProjectService projectService, AppConfig appConfig,
			RegistryActionService registryActionService) {
		this.projectService = projectService;
		this.appConfig = appConfig;
		this.registryActionService = registryActionService;
		message = "";
		status = 0;
	}

	@GetMapping("/")
	public String home(Model model) {
		List<Project> list = new ArrayList<Project>();
		list = projectService.getAllProjects();
		model.addAttribute("list", list);
		// model.addAttribute("Mysql",ExecuteCommand.executeGetReturn("minikube service
		// "+appConfig.getDbService()+" --url"));
		if (!message.isBlank()) {
			model.addAttribute("message", message);
			model.addAttribute("status", status);
		}
		return "index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		message = "";
		model.addAttribute("message", "Welcome!!!");
		return "form";
	}

	@GetMapping("/registry")
	public String registry(Model model) {
		List<RegistryAction> list = new ArrayList<RegistryAction>();
		list = registryActionService.findAllWithProject();
		model.addAttribute("list", list);
		message = "";
		model.addAttribute("message", "Welcome!!!");
		return "registry";
	}

	@PostMapping("/create_project")
	public String createProject(@ModelAttribute Project project) {
		project = projectService.saveProject(project);
		if (project != null) {
			registryActionService.insertRegistryAction(new RegistryAction(project, Actions.CREATE, new Date(), null));
			message = "Success in Create the Project " + project.getName();
			status = 1;
		} else {
			message = "Error in Create the Project.";
			status = 0;
		}
		return "redirect:/";
	}

	@GetMapping("/init_project/{id}")
	public String initProject(@PathVariable Long id) {
		Message check = projectService.initProject(id);
		message = check.getMessage();
		status = check.getStatus();
		return "redirect:/";
	}

	@GetMapping("/deploy_project/{id}")
	public String deployProject(@PathVariable Long id) {
		Message check = projectService.deployProject(id);
		message = check.getMessage();
		status = check.getStatus();
		return "redirect:/";
	}

	@GetMapping("/service_project/{id}")
	public String serviceProject(@PathVariable Long id) {
		Message check = projectService.serviceProject(id);
		message = check.getMessage();
		status = check.getStatus();
		return "redirect:/";
	}

	@GetMapping("/del_service_project/{id}")
	public String delServiceProject(@PathVariable Long id) {
		Message check = projectService.delServiceProject(id);
		message = check.getMessage();
		status = check.getStatus();
		return "redirect:/";
	}

	@GetMapping("/del_deploy_project/{id}")
	public String delDeployProject(@PathVariable Long id) {
		Message check = projectService.delDeployProject(id);
		message = check.getMessage();
		status = check.getStatus();
		return "redirect:/";
	}

	@GetMapping("/del_project/{id}")
	public String delProject(@PathVariable Long id) {
		Message check = projectService.delProject(id);
		message = check.getMessage();
		status = check.getStatus();
		return "redirect:/";
	}

}
