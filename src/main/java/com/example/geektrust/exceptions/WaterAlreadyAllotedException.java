package com.example.geektrust.exceptions;

public class WaterAlreadyAllotedException extends RuntimeException{
    public WaterAlreadyAllotedException(String message){
        super(message);
    }
}
