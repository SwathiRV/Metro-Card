package com.geektrust.backend.exceptions;

public class NegativeBalanceException extends RuntimeException{
    public NegativeBalanceException(){
        super();
    }

    public NegativeBalanceException(String message){
        super(message);
    }
}
