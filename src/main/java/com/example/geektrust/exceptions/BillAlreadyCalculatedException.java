package com.example.geektrust.exceptions;

public class BillAlreadyCalculatedException extends RuntimeException{
    public BillAlreadyCalculatedException(String message){
        super(message);
    }
}
