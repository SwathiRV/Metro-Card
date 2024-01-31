package com.geektrust.backend.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.enums.CheckStatus;
import com.geektrust.backend.enums.PassengerType;
import com.geektrust.backend.exceptions.NoMetroCardFoundException;
import com.geektrust.backend.services.IPassengerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CheckInCommandTest")
@ExtendWith(MockitoExtension.class)
public class CheckInCommandTest {

    @Mock
    private IPassengerService passengerServiceMock;

    @InjectMocks
    private CheckInCommand checkInCommand;
    

    @Test
    @DisplayName("Passenger CheckIn in Central")
    public void passengerCheckInStationCentral(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("CHECK_IN");
        tokens.add("MC1");
        tokens.add("ADULT");
        tokens.add("CENTRAL");
        when(passengerServiceMock.create(anyString(), anyString(), anyString())).thenReturn(new Passenger(PassengerType.ADULT, "MC1", CheckStatus.CHECK_IN, "1"));
        checkInCommand.execute(tokens);
        
        verify(passengerServiceMock, times(1)).create(anyString(),anyString(),anyString());

    }

    @Test
    @DisplayName("Passenger CheckOut in Central")
    public void passengerCheckOutStationCentral(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("CHECK_OUT");
        tokens.add("MC1");
        tokens.add("ADULT");
        tokens.add("CENTRAL");
        when(passengerServiceMock.create(anyString(), anyString(), anyString())).thenReturn(new Passenger(PassengerType.ADULT, "MC1", CheckStatus.CHECK_OUT, "1"));
        checkInCommand.execute(tokens);
        
        verify(passengerServiceMock, times(1)).create(anyString(),anyString(),anyString());

    }

    @Test
    @DisplayName("Passenger CheckIn in Airport")
    public void passengerCheckInStationAirport(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("CHECK_IN");
        tokens.add("MC2");
        tokens.add("ADULT");
        tokens.add("AIRPORT");
        when(passengerServiceMock.create(anyString(), anyString(), anyString())).thenReturn(new Passenger(PassengerType.ADULT, "MC2", CheckStatus.CHECK_IN, "2"));
        checkInCommand.execute(tokens);
        
        verify(passengerServiceMock, times(1)).create(anyString(),anyString(),anyString());

    }

    @Test
    @DisplayName("Passenger CheckOut in Airport")
    public void passengerCheckOutStationAirport(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("CHECK_OUT");
        tokens.add("MC2");
        tokens.add("ADULT");
        tokens.add("AIRPORT");
        when(passengerServiceMock.create(anyString(), anyString(), anyString())).thenReturn(new Passenger(PassengerType.ADULT, "MC2", CheckStatus.CHECK_OUT, "2"));
        checkInCommand.execute(tokens);
        
        verify(passengerServiceMock, times(1)).create(anyString(),anyString(),anyString());

    }

    @Test
    @DisplayName("Passenger CheckIn in Airport When No Metro Card Found")
    public void passengerCheckOutStationAirportWithException(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("CHECK_IN");
        tokens.add("MC2");
        tokens.add("ADULT");
        tokens.add("AIRPORT");
        when(passengerServiceMock.create(anyString(), anyString(), anyString())).thenThrow(new NoMetroCardFoundException());
        assertThrows(NoMetroCardFoundException.class, ()-> checkInCommand.execute(tokens));
        verify(passengerServiceMock, times(1)).create(anyString(),anyString(),anyString());

    }

    @Test
    @DisplayName("Passenger CheckIn in Central As Kid")
    public void passengerCheckInStationCentralAsKid(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("CHECK_IN");
        tokens.add("MC1");
        tokens.add("KID");
        tokens.add("CENTRAL");
        when(passengerServiceMock.create(anyString(), anyString(), anyString())).thenReturn(new Passenger(PassengerType.KID, "MC1", CheckStatus.CHECK_IN, "1"));
        checkInCommand.execute(tokens);
        
        verify(passengerServiceMock, times(1)).create(anyString(),anyString(),anyString());

    }

    @Test
    @DisplayName("Passenger CheckIn in Central As Senior Citizen")
    public void passengerCheckInStationCentralAsSeniorCitizen(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("CHECK_IN");
        tokens.add("MC1");
        tokens.add("SENIOR_CITIZEN");
        tokens.add("CENTRAL");
        when(passengerServiceMock.create(anyString(), anyString(), anyString())).thenReturn(new Passenger(PassengerType.SENIOR_CITIZEN, "MC1", CheckStatus.CHECK_IN, "1"));
        checkInCommand.execute(tokens);
        
        verify(passengerServiceMock, times(1)).create(anyString(),anyString(),anyString());

    }
    
}
