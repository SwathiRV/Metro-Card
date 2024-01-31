package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.Station;

public class StationRepository implements IStationRepository{

    private final HashMap<String, Station> stationRepository;
    private int autoincrement;

    public StationRepository() {
        this.stationRepository = new HashMap<>();
        this.autoincrement = Constants.ZERO;
    }
    
    public StationRepository(HashMap<String, Station> stationRepository) {
        this.stationRepository = stationRepository;
        this.autoincrement = stationRepository.size();
    }

    @Override
    public Station save(Station station) {
        if(station.getId()== Constants.NULL){
            autoincrement++;
            Station tStation = new Station(Integer.toString(autoincrement), 
            station.getStationName(), station.getCollectedAmount(), station.getDiscountGiven(), 
            station.getPassengerDetails());
            stationRepository.put(tStation.getId(), tStation);
            return tStation;
        }
        stationRepository.put(station.getId(), station);
        return station;
    }

    @Override
    public Optional<List<Station>> getAllStations(){
        return Optional.of(stationRepository.values().stream().collect(Collectors.toList()));
    }

    @Override
    public Optional<Station> getByStationName(String stationName){
        return stationRepository.values().stream().filter(e->e.getStationName().equals(stationName)).findAny(); 
    }

    @Override
    public Optional<Station> getStationById(String stationId){
        if(stationRepository.containsKey(stationId)){
            return Optional.of(stationRepository.get(stationId));
        }
        return Optional.empty();
    }

}
