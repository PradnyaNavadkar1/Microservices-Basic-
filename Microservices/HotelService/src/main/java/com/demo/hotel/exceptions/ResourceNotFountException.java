package com.demo.hotel.exceptions;

public class ResourceNotFountException extends RuntimeException {
    public ResourceNotFountException(String s) {
        super(s);
    }

    public ResourceNotFountException(){
        super("resource not foound !!");
    }
}
