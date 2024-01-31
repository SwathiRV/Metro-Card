package com.geektrust.backend.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.comparators.PassengerCountComparator;
import com.geektrust.backend.comparators.StationsNameComparator;
import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.PassengerCount;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.exceptions.NoPassengersFoundException;
import com.geektrust.backend.exceptions.NoStationsFoundException;
import com.geektrust.backend.repositories.IStationRepository;
import com.geektrust.backend.utility.StationUtility;
import com.geektrust.backend.utility.Utility;
import com.geektrust.backend.utility.ValidationUtility;

public class StationService implements IStationService{
    
    private final IStationRepository stationRepository;

    public StationService(IStationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Station create(String passengerType, String stationName, float pay, float discount){
        
        ValidationUtility.validate(passengerType, stationName);
        Optional<Station> optStation = stationRepository.getByStationName(stationName);
        if(!stationRepository.getByStationName(stationName).isPresent()){
            return stationRepository.save(StationUtility.newStation(stationName, passengerType, pay, discount));
        }
        else{
            return stationRepository.save(StationUtility.updateStation(optStation.get(), passengerType, pay, discount));
        }
    }

    public List<Station> getAllStationsSummary(){
        Optional<List<Station>> optStations = stationRepository.getAllStations();
        if(!optStations.isPresent()){
            throw new NoStationsFoundException("No Stations Found");
        }
        List<Station> stations = stationRepository.getAllStations().get();
        Collections.sort(stations, new StationsNameComparator());
        return stations;
    }


    public List<PassengerCount> getAllPassengerSummary(String stationId){
        Optional<Station> optStation = stationRepository.getStationById(stationId);
        if(!optStation.isPresent()){
            throw new NoPassengersFoundException("No Passenegers Found in Station");
        }
        List<PassengerCount> passengerCounts = optStation.get().getPassengerDetails().values().stream().collect(Collectors.toList());
        Collections.sort(passengerCounts, new PassengerCountComparator());
        return passengerCounts;
    }

    public void getPrintSummary(){
        for(Station station: getAllStationsSummary()){
            Utility.print(String.format(Constants.stationTemplate, station.getStationName(), (int)station.getCollectedAmount(), (int)station.getDiscountGiven())+ Constants.passengerPrintTemplate);
            printPassenger(station.getId());
        }
    }

    private void printPassenger(String id){
        getAllPassengerSummary(id).stream()
            .map(e-> String.format(Constants.passengerTemplate, e.getPassengerType() , e.getCount())).forEach(System.out::println);
    }
}
