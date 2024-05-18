package com.example.geektrust.exceptions;

public class NoBillCommandException extends RuntimeException{
    public NoBillCommandException(String message){
        super(message);
    }
}
