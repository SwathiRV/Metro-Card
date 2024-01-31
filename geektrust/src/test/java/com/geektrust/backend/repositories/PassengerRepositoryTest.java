package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Optional;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.enums.CheckStatus;
import com.geektrust.backend.enums.PassengerType;
import com.geektrust.backend.entities.PassengerCount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PassengerRepositoryTest")
public class PassengerRepositoryTest {
    
    private PassengerRepository passengerRepository;

    @BeforeEach
    void setup(){
        HashMap<String, PassengerCount> passengerDetails1 = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };
        final HashMap<String, Station> stationMap = new HashMap<String,Station>(){
            {
                put("1",new Station("1", "CENTRAL", 100, 200, passengerDetails1));
                
            }
        };
        StationRepository stationRepository = new StationRepository();
        stationRepository.save(stationMap.get("1"));
        passengerRepository = new PassengerRepository();
        Passenger passenger = new Passenger(PassengerType.KID, "MC1", CheckStatus.CHECK_IN, "1");
        passengerRepository.save(passenger);
    }

    @Test
    @DisplayName("save Passenger to Repository")
    public void savePassenger(){
        Passenger passenger = new Passenger(PassengerType.ADULT, "MC2", CheckStatus.CHECK_IN, "1");
        Passenger actualOutput = passengerRepository.save(passenger);
        Passenger expectedOutput = new Passenger("2", PassengerType.ADULT, "MC2", CheckStatus.CHECK_IN, "1");
        assertEquals(expectedOutput.getCheckStatus(), actualOutput.getCheckStatus());
        assertEquals(expectedOutput.getId(), actualOutput.getId());
        assertEquals(expectedOutput.getMetroCardId(), actualOutput.getMetroCardId());
        assertEquals(expectedOutput.getStationId(), actualOutput.getStationId());
        assertEquals(expectedOutput.getPassengerType(), actualOutput.getPassengerType());
    }

    @Test
    @DisplayName("Get a Passenger by metro Id")
    public void getPassengerByMetroId(){
        Optional<Passenger> expectedOutput = Optional.of(new Passenger("1", PassengerType.KID, "MC1", CheckStatus.CHECK_IN, "1"));
        Optional<Passenger> actualOutput = passengerRepository.getPassengerByMetroId("MC1");
        
        assertEquals(expectedOutput.get().getCheckStatus(), actualOutput.get().getCheckStatus());
        assertEquals(expectedOutput.get().getId(), actualOutput.get().getId());
        assertEquals(expectedOutput.get().getMetroCardId(), actualOutput.get().getMetroCardId());
        assertEquals(expectedOutput.get().getStationId(), actualOutput.get().getStationId());
        assertEquals(expectedOutput.get().getPassengerType(), actualOutput.get().getPassengerType());
    }

    @Test
    @DisplayName("Get a Passenger by metro Id When not Present")
    public void getPassengerByMetroIdWhenNotPresent(){
        Optional<Passenger> expectedOutput = Optional.empty();
        Optional<Passenger> actualOutput = passengerRepository.getPassengerByMetroId("MC5");
        Assertions.assertEquals(expectedOutput, actualOutput);
    }


}
