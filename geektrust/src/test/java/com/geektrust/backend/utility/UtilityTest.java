package com.geektrust.backend.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Utility Test")
public class UtilityTest {
    
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    public void printTest(){

        String expected = "Geektrust challenge";

        Utility.print("Geektrust challenge");

        assertEquals(expected,outputStreamCaptor.toString().trim());
	}

    @Test
    public void printTest1(){

        String expected = "Backslash";

        Utility.print("Backslash");

        assertEquals(expected,outputStreamCaptor.toString().trim());
	}

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
