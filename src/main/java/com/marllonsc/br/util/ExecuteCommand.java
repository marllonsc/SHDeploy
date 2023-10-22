package com.marllonsc.br.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteCommand {

	 public static boolean execute(String command) {
	        // Command to be executed
	        //String command = "ls -l";

	        try {
	            // Create a ProcessBuilder with the specified command
	            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
	            
	            // Redirect error stream to output stream
	            processBuilder.redirectErrorStream(true);
	            
	            // Start the process
	            Process process = processBuilder.start();

	            // Read the output of the command
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }

	            // Wait for the process to complete
	            int exitCode = process.waitFor();
	            System.out.println("Command executed with exit code: " + exitCode);
	            return true;
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        return false;
	    }

}
