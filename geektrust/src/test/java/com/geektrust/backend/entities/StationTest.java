package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import com.geektrust.backend.enums.PassengerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(" Station Test ")
public class StationTest {

    private Station station;

    @BeforeEach
    public void setup(){
        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };

        station = new Station("1", "CENTRAL", 500, 350, passengerDetails);
    }

    @Test
    public void getStationByNameTest(){
        assertEquals("CENTRAL", station.getStationName());
    }

    @Test
    public void getCollectedAmountTest(){
        assertEquals(500, station.getCollectedAmount());
    }
    
    @Test
    public void getDiscountGivenTest(){
        assertEquals(350, station.getDiscountGiven());
    }

    @Test
    public void getIdTest(){
        assertEquals("1", station.getId());
    }

    @Test
    public void getPassengerDetailsTest(){
        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };

        station = new Station("1", "CENTRAL", 500, 350, passengerDetails);
        assertEquals(passengerDetails, station.getPassengerDetails());
    }

    @Test
    public void toStringTest(){
        String expected = "Station [id=1 collectedAmount=500.0, discountGiven=350.0, passengerDetails={SENIOR_CITIZEN=PassengerCount [passengerType=SENIOR_CITIZEN count=1], ADULT=PassengerCount [passengerType=ADULT count=2]}, stationName=CENTRAL]";
        assertEquals(expected, station.toString());
    }
}
