package com.example.geektrust.exceptions;

public class WrongCommandEnteredException extends RuntimeException{
    public WrongCommandEnteredException(String message){
        super(message);
    }
}
