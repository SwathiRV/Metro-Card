package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Optional;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerCount;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.enums.CheckStatus;
import com.geektrust.backend.enums.PassengerType;
import com.geektrust.backend.exceptions.NoMetroCardFoundException;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IPassengerRepository;
import com.geektrust.backend.repositories.IStationRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName("PassengerServiceTest")
@ExtendWith(MockitoExtension.class)
public class PassengerServiceTest {
    
    @Mock
    private IPassengerRepository passengerRepositoryMock;

    @Mock
    private IStationRepository stationRepositoryMock;

    @Mock
    private IMetroCardRepository metroCardRepositoryMock;

    @Mock
    private IStationService stationServiceMock;

    @Mock
    private IMetroCardService metroCardServiceMock;

    @InjectMocks
    private PassengerService passengerService;


    @Test
    @DisplayName("create method saves the passenger")
    public void createPassenger(){
        
        HashMap<String, PassengerCount> passengerDetails = new HashMap<String, PassengerCount>(){
            {
                put(PassengerType.ADULT.toString(), new PassengerCount(PassengerType.ADULT, 1));
            }
        };

        when(metroCardRepositoryMock.getMetroCard(anyString())).thenReturn(Optional.of(new MetroCard("MC1", 300)));
        when(passengerRepositoryMock.getPassengerByMetroId(anyString())).thenReturn(Optional.empty());
        when(metroCardServiceMock.update(anyString(), anyFloat())).thenReturn(new MetroCard("MC1", 100));
        when(stationServiceMock.create(anyString(), anyString(), anyFloat(), anyFloat())).thenReturn(new Station("1", "CENTRAL", 200, 0, passengerDetails));
        when(passengerRepositoryMock.save(any(Passenger.class))).thenReturn(new Passenger("1", PassengerType.ADULT, "MC1", CheckStatus.CHECK_IN, "1"));
        when(stationRepositoryMock.getByStationName(anyString())).thenReturn(Optional.of(new Station("1", "CENTRAL", 200, 0, passengerDetails)));

        Passenger expectedOutput = new Passenger("1", PassengerType.ADULT, "MC1", CheckStatus.CHECK_IN, "1");

        Passenger actualOutput = passengerService.create("MC1", "ADULT", "CENTRAL");

        assertEquals(expectedOutput.getCheckStatus(), actualOutput.getCheckStatus());
        assertEquals(expectedOutput.getId(), actualOutput.getId());
        assertEquals(expectedOutput.getMetroCardId(), actualOutput.getMetroCardId());
        assertEquals(expectedOutput.getPassengerType(), actualOutput.getPassengerType());
        assertEquals(expectedOutput.getStationId(), actualOutput.getStationId());

        verify(metroCardRepositoryMock,times(1)).getMetroCard(anyString());
        verify(passengerRepositoryMock, times(1)).getPassengerByMetroId(anyString());
        verify(metroCardServiceMock, times(1)).update(anyString(),anyFloat());
        verify(stationServiceMock,times(1)).create(anyString(), anyString(), anyFloat(), anyFloat());
        verify(passengerRepositoryMock,times(1)).save(any(Passenger.class));
        verify(stationRepositoryMock, times(1)).getByStationName(anyString());
    }

    @Test
    @DisplayName("create method when metro card is not registered")
    public void createPassengerWhenCardNotRegistered(){
        when(metroCardRepositoryMock.getMetroCard(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoMetroCardFoundException.class, ()-> passengerService.create("MC1", PassengerType.ADULT.toString(), "CENTRAL"));

        verify(metroCardRepositoryMock,times(1)).getMetroCard(anyString());

    }

}
