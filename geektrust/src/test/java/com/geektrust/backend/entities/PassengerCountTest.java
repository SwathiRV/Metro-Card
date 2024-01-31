package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.geektrust.backend.enums.PassengerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Passenger Count Test")
public class PassengerCountTest {
    
    public PassengerCount passengerCount;

    @BeforeEach
    public void setup(){
        passengerCount = new PassengerCount(PassengerType.SENIOR_CITIZEN, 1);
    }

    @Test
    public void getPassengerType(){
        assertEquals(PassengerType.SENIOR_CITIZEN, passengerCount.getPassengerType());
    }

    @Test
    public void getCountTest(){
        assertEquals(1, passengerCount.getCount());
    }

    @Test
    public void setCountTest(){
        passengerCount.setCount(2);
        assertEquals(2, passengerCount.getCount());
    }

    @Test
    public void toStringTest(){
        String expected = "PassengerCount [passengerType=SENIOR_CITIZEN count=1]";
        assertEquals(expected, passengerCount.toString());
    }


}
