package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Optional;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.Passenger;

public class PassengerRepository implements IPassengerRepository{

    private final HashMap<String, Passenger> passengerRepository;
    private int autoincrement;
    
    public PassengerRepository() {
        this.passengerRepository = new HashMap<String, Passenger>();
        this.autoincrement = Constants.ZERO;
    }

    @Override
    public Passenger save(Passenger passenger) {
        if(passenger.getId()==Constants.NULL){
            autoincrement++;
            Passenger tPassenger = new Passenger(Integer.toString(autoincrement), passenger.getPassengerType(),
            passenger.getMetroCardId(), passenger.getCheckStatus(), passenger.getStationId());
            passengerRepository.put(tPassenger.getId(), tPassenger);
            return tPassenger;
        }
        passengerRepository.put(passenger.getId(), passenger);
        return passenger;
    }

    @Override
    public Optional<Passenger> getPassengerByMetroId(String metroCardId){
        return passengerRepository.values().stream().filter(e->e.getMetroCardId().equals(metroCardId)).findAny(); 
    }

}