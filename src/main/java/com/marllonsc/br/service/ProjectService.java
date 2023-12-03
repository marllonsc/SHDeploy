package com.marllonsc.br.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marllonsc.br.config.AppConfig;
import com.marllonsc.br.dto.Message;
import com.marllonsc.br.entity.ProgrammingLanguage;
import com.marllonsc.br.entity.Project;
import com.marllonsc.br.repository.ProjectRepository;
import com.marllonsc.br.util.ExecuteCommand;
import com.marllonsc.br.util.ExecuteSh;
import com.marllonsc.br.util.FileActions;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	private final AppConfig appConfig;

	@Autowired
	public ProjectService(ProjectRepository projectRepository, AppConfig appConfig) {
		this.projectRepository = projectRepository;
		this.appConfig = appConfig;
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

	public Message initProject(Long id) {

		

		Optional<Project> project = getProjectById(id);

		String pathApp = appConfig.getPathApp();
		String pathProject = appConfig.getPathProject();

		if(!FileActions.ExistDirectory(pathApp)){
			FileActions.createFile("folder.sh");
			FileActions.writeFille("folder.sh", FileActions.createfolder(appConfig));
			ExecuteCommand.execute("chmod a+x " + "folder.sh");
			ExecuteSh.execute(System.getProperty("user.dir")+"/folder.sh");
			ExecuteCommand.execute("rm -rf " + "folder.sh");
		}

		boolean check = false;
		Project p = project.get();

		if(!p.getPathApp().endsWith("/")){
			p.setPathApp(p.getPathApp()+"/");
		}

		if(!p.getPathProject().endsWith("/")){
			p.setPathProject(p.getPathProject()+"/");
		}

		check = FileActions.checkPath(p);

		if(!check){
			p.setPathApp(p.getPathProject()+p.getPathApp());
		}

		
		if (!p.getPathApp().startsWith(pathProject, 0)) {
			p.setPathApp(pathProject + project.get().getPathApp());
		}
		if (!p.getPathProject().startsWith(pathProject, 0)) {
			p.setPathProject(pathProject + project.get().getPathProject());
		}

		String fileInit = appConfig.getFileInit() + p.getName() + ".sh";
		String deployFile = appConfig.getFileDeploy()+ p.getName() + ".sh";

		check = FileActions.ExistFile(fileInit);
		boolean checkDeployFile = FileActions.ExistFile(deployFile);

		if (check) {
			ExecuteCommand.execute("rm -rf " + fileInit);
		}

		if (checkDeployFile) {
			ExecuteCommand.execute("rm -rf " + deployFile);
		}


		check = FileActions.ExistDirectory(p.getPathProject());
		if (check) {
			ExecuteCommand.execute("rm -rf " + p.getPathProject());
		}

		FileActions.createFile(fileInit);
		FileActions.createFile(deployFile);

		FileActions.writeFille(fileInit, FileActions.createInit(p,appConfig.getPathProject()));
		FileActions.writeFille(deployFile, FileActions.commandsDeploy(p));

		check = ExecuteCommand.execute("chmod a+x " + fileInit);
		check = ExecuteCommand.execute("chmod a+x " + deployFile);

		if (check) {
			check = ExecuteSh.execute(fileInit);
			if (!check) {
				return new Message("Error to execute file Init sh", 0);
			}
		} else {
			return new Message("Error to execute chmod a+x " + fileInit, 0);
		}

		p.setInit(1);
		saveProject(p);

		return new Message("Success in init Project " + p.getName(), 1);

	}

	public Message deployProject(long id) {

		Optional<Project> project = getProjectById(id);

		Project p = project.get();
		boolean check = false;
		String deployFile = appConfig.getFileDeploy()+ p.getName() + ".sh";

		check = ExecuteCommand.execute("chmod a+x " + deployFile);
		check = ExecuteCommand.execute("sh " + deployFile);
			if (!check) {
				return new Message("Error to execute file deploy sh", 0);
			}

		p.setDeploy(1);
		saveProject(p);

		return new Message("Success in deploy Project " + p.getName(), 1);

	}

	public Message serviceProject(long id) {

		Optional<Project> project = getProjectById(id);

		Project p = project.get();
		boolean check = false;

		String outPut = ExecuteCommand
				.executeGetReturn("minikube kubectl -- get service spring-boot-library --namespace=default ");

		check = outPut.contains("NotFound");

		if (!check) {
			return new Message("Service aready create: " + p.getName(), 0);
		}

		check = FileActions.ExistFile(p.getPathApp() + "service.sh");

		if (!check) {
			return new Message("No find file service on project " + p.getName(), 0);
		}

		check = ExecuteCommand.execute("chmod a+x " + p.getPathApp() + "service.sh");

		if (!check) {
			return new Message("Error to execute chmod a+x " + p.getPathApp() + "service.sh", 0);
		}

		String fileService = appConfig.getFileService() + p.getName() + ".sh";
		FileActions.createFile(fileService);

		check = FileActions.ExistFile(fileService);

		FileActions.writeFille(fileService, FileActions.createCommandsService(p));

		if (check) {
			check = ExecuteCommand.execute("chmod a+x " + fileService);
			check = ExecuteCommand.execute("sh " + fileService);
		} else {
			return new Message("Error to write file sh  " + fileService, 0);
		}

		if (!check) {
			return new Message("Error to execute file sh  " + fileService, 0);
		}

		String ip_service = ExecuteCommand.executeGetReturn("minikube service " + p.getName() + " --url");
		p.setIpPort(ip_service);
		p.setService(1);
		saveProject(p);

		return new Message("Success in create service Project " + p.getName(), 1);

	}

	public Message delServiceProject(long id) {

		Optional<Project> project = getProjectById(id);

		Project p = project.get();
		boolean check = false;

		String outPut = ExecuteCommand
				.executeGetReturn("minikube kubectl -- get service spring-boot-library --namespace=default ");

		check = outPut.contains("NotFound");

		if (!check) {

			check = ExecuteCommand.execute("kubectl delete service " + p.getName());

			if (!check) {
				return new Message("Error in remove service Project " + p.getName(), 0);
			}

			check = ExecuteCommand.execute("rm -rf " + appConfig.getFileService() + p.getName() + ".sh");

			if (!check) {
				return new Message("Error in delete service file " + appConfig.getFileService() + p.getName()
						+ " Project " + p.getName(), 0);
			}

			p.setIpPort(null);
			p.setService(0);
			saveProject(p);

		}

		return new Message("Success in remove service Project " + p.getName(), 1);
	}


	public Message delDeployProject(long id) {

		Optional<Project> project = getProjectById(id);

		Project p = project.get();
		boolean check = false;

			check = ExecuteCommand.execute("docker stop " + p.getName() + ":0.0.1");

			if (!check) {
				return new Message("Error in stop container Project " + p.getName(), 0);
			}

			check = ExecuteCommand.execute("docker rm " + p.getName() + ":0.0.1");

			if (!check) {
				return new Message("Error in remove container Project " + p.getName(), 0);
			}

			check = ExecuteCommand.execute("docker rmi " + p.getName() + ":0.0.1");

			if (!check) {
				return new Message("Error in remove image Project " + p.getName(), 0);
			}

			p.setDeploy(0);
			saveProject(p);


		return new Message("Success in Undeploy Project " + p.getName(), 1);
	}

	public Message delProject(long id) {

		Optional<Project> project = getProjectById(id);

		Project p = project.get();
		boolean check = false;

		check = ExecuteCommand.execute("rm -rf " + appConfig.getFileInit() + p.getName() + ".sh");
		check = ExecuteCommand.execute("rm -rf " + appConfig.getFileDeploy() + p.getName() + ".sh");

		if (!check) {
				return new Message("Error in delete init file " + appConfig.getFileInit() + p.getName()
						+ " Project " + p.getName(), 0);
			}
		

		List<Project> projects =  getAllProjects();
		boolean gitSame = false;
		for (Project p2 : projects) {
			if(p2.getPathProject().equals(p.getPathProject()) && p2.getId()!= p.getId()){
				gitSame=true;
				break;
			}
		}

		if(!gitSame)
			check = ExecuteCommand.execute("rm -rf " + p.getPathProject());

		if (!check) {
				return new Message("Error in delete directorio " + appConfig.getPathProject() + p.getName()
						+ " Project " + p.getName(), 0);
			}

		deleteProject(id);	

		return new Message("Success in delete Project " + p.getName(), 1);
	}


}
