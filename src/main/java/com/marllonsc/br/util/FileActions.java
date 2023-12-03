package com.marllonsc.br.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.marllonsc.br.config.AppConfig;
import com.marllonsc.br.entity.Project;

public class FileActions {

    public static boolean createFile(String path) {
        // Specify the file path
        // String filePath = "C:/path/to/your/file.txt";
        String filePath = path;

        try {
            // Create a File object
            File file = new File(filePath);

            // Check if the file already exists
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getAbsolutePath());
                return true;
            } else {
                System.out.println("File already exists.");
                return true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean writeFille(String p, String body) {
        // Specify the file path
        String filePath = p;

        try {
            // Get the Path object for the file
            Path path = Path.of(filePath);

            // Text to be written to the file
            String textToWrite = body;

            // Write the text to the file (append mode)
            Files.writeString(path, textToWrite, StandardOpenOption.APPEND);

            System.out.println("Text successfully written to the file.");

            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        return false;
    }

    public static String createInit(Project project, String path) {
        return "cd "+path+ " \n"
                + "git clone " + project.getGit() + "\n"
                + "";
    }

    public static boolean ExistDirectory(String pathToFileOrFolder) {

        // Check if it's a directory (folder)
        boolean isDirectory = Files.isDirectory(Paths.get(pathToFileOrFolder));
        return isDirectory;
    }

    public static boolean ExistFile(String pathToFileOrFolder) {

        // Check if it's a file
        boolean isFile = Files.isRegularFile(Paths.get(pathToFileOrFolder));
        return isFile;
    }

    public static String createCommandsDeploy(Project p) {
        String cmd = "cd " + p.getPathApp() + "\n" + "git pull \n" + "sh deploy.sh";
        return cmd;
    }

    public static String createCommandsService(Project p) {
        String cmd = "cd " + p.getPathApp() + "\n" + "git pull \n" + "sh service.sh";
        return cmd;
    }

    public static boolean checkPath(Project p) {

        if (p.getPathApp().contains(p.getPathProject())) {
            return true;
        }

        return false;
    }

    public static String createfolder(AppConfig appConfig) {
         String cmd = "cd " + appConfig.getRoot() + "\n" + "mkdir deploy \n" + "chmod a+x deploy \n cd deploy \n mkdir projects \n chmod a+x project" + //
                 "";
        return cmd;
    }

    public static String commandsDeploy(Project project) {
         return "cd "+project.getPathApp()+ " \n"
                + "mvn clean install \n" 
                + "docker build -t " + project.getName() + ":0.0.1 . \n"
                + "docker run -p 8080:"+project.getIpPort()+" "+ project.getName() + ":0.0.1 \n"  
                + "" ;
    }

}
