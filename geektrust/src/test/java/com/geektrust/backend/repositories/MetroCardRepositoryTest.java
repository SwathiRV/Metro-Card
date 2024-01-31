package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("MetroCardRepositoryTest")
public class MetroCardRepositoryTest {
    
    private MetroCardRepository metroCardRepository;

    @BeforeEach
    void setup(){
        metroCardRepository = new MetroCardRepository();
        MetroCard metroCard = new MetroCard("MC1", 400.0f);
        metroCardRepository.save(metroCard);
    }


    @Test
    @DisplayName("save a metro card")
    public void createMetroCard(){
        MetroCard expectedOutput = new MetroCard("MC2", 500.0f);
        MetroCard actualOutput = metroCardRepository.save(expectedOutput);
        assertEquals(expectedOutput.getBalance(), actualOutput.getBalance());
        assertEquals(expectedOutput.getId(), actualOutput.getId());
    }

    @Test
    @DisplayName(" Update metro Card")
    public void updateMetroCardTest(){
        MetroCard metroCard = new MetroCard("MC1", 150.0f);
        MetroCard actualOutput = metroCardRepository.save(metroCard);
        assertEquals(metroCard.getBalance(), actualOutput.getBalance());
        assertEquals(metroCard.getId(), actualOutput.getId());
    }

    @Test
    @DisplayName("get metro card")
    public void getMetroCard(){
        
        MetroCard expectedOutput = new MetroCard("MC1", 400.0f);
        MetroCard actualOutput = metroCardRepository.getMetroCard("MC1").get();
        assertEquals(expectedOutput.getBalance(), actualOutput.getBalance());
        assertEquals(expectedOutput.getId(), actualOutput.getId());
    }

    @Test
    @DisplayName("get metro card when not present")
    public void getMetroCardWhenNotPresent(){
        
        Optional<MetroCard> expectedOutput = Optional.empty();
        Optional<MetroCard> actualOutput = metroCardRepository.getMetroCard("MC2");
        assertEquals(expectedOutput, actualOutput);
    }



}
