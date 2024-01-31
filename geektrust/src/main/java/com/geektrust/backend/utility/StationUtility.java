package com.geektrust.backend.utility;

import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.entities.PassengerCount;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.enums.CheckStatus;
import com.geektrust.backend.enums.PassengerType;
import com.geektrust.backend.exceptions.NoStationsFoundException;
import java.util.HashMap;
import java.util.Optional;
public class StationUtility {
    
    public static Station updateStation(Station station, String passengerType, float pay, float discount){
            HashMap<String, PassengerCount> passengerDetails = station.getPassengerDetails();
            PassengerCount passengerCount = passengerDetails.get(passengerType);
            if(passengerCount==Constants.NULL){
                passengerCount = new PassengerCount(PassengerType.valueOf(passengerType), Constants.ONE);
            }else{
                passengerCount.setCount(passengerCount.getCount() + Constants.ONE);
            }
            passengerDetails.put(passengerCount.getPassengerType().toString(), passengerCount);
            return new Station(station.getId(), 
            station.getStationName(), station.getCollectedAmount() + pay, station.getDiscountGiven() + discount, 
            passengerDetails);
    }

    public static Station newStation(String stationName, String passengerType, float pay, float discount){
            PassengerCount passengerCount = new PassengerCount(PassengerType.valueOf(passengerType), Constants.ONE);
            HashMap<String, PassengerCount> passengerDetails = new HashMap<>();
            passengerDetails.put(passengerCount.getPassengerType().toString(), passengerCount);
            return new Station(stationName, pay, discount, passengerDetails);
    }

    public static CheckStatus checkTheStatus(Optional<Station> optStation, String stationName, CheckStatus checkStatus){
        if(!optStation.isPresent()){
            throw new NoStationsFoundException("No Station Found");
        }

        if(checkStatus == CheckStatus.CHECK_IN && !optStation.get().getStationName().equals(stationName)){
            checkStatus = CheckStatus.CHECK_OUT;
        }
        else if(checkStatus==CheckStatus.CHECK_OUT){
            checkStatus = CheckStatus.CHECK_IN;
        }
        
        return checkStatus;
    }
}
