package com.geektrust.backend.repositories;

import java.util.Optional;
import com.geektrust.backend.entities.Passenger;

public interface IPassengerRepository {
    
    public Passenger save(Passenger passenger);
    public Optional<Passenger> getPassengerByMetroId(String metroCardId);
}
