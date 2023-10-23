package com.marllonsc.br.dto;

public class Message {

    private String message;
    private int status;

    public Message(String message, int status){
        this.message = message;
        this.status = status; 
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
    
}
