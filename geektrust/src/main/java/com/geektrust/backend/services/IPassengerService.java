package com.geektrust.backend.services;

import com.geektrust.backend.entities.Passenger;

public interface IPassengerService {
    public Passenger create(String metroCardId, String passengerType, String stationName);
}
