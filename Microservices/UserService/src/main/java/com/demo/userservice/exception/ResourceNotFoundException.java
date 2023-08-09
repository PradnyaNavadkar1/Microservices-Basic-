package com.demo.userservice.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found exception");
    }

    public ResourceNotFoundException(String message){
        super (message);
    }
}