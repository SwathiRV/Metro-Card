package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.PassengerCount;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.enums.PassengerType;
import com.geektrust.backend.exceptions.NoPassengersFoundException;
import com.geektrust.backend.exceptions.NoStationsFoundException;
import com.geektrust.backend.repositories.IStationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("StationServiceTest")
@ExtendWith(MockitoExtension.class)
public class StationServiceTest {
    
    @Mock
    private IStationRepository stationRepositoryMock;

    @InjectMocks
    private StationService stationService;


    @Test
    @DisplayName("create method to create station")
    public void createStation(){

        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 1));
            }
        };
        when(stationRepositoryMock.getByStationName(anyString())).thenReturn(Optional.empty());
        when(stationRepositoryMock.save(any(Station.class))).thenReturn(new Station("1", "CENTRAL", 100, 0,passengerDetails));
        Station expectedOutput = new Station("1", "CENTRAL", 100, 0,passengerDetails);
        Station actualOutput = stationService.create("ADULT", "CENTRAL", 100, 0);
        
        assertEquals(expectedOutput.getId(), actualOutput.getId());
        assertEquals(expectedOutput.getStationName(), actualOutput.getStationName());
        assertEquals(expectedOutput.getCollectedAmount(), actualOutput.getCollectedAmount());
        assertEquals(expectedOutput.getDiscountGiven(), actualOutput.getDiscountGiven());
        assertEquals(expectedOutput.getPassengerDetails(), actualOutput.getPassengerDetails());

        verify(stationRepositoryMock, times(2)).getByStationName(anyString());
        verify(stationRepositoryMock, times(1)).save(any(Station.class));
        
    }

    @Test
    @DisplayName("create method to create station when passenger is child")
    public void createStationWhenChild(){

        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 1));
            }
        };
        when(stationRepositoryMock.getByStationName(anyString())).thenReturn(Optional.empty());
        when(stationRepositoryMock.save(any(Station.class))).thenReturn(new Station("1", "CENTRAL", 100, 0,passengerDetails));
        Station expectedOutput = new Station("1", "CENTRAL", 100, 0,passengerDetails);
        Station actualOutput = stationService.create("ADULT", "CENTRAL", 100, 0);
        
        assertEquals(expectedOutput.getId(), actualOutput.getId());
        assertEquals(expectedOutput.getStationName(), actualOutput.getStationName());
        assertEquals(expectedOutput.getCollectedAmount(), actualOutput.getCollectedAmount());
        assertEquals(expectedOutput.getDiscountGiven(), actualOutput.getDiscountGiven());
        assertEquals(expectedOutput.getPassengerDetails(), actualOutput.getPassengerDetails());

        verify(stationRepositoryMock, times(2)).getByStationName(anyString());
        verify(stationRepositoryMock, times(1)).save(any(Station.class));
        
    }
    

    @Test
    public void getAllStationsSummaryTest(){
        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };

        HashMap<String, PassengerCount> passengerDetails1 = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.KID.toString(), new PassengerCount(PassengerType.KID, 3));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };


        Station station_central = new Station("1", "CENTRAL", 500, 350, passengerDetails);
        Station station_airport = new Station("1", "CENTRAL", 500, 350, passengerDetails1);
     
        List<Station> stations = new ArrayList<>();
        stations.add(station_central);
        stations.add(station_airport);

        when(stationRepositoryMock.getAllStations()).thenReturn(Optional.of(stations));
        
        List<Station> actualOutput = stationService.getAllStationsSummary();

        assertEquals(stations, actualOutput);

    }

    @Test
    public void getAllStationsSummaryTestWhenNoStations(){

        when(stationRepositoryMock.getAllStations()).thenReturn(Optional.empty());
        
        assertThrows(NoStationsFoundException.class, ()->stationService.getAllStationsSummary());

    }

    @Test
    public void getAllPassengerSummaryTestWhen(){
        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };
        Station station = new Station("1", "CENTRAL", 500, 350, passengerDetails);


        List<PassengerCount> expectedOutput = new ArrayList<>();
        expectedOutput.add(new PassengerCount(PassengerType.ADULT, 2));
        expectedOutput.add(new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                                            
        when(stationRepositoryMock.getStationById("1")).thenReturn(Optional.of(station));

        List<PassengerCount> actualOutput = stationService.getAllPassengerSummary("1");

        assertEquals(expectedOutput.get(0).getPassengerType(), actualOutput.get(0).getPassengerType());
        assertEquals(expectedOutput.get(1).getPassengerType(), actualOutput.get(1).getPassengerType());

        assertEquals(expectedOutput.get(0).getCount(), actualOutput.get(0).getCount());
        assertEquals(expectedOutput.get(1).getCount(), actualOutput.get(1).getCount());

    }

    @Test
    public void getAllPassengerSummaryTestWhenNoPassengerWasFound(){

        when(stationRepositoryMock.getStationById("1")).thenThrow(new NoPassengersFoundException());

        assertThrows(NoPassengersFoundException.class, ()->stationService.getAllPassengerSummary("1"));
    }



    @Test
    public void printPassengerTest(){

        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.SENIOR_CITIZEN.toString(), new PassengerCount(PassengerType.SENIOR_CITIZEN, 1));
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 2));
            }
        };
        Station station = new Station("1", "CENTRAL", 500, 350, passengerDetails);
        
        List<Station> stations = new ArrayList<>();
        stations.add(station);

        when(stationRepositoryMock.getAllStations()).thenReturn(Optional.of(stations));
                                            
        when(stationRepositoryMock.getStationById("1")).thenReturn(Optional.of(station));

        stationService.getPrintSummary();

    }

}
