package com.geektrust.backend.exceptions;

public class NoPassengersFoundException extends RuntimeException{

    public NoPassengersFoundException(){
        super();
    }

    public NoPassengersFoundException(String message){
        super(message);
    }
}
