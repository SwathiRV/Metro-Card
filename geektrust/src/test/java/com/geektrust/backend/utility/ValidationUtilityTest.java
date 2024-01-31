package com.geektrust.backend.utility;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.geektrust.backend.exceptions.NoPassengersFoundException;
import com.geektrust.backend.exceptions.NoStationsFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(" validation utility ")
public class ValidationUtilityTest {
    
    @Test
    public void validateTestNoPassengersFoundException(){
        assertThrows(NoPassengersFoundException.class, ()-> ValidationUtility.validate(null, null));
    }

    @Test
    public void validateTestNoPassengersFoundException1(){
        assertThrows(NoPassengersFoundException.class, ()-> ValidationUtility.validate(null, "CENTRAL"));
    }

    @Test
    public void validateTestNoStationsFoundException(){
        assertThrows(NoStationsFoundException.class, ()-> ValidationUtility.validate("ADULT", null));     
    }

    @Test
    public void validateTestInvalidPassenger(){
        assertThrows(NoPassengersFoundException.class, ()-> ValidationUtility.validate("DAD", "CENTRAL"));     
    }

    @Test
    public void validateTestInvalidStation(){
        assertThrows(NoStationsFoundException.class, ()-> ValidationUtility.validate("ADULT", "BANGALORE"));     
    }

    @Test
    public void validateTestValidPassengerAndStation(){
        assertDoesNotThrow(()->ValidationUtility.validate("ADULT", "CENTRAL"));

    }
}
