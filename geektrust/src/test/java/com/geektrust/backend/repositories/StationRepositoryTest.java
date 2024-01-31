package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.PassengerCount;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.enums.PassengerType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StationRepositoryTest")
public class StationRepositoryTest {
    
    private StationRepository stationRepository;
    private HashMap<String,Station> stationMap;

    @BeforeEach
    void setup(){
        HashMap<String, PassengerCount> passengerDetails1 = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };

        stationMap = new HashMap<String,Station>(){
            {
                put("1",new Station("1", "CENTRAL", 100, 200, passengerDetails1));
                
            }
        };
        stationRepository = new StationRepository(stationMap);
    }

    @Test
    @DisplayName("save the station to the station repository")
    public void saveStation(){

        HashMap<String, PassengerCount> passengerDetails= new HashMap<String, PassengerCount>();
        passengerDetails.put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
        Station station = new Station("AIRPORT", 200.0f, 400.0f, passengerDetails);
        Station expectedOutput = new Station("2", "AIRPORT", 200.0f, 400.0f, passengerDetails);
        Station actualOutput = stationRepository.save(station);
        assertEquals(expectedOutput.getId(), actualOutput.getId());
        assertEquals(expectedOutput.getStationName(), actualOutput.getStationName());
        assertEquals(expectedOutput.getCollectedAmount(), actualOutput.getCollectedAmount());
        assertEquals(expectedOutput.getDiscountGiven(), actualOutput.getDiscountGiven());
        assertEquals(expectedOutput.getPassengerDetails(), actualOutput.getPassengerDetails());

    }
         
    @Test
    @DisplayName("Get all the stations")
    public void getAllStation(){
        List<Station> optStation = new ArrayList<>();
        optStation.add(stationMap.get("1"));
        Optional<List<Station>> expectedOutput = Optional.of(optStation);
        Optional<List<Station>> actualOutput = stationRepository.getAllStations();
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    @Test
    @DisplayName("Get all stations when station is not present")
    public void getAllStationWhenNotPresent(){
        stationRepository = new StationRepository();
        Optional<List<Station>> expectedOutput = Optional.of(new ArrayList<>());
        Optional<List<Station>> actualOutput = stationRepository.getAllStations();
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    @Test
    @DisplayName("Get a station")
    public void getStation(){
        Optional<Station> expectedOutput = Optional.of(stationMap.get("1"));
        Optional<Station> actualOutput = stationRepository.getStationById("1");
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    @Test
    @DisplayName("Get a station that's not present")
    public void getStationIfNotPresent(){
        Optional<Station> expectedOutput = Optional.empty();
        Optional<Station> actualOutput = stationRepository.getStationById("4");
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    @Test
    @DisplayName("Get a station by name")
    public void getStationByName(){
        Optional<Station> expectedOutput = Optional.of(stationMap.get("1"));
        Optional<Station> actualOutput = stationRepository.getByStationName("CENTRAL");
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    @Test
    @DisplayName("Get a station by name when not present")
    public void getStationByNameWhenNotPresent(){
        Optional<Station> expectedOutput = Optional.empty();
        Optional<Station> actualOutput = stationRepository.getByStationName("ABC");
        Assertions.assertEquals(expectedOutput,actualOutput);
    }




}
