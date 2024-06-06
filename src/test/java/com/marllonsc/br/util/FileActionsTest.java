package com.marllonsc.br.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.marllonsc.br.entity.Project;
import com.marllonsc.br.entity.ProgrammingLanguage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FileActionsTest {

    @TempDir
    Path tempDir;

    private String testFilePath;
    private Project mockProject;

    @BeforeEach
    public void setUp() {
        testFilePath = tempDir.resolve("testFile.txt").toString();
        mockProject = mock(Project.class);
    }

    @Test
    public void testCreateFile() throws IOException {
        assertTrue(FileActions.createFile(testFilePath));
        assertTrue(Files.exists(Path.of(testFilePath)));
    }

    @Test
    public void testCreateFileAlreadyExists() throws IOException {
        Files.createFile(Path.of(testFilePath));
        assertTrue(FileActions.createFile(testFilePath));
    }

    @Test
    public void testWriteFille() throws IOException {
        Files.createFile(Path.of(testFilePath));
        assertTrue(FileActions.writeFille(testFilePath, "Hello, World!"));
        assertTrue(Files.readString(Path.of(testFilePath)).contains("Hello, World!"));
    }

    @Test
    public void testWriteFilleFileNotFound() {
        assertFalse(FileActions.writeFille(testFilePath + "nonexistent", "Hello, World!"));
    }

    @Test
    public void testCreateInit() {
        when(mockProject.getGit()).thenReturn("https://github.com/example/repo.git");
        String expected = "cd " + tempDir + " \n"
                + "git clone https://github.com/example/repo.git\n";
        String result = FileActions.createInit(mockProject, tempDir.toString());
        assertTrue(result.contains("git clone https://github.com/example/repo.git"));
    }

    @Test
    public void testExistDirectory() {
        assertTrue(FileActions.ExistDirectory(tempDir.toString()));
    }

    @Test
    public void testExistFile() throws IOException {
        Files.createFile(Path.of(testFilePath));
        assertTrue(FileActions.ExistFile(testFilePath));
    }

    @Test
    public void testCreateCommandsDeploy() {
        when(mockProject.getPathApp()).thenReturn("/path/to/app");
        String expected = "cd /path/to/app\n" + "git pull \n" + "sh deploy.sh";
        assertTrue(FileActions.createCommandsDeploy(mockProject).contains("sh deploy.sh"));
    }

    @Test
    public void testCreateCommandsService() {
        when(mockProject.getPathApp()).thenReturn("/path/to/app");
        String expected = "cd /path/to/app\n" + "git pull \n" + "sh service.sh";
        assertTrue(FileActions.createCommandsService(mockProject).contains("sh service.sh"));
    }

    @Test
    public void testCheckPath() {
        when(mockProject.getPathApp()).thenReturn("/root/app");
        when(mockProject.getPathProject()).thenReturn("/root");
        assertTrue(FileActions.checkPath(mockProject));
    }

    @Test
    public void testCommandsDeployMaven() {
        when(mockProject.getPathApp()).thenReturn("/path/to/app");
        when(mockProject.getProgrammingLanguage()).thenReturn(ProgrammingLanguage.MAVEN);
        when(mockProject.getGit()).thenReturn("https://github.com/example/repo.git");
        when(mockProject.getName()).thenReturn("example-project");
        when(mockProject.getIpPort()).thenReturn("8080");

        String expected = "cd /path/to/app \n" +
                "git pull \n" +
                "mvn clean install \n" +
                "docker build -t example-project:0.0.1 . \n" +
                "docker run -d -p 8080:8080 --name example-project example-project:0.0.1";

        assertTrue(FileActions.commandsDeploy(mockProject).contains("mvn clean install"));
    }

    @Test
    public void testCommandsDeployReact() {
        when(mockProject.getPathApp()).thenReturn("/path/to/app");
        when(mockProject.getProgrammingLanguage()).thenReturn(ProgrammingLanguage.REACT);
        when(mockProject.getGit()).thenReturn("https://github.com/example/repo.git");
        when(mockProject.getName()).thenReturn("example-project");
        when(mockProject.getIpPort()).thenReturn("3000");

        String expected = "cd /path/to/app \n" +
                "git pull \n" +
                "npm i \n" +
                "npm run build \n" +
                "docker build -t example-project . \n" +
                "docker run -d -p 3000:80 --name example-project example-project:latest";

        assertTrue(FileActions.commandsDeploy(mockProject).contains("npm run build"));
    }

}
