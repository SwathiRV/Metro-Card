package com.geektrust.backend.entities;

import com.geektrust.backend.enums.PassengerType;

public class PassengerCount {
    
    private final PassengerType passengerType;
    private int count;
    
    public PassengerCount(PassengerType passengerType, int count) {
        this.passengerType = passengerType;
        this.count = count;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PassengerCount [passengerType=" + passengerType + " count=" + count + "]";
    }

    
}
