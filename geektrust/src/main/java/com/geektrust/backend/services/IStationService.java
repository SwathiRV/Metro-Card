package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.entities.PassengerCount;
import com.geektrust.backend.entities.Station;

public interface IStationService {
    public Station create(String passengerType, String stationName, float pay, float discount);
    public List<Station> getAllStationsSummary();
    public List<PassengerCount> getAllPassengerSummary(String stationId);
    public void getPrintSummary();
}
