package com.geektrust.backend.repositories;

import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.Station;

public interface IStationRepository {
    
    public Station save(Station station);
    public Optional<List<Station>> getAllStations();
    public Optional<Station> getByStationName(String stationName);
    public Optional<Station> getStationById(String stationId);
    
}