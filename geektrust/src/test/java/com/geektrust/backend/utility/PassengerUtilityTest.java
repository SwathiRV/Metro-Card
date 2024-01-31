package com.geektrust.backend.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(" Passenger Utility Test")
public class PassengerUtilityTest {
    

    @Test
    public void getDiscountTest(){
        assertEquals(500*0.5, PassengerUtility.getDiscount(500));
    }

    @Test
    public void getPayTest(){
        assertEquals(100.0, PassengerUtility.getPay(200, 100, 300));
    }

    @Test
    public void getBalanceTest(){
        assertEquals(0, PassengerUtility.getBalance(200, 100, 50));
    }

}

