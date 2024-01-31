package com.geektrust.backend.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashMap;
import java.util.Optional;
import com.geektrust.backend.entities.PassengerCount;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.enums.CheckStatus;
import com.geektrust.backend.enums.PassengerType;
import com.geektrust.backend.exceptions.NoStationsFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Station Utility Test")
public class StationUtilityTest {

    @Test
    public void checkTheStatusTest(){

        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };
        Station station = new Station("1", "CENTRAL", 500, 350, passengerDetails);

        CheckStatus checkstatus = StationUtility.checkTheStatus(Optional.of(station), "AIRPORT",  CheckStatus.CHECK_IN);
        
        assertEquals(CheckStatus.CHECK_OUT, checkstatus);
	}


    @Test
    public void checkTheStatusTestWhenCheckedOut(){

        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };
        Station station = new Station("1", "CENTRAL", 500, 350, passengerDetails);

        CheckStatus checkstatus = StationUtility.checkTheStatus(Optional.of(station), "AIRPORT",  CheckStatus.CHECK_OUT);
        
        assertEquals(CheckStatus.CHECK_IN, checkstatus);
	}


    @Test
    public void checkTheStatusThrowsNoStationFoundException(){

        assertThrows(NoStationsFoundException.class, ()->StationUtility.checkTheStatus(Optional.empty(), "AIRPORT",  CheckStatus.CHECK_OUT));
    }

    @Test
    public void newStationTest(){

        HashMap<String, PassengerCount> passengerDetails = new HashMap<>();
        passengerDetails.put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 1));

        Station expected = new Station(null, "CENTRAL", 500, 150, passengerDetails);
        Station actual = StationUtility.newStation("CENTRAL", "ADULT", 500, 150);      
        assertEquals(expected.getStationName(), actual.getStationName());
        assertEquals(expected.getDiscountGiven(), actual.getDiscountGiven());
        assertEquals(expected.getCollectedAmount(), actual.getCollectedAmount());
        assertEquals(expected.getPassengerDetails().get("ADULT").getCount(), actual.getPassengerDetails().get("ADULT").getCount());
    }


    @Test
    public void updateStationTest(){

        HashMap<String, PassengerCount> passengerDetails = new HashMap<>();
        passengerDetails.put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
        passengerDetails.put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));

        Station station = new Station("1", "CENTRAL", 500, 200, passengerDetails);

        passengerDetails.put(PassengerType.KID.toString(), new PassengerCount(PassengerType.KID, 1));
        Station expected = new Station("1", "CENTRAL", 550, 200, passengerDetails);

        Station actual = StationUtility.updateStation(station, "KID", 50, 0);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCollectedAmount(), actual.getCollectedAmount());
        assertEquals(expected.getDiscountGiven(), actual.getDiscountGiven());
        assertEquals(expected.getStationName(), actual.getStationName());
        assertEquals(expected.getPassengerDetails().size(), actual.getPassengerDetails().size());
    }

}

