package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.services.IPassengerService;


public class CheckInCommand implements ICommand{

    private final IPassengerService passengerService;

    public CheckInCommand(IPassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Override
    public void execute(List<String> tokens) {
        String metroCardId = tokens.get(Constants.ONE);
        String passengerType = tokens.get(Constants.TWO);
        String stationName = tokens.get(Constants.THREE);
        
        passengerService.create(metroCardId, passengerType, stationName);
        
       
    }
    
}
