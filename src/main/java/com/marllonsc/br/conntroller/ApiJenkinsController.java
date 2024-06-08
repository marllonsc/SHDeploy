package com.marllonsc.br.conntroller;

import com.marllonsc.br.entity.Project;
import com.marllonsc.br.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ApiJenkinsController {

    private final ProjectService projectService;

    @Autowired
    public ApiJenkinsController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/deploy")
    public String getUserById(@RequestBody() Project project) {
        return projectService.jenkisDeploy(project);
    }

}
