package com.marllonsc.br.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app") // Prefix for properties in application.properties
public class AppConfig {

    private String root;
    private String pathApp;
    private String pathProject;
    private String fileInit;
    private String fileDeploy;
    private String fileService;
    private String dbService;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getPathApp() {
        return pathApp;
    }

    public void setPathApp(String pathApp) {
        this.pathApp = pathApp;
    }

    public String getFileInit() {
        return fileInit;
    }

    public String getPathProject() {
        return pathProject;
    }

    public void setFileInit(String fileInit) {
        this.fileInit = fileInit;
    }

    public void setPathProject(String pathProject) {
        this.pathProject = pathProject;
    }

    public void setFileDeploy(String fileDeploy) {
        this.fileDeploy = fileDeploy;
    }

    public void setFileService(String fileService) {
        this.fileService = fileService;
    }

    public String getFileDeploy() {
        return fileDeploy;
    }

    public String getFileService() {
        return fileService;
    }

	public String getDbService() {
		return dbService;
	}

	public void setDbService(String dbService) {
		this.dbService = dbService;
	}
    
    
    
}
