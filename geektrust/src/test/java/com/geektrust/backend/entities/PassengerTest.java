package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.geektrust.backend.enums.CheckStatus;
import com.geektrust.backend.enums.PassengerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(" Passenger Test ")
public class PassengerTest {
    
    private Passenger passenger;

    @BeforeEach
    public void setup(){
        passenger = new Passenger(PassengerType.ADULT, "MC2", CheckStatus.CHECK_IN, "1");
    }

    @Test
    public void getIdTest(){
        assertNull(passenger.getId());
    }

    @Test
    public void getCheckStatusTest(){
        assertEquals(CheckStatus.CHECK_IN, passenger.getCheckStatus());
    }

    @Test
    public void getMetroCardIdTest(){
        assertEquals("MC2", passenger.getMetroCardId());
    }

    @Test
    public void getPassengerTypeTest(){
        assertEquals(PassengerType.ADULT, passenger.getPassengerType());
    }

    @Test
    public void getStationIdTest(){
        assertEquals("1", passenger.getStationId());
    }

    @Test
    public void toStringTest(){
        String expected = "Passenger [id=null checkStatus=CHECK_IN, metroCardId=MC2, passengerType=ADULT, stationId=1]";
        assertEquals(expected, passenger.toString());
    }



}
