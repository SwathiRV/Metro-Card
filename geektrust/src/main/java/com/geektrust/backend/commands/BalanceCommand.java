package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.services.IMetroCardService;

public class BalanceCommand implements ICommand{

    private final IMetroCardService metroCardService;

    public BalanceCommand(IMetroCardService metroCardService) {
        this.metroCardService = metroCardService;
    }

    @Override
    public void execute(List<String> tokens) {
        String metroCardId = tokens.get(Constants.ONE);
        int balance = Integer.parseInt(tokens.get(Constants.TWO));
        
        metroCardService.create(metroCardId, balance);
        
        
    }
    
}
