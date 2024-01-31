package com.geektrust.backend.enums;

public enum PassengerType {  
    ADULT(200),
    SENIOR_CITIZEN(100),
    KID(50);

    private final int cost;

    private PassengerType(int cost) {
        this.cost = cost;
    }

    public int getPassengerCost(){
        return cost;
    }
    

}
