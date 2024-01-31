package com.geektrust.backend.commands;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.exceptions.NoPassengersFoundException;
import com.geektrust.backend.exceptions.NoStationsFoundException;
import com.geektrust.backend.services.IStationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CheckInCommandTest")
@ExtendWith(MockitoExtension.class)
public class PrintSummaryCommandTest {

    @Mock
    private IStationService stationServiceMock;

    @InjectMocks
    private PrintSummaryCommand printSummaryCommand;
    
    @Test
    @DisplayName("Print Summary Result Test - 1")
    public void integrationTest1(){

        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");

        doNothing().when(stationServiceMock).getPrintSummary();
    	printSummaryCommand.execute(tokens);

        verify(stationServiceMock, times(1)).getPrintSummary();

    }

    @Test
    @DisplayName("Print Summary Result Test - 2")
    public void integrationTest2(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");

        doNothing().when(stationServiceMock).getPrintSummary();
    	printSummaryCommand.execute(tokens);
        verify(stationServiceMock, times(1)).getPrintSummary();

    }

    @Test
    @DisplayName("Print Summary Result Test - 3")
    public void integrationTest3(){
        
        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");
	
        doNothing().when(stationServiceMock).getPrintSummary();
    	printSummaryCommand.execute(tokens);
        verify(stationServiceMock, times(1)).getPrintSummary();
    
    }

    @Test
    @DisplayName("Print Summary Result Test - 4")
    public void integrationTest4(){

        
        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");
	
        doNothing().when(stationServiceMock).getPrintSummary();
    	printSummaryCommand.execute(tokens);
        verify(stationServiceMock, times(1)).getPrintSummary();
    
    }

    @Test
    @DisplayName("Print Summary Result Test - 5")
    public void integrationTest5(){

        
        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");
	
        doNothing().when(stationServiceMock).getPrintSummary();
    	printSummaryCommand.execute(tokens);
        verify(stationServiceMock, times(1)).getPrintSummary();
    
    }
	
    @Test
    @DisplayName("Print Summary Result Test - 6")
    public void integrationTest6(){

        
        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");
	
        doNothing().when(stationServiceMock).getPrintSummary();
    	printSummaryCommand.execute(tokens);
        verify(stationServiceMock, times(1)).getPrintSummary();
    
    }

    @Test
    @DisplayName("Print Summary Result Test - 7")
    public void integrationTest7(){

        
        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");
	
        doNothing().when(stationServiceMock).getPrintSummary();
    	printSummaryCommand.execute(tokens);
        verify(stationServiceMock, times(1)).getPrintSummary();
    
    }

    @Test
    @DisplayName(" Throws NoPassergersFoundException when no passengers checked in to the station")
    public void integrationTest8(){

        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");
	
        doThrow(NoPassengersFoundException.class).when(stationServiceMock).getPrintSummary();
    	assertThrows(NoPassengersFoundException.class, ()-> printSummaryCommand.execute(tokens));
        verify(stationServiceMock, times(1)).getPrintSummary();
    
    }

    @Test
    @DisplayName(" Throws NoStationFoundException when no station is present")
    public void integrationTest9(){

        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");
	
        doThrow(NoStationsFoundException.class).when(stationServiceMock).getPrintSummary();
    	assertThrows(NoStationsFoundException.class, ()-> printSummaryCommand.execute(tokens));
        verify(stationServiceMock, times(1)).getPrintSummary();
    
    }
}