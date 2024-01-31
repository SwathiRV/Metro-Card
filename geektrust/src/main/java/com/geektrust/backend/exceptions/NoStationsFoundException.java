package com.geektrust.backend.exceptions;

public class NoStationsFoundException extends RuntimeException{

    public NoStationsFoundException(){
        super();
    }

    public NoStationsFoundException(String message){
        super(message);
    }
}
