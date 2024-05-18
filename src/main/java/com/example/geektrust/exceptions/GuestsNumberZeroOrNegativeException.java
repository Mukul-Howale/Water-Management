package com.example.geektrust.exceptions;

public class GuestsNumberZeroOrNegativeException extends RuntimeException{
    public GuestsNumberZeroOrNegativeException(String message){
        super(message);
    }
}
