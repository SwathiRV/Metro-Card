package com.geektrust.backend.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.services.IMetroCardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.geektrust.backend.exceptions.NegativeBalanceException;

@DisplayName("BalanceCommandTest")
@ExtendWith(MockitoExtension.class)
public class BalanceCommandTest {
    
    @Mock
    private IMetroCardService metroCardServiceMock;

    @InjectMocks
    private BalanceCommand balanceCommand;
    

    @Test
    @DisplayName("Register a metro card")
    public void addMetroCard(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("BALANCE");
        tokens.add("MC1");
        tokens.add("200");
        when(metroCardServiceMock.create(anyString(), anyFloat())).thenReturn(new MetroCard("MC1", 200));
        balanceCommand.execute(tokens);
        
        verify(metroCardServiceMock, times(1)).create(anyString(),anyFloat());

    }

    @Test
    @DisplayName("Register a metro card with zero balance")
    public void addMetroCardWithZeroBalance(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("BALANCE");
        tokens.add("MC2");
        tokens.add("0");
        when(metroCardServiceMock.create(anyString(), anyFloat())).thenReturn(new MetroCard("MC2", 0));
        balanceCommand.execute(tokens);
        
        verify(metroCardServiceMock, times(1)).create(anyString(),anyFloat());

    }

    @Test
    @DisplayName("Register a metro card with zero balance")
    public void addMetroCardWithNegativeBalanceThrowsNegativeBalanceException(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("BALANCE");
        tokens.add("MC2");
        tokens.add("-50");
        
        when(metroCardServiceMock.create(anyString(), anyFloat())).thenThrow(new NegativeBalanceException());
        
        assertThrows(NegativeBalanceException.class, ()->balanceCommand.execute(tokens));
        verify(metroCardServiceMock, times(1)).create(anyString(),anyFloat());

    }


}
