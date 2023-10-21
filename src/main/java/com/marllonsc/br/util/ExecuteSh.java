package com.marllonsc.br.util;

import java.io.IOException;

public class ExecuteSh {
	
	public  static  boolean execute(String scriptPath) {
		
		scriptPath = "/home/wolf/test.sh";

        Process process;
        try {
            
            process = Runtime.getRuntime().exec(scriptPath);

            
            process.waitFor();
            

            int exitValue = process.exitValue();


            if (exitValue == 0) {
                System.out.println("Script executed successfully");
                return true;
            } else {
                System.out.println("Script failed with exit value: " + exitValue);
                return false;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
        }
        
        
    }
		

}
