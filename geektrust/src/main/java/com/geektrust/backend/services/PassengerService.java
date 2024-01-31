package com.geektrust.backend.services;

import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.enums.CheckStatus;
import com.geektrust.backend.enums.PassengerType;
import com.geektrust.backend.exceptions.NoMetroCardFoundException;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IPassengerRepository;
import com.geektrust.backend.repositories.IStationRepository;
import com.geektrust.backend.utility.PassengerUtility;
import com.geektrust.backend.utility.StationUtility;
import com.geektrust.backend.utility.ValidationUtility;

public class PassengerService implements IPassengerService{
    
    private final IPassengerRepository passengerRepository;
    private final IStationRepository stationRepository;
    private final IMetroCardRepository metroCardRepository;
    private final IStationService stationService;
    private final IMetroCardService metroCardService;

    public PassengerService(IPassengerRepository passengerRepository,
            IStationRepository stationRepository, IMetroCardRepository metroCardRepository, 
            IStationService stationService, IMetroCardService metroCardService) {
        this.passengerRepository = passengerRepository;
        this.stationRepository = stationRepository;
        this.metroCardRepository = metroCardRepository;
        this.stationService = stationService;
        this.metroCardService = metroCardService;
    }

    public Passenger create(String metroCardId, String passengerType, String stationName){

        ValidationUtility.validate(passengerType, stationName);

        float actualBalance = Constants.ZERO;
        try{
            actualBalance = metroCardRepository.getMetroCard(metroCardId).get().getBalance();
        }
        catch(Exception e){
            throw new NoMetroCardFoundException("MetroCard is not registered!");
        }

        if(passengerRepository.getPassengerByMetroId(metroCardId).isPresent()){

            float discount = PassengerUtility.findDiscount(findStatus(passengerRepository.getPassengerByMetroId(metroCardId).get(),stationName), passengerType);

            stationService.create(
                passengerType, 
                stationName, 
                PassengerUtility.getPay(PassengerType.valueOf(passengerType).getPassengerCost(), discount, actualBalance), 
                discount
            );

            metroCardService.update(
                metroCardId, 
                PassengerUtility.getBalance(PassengerType.valueOf(passengerType).getPassengerCost(), discount, actualBalance)
            );

            return passengerRepository.save(
                new Passenger(
                    passengerRepository.getPassengerByMetroId(metroCardId).get().getId(), 
                    PassengerType.valueOf(passengerType),
                    metroCardId, 
                    findStatus(passengerRepository.getPassengerByMetroId(metroCardId).get(),stationName), 
                    stationRepository.getByStationName(stationName).get().getId()
                )
            );
        }else{

            stationService.create(
                passengerType, 
                stationName, 
                PassengerUtility.getPay(PassengerType.valueOf(passengerType).getPassengerCost(), Constants.ZERO, actualBalance), 
                Constants.ZERO
            );
            
            metroCardService.update(
                metroCardId, 
                PassengerUtility.getBalance(PassengerType.valueOf(passengerType).getPassengerCost(), Constants.ZERO, actualBalance)
            );
            
            return passengerRepository.save(
                new Passenger( 
                    PassengerType.valueOf(passengerType),
                    metroCardId, 
                    CheckStatus.CHECK_IN, 
                    stationRepository.getByStationName(stationName).get().getId()
                )
            );
        }
    }

    private CheckStatus findStatus(Passenger passenger, String stationName){
        return StationUtility.checkTheStatus(stationRepository.getStationById(passenger.getStationId()), stationName, passenger.getCheckStatus());
    }

    

}