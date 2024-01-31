package com.geektrust.backend.utility;

import com.geektrust.backend.constants.Constants;
import com.geektrust.backend.enums.PassengerType;
import com.geektrust.backend.enums.StationType;
import com.geektrust.backend.exceptions.NoPassengersFoundException;
import com.geektrust.backend.exceptions.NoStationsFoundException;

public class ValidationUtility {
    
    private static void validatePassengerType(String passengerType){
        try{
            PassengerType.valueOf(passengerType);
        }catch(Exception e){
            throw new NoPassengersFoundException("Passenger can only be of type ADULT, SENIOR CITIZEN, KID");
        }
    }

    private static void validateStation(String stationName){

        try{StationType.valueOf(stationName);}
        catch(Exception e){ throw new NoStationsFoundException("Station is not registered!");}
        
    }

    public static void validate(String passengerType, String stationName) {
        if(passengerType==Constants.NULL && stationName==Constants.NULL){
            throw new NoPassengersFoundException("Please provide passenger type and station name!!");
        }
        if(passengerType==Constants.NULL){
            throw new NoPassengersFoundException("Please provide passenger type!!");
        }
        else if(stationName==Constants.NULL){
            throw new NoStationsFoundException("Please provide station type!!");
        }

        validatePassengerType(passengerType);
        validateStation(stationName);
    }
}
