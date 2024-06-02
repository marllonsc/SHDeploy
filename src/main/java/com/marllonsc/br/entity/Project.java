package com.marllonsc.br.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String pathProject;
	private String pathApp;
	private String git;
	private String ipPort;
	private int service;
	private int init;
	private int deploy;
	private int ativo;

	@Enumerated(EnumType.STRING)
	@Column(name = "programming_language")
	private ProgrammingLanguage programmingLanguage;

	public Project(Long id, String name, String pathProject, String pathApp, ProgrammingLanguage programmingLanguage,
			String git, String ipPort, int service, int init, int deploy, int ativo) {
		super();
		this.id = id;
		this.name = name;
		this.pathProject = pathProject;
		this.pathApp = pathApp;
		this.git = git;
		this.programmingLanguage = programmingLanguage;
		this.ipPort = ipPort;
		this.service = service;
		this.init = init;
		this.deploy = deploy;
		this.ativo = ativo;
	}

	public Project() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPathProject() {
		return pathProject;
	}

	public void setPathProject(String pathProject) {
		this.pathProject = pathProject;
	}

	public String getPathApp() {
		return this.pathApp;
	}

	public void setPathApp(String pathApp) {
		this.pathApp = pathApp;
	}

	public String getGit() {
		return git;
	}

	public void setGit(String git) {
		this.git = git;
	}

	public String getIpPort() {
		return ipPort;
	}

	public void setIpPort(String ipPort) {
		this.ipPort = ipPort;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}

	public int getDeploy() {
		return deploy;
	}

	public void setDeploy(int deploy) {
		this.deploy = deploy;
	}

	public ProgrammingLanguage getProgrammingLanguage() {
		return this.programmingLanguage;
	}

	public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	public int getAtivo() {
		return service;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	// getters and setters

}
