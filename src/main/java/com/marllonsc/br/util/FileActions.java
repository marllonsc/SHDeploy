package com.marllonsc.br.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.marllonsc.br.entity.Project;

public class FileActions {
	
	public static boolean createFile(String path) {
        // Specify the file path
        //String filePath = "C:/path/to/your/file.txt";
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
	
	
	public static boolean writeFille(String p,String body) {
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


	public static String createInit(Project project) {
		return "cd /home/wolf/deploy/projects/ \n"
				+ "git clone "+project.getGit() + "\n"
						+ "";
	}

}
