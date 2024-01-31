package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.exceptions.NegativeBalanceException;
import com.geektrust.backend.exceptions.NoMetroCardFoundException;
import com.geektrust.backend.repositories.IMetroCardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("MetroCardServeTest")
@ExtendWith(MockitoExtension.class)
public class MetroCardServiceTest {
    
    @Mock
    private IMetroCardRepository metroCardRepositoryMock;

    @InjectMocks
    private MetroCardService metroCardService;

    @Test
    @DisplayName("save method should create Metrocard")
    public void createMetroCard(){
        //Arrange
        MetroCard expectedOutput = new MetroCard("MC1", 250);
        when(metroCardRepositoryMock.save(any(MetroCard.class))).thenReturn(expectedOutput);

        //Act
        MetroCard actualOutput = metroCardService.create("MC1", 250);

        //Assert
        Assertions.assertEquals(expectedOutput,actualOutput);
        verify(metroCardRepositoryMock,times(1)).save(any(MetroCard.class));
    }
    
    @Test
    @DisplayName("save method should throw negative balance exception")
    public void createMetroCardWithNegativeBalance(){
        
        Assertions.assertThrows(NegativeBalanceException.class, ()->metroCardService.create("MC1", -50));

    }

    @Test
    @DisplayName("update method")
    public void updateMetroCard(){

        MetroCard expectedOutput = new MetroCard("MC1", 300);
        when(metroCardRepositoryMock.getMetroCard(anyString())).thenReturn(Optional.of(new MetroCard("MC1", 250)));
        when(metroCardRepositoryMock.getMetroCard(anyString())).thenReturn(Optional.of(new MetroCard("MC1", 300)));
        
        metroCardService.create("MC1", 250);
        MetroCard actualOutput = metroCardService.update("MC1", 300);
        assertEquals(expectedOutput.getBalance(), actualOutput.getBalance());
        assertEquals(expectedOutput.getId(), actualOutput.getId());

        verify(metroCardRepositoryMock,times(2)).getMetroCard(anyString());
    }

    @Test
    @DisplayName("update method throws exception when card not present")
    public void updateMetroCardWhenCradNotPresent(){

        when(metroCardRepositoryMock.getMetroCard(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoMetroCardFoundException.class, ()-> metroCardService.update("MC1", 300));
        
        verify(metroCardRepositoryMock,times(1)).getMetroCard(anyString());
    }


    @Test
    @DisplayName("When metro card balance is negative")
    public void metroCardWhenBalanceIsFloatNegative(){
        //Act
        Assertions.assertThrows(NoMetroCardFoundException.class, ()-> metroCardService.update("MC1", -300.0f));
        
    }

    @Test
    @DisplayName("When metro card balance is zero")
    public void metroCardWhenBalanceIsZero(){

       
        MetroCard expectedOutput = new MetroCard("MC1",0);
        when(metroCardRepositoryMock.save(any(MetroCard.class))).thenReturn(expectedOutput);

        //Act
        MetroCard actualOutput = metroCardService.create("MC1", 0);

        //Assert
        Assertions.assertEquals(expectedOutput,actualOutput);
        verify(metroCardRepositoryMock,times(1)).save(any(MetroCard.class));
  
    }



}
