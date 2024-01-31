package com.geektrust.backend.exceptions;

public class NoMetroCardFoundException extends RuntimeException{
    public NoMetroCardFoundException(){
        super();
    }

    public NoMetroCardFoundException(String message){
        super(message);
    }
}
