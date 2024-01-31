package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(" Metro Card Test ")
public class MetroCardTest {
    
    private MetroCard metroCard;

    @BeforeEach
    public void setup(){
        metroCard = new MetroCard("MC1", 500);
    }

    @Test
    public void getBalanceTest(){
        assertEquals(500, metroCard.getBalance());
    }

    @Test
    public void getIdTest(){
        assertEquals("MC1", metroCard.getId());
    }

    @Test
    public void toStringTest(){
        String expected = "MetroCard [id=MC1 balance=500.0]";
        assertEquals(expected, metroCard.toString());
    }
}
