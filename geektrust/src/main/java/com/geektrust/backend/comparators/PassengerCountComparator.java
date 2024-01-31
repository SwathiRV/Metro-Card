package com.geektrust.backend.comparators;

import java.util.Comparator;
import com.geektrust.backend.entities.PassengerCount;

public class PassengerCountComparator implements Comparator<PassengerCount>{

    @Override
    public int compare(PassengerCount passenger1, PassengerCount passenger2) {
        if(passenger1.getCount() == passenger2.getCount()){
            return passenger1.getPassengerType().toString().compareTo(passenger2.getPassengerType().toString());
        }else{
            return passenger2.getCount() - passenger1.getCount();
        }
    }
    
}
