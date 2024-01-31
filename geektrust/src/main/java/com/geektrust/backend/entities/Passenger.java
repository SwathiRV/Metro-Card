package com.geektrust.backend.entities;

import com.geektrust.backend.enums.CheckStatus;
import com.geektrust.backend.enums.PassengerType;

public class Passenger extends BaseEntity{

    private final PassengerType passengerType;
    private final String metroCardId;
    private CheckStatus checkStatus;
    private String stationId;

    public Passenger(String id, PassengerType passengerType, String metroCardId, CheckStatus checkStatus,
            String stationId) {
        this.id = id;
        this.passengerType = passengerType;
        this.metroCardId = metroCardId;
        this.checkStatus = checkStatus;
        this.stationId = stationId;
    }

    public Passenger(PassengerType passengerType, String metroCardId, CheckStatus checkStatus,
            String stationId) {
        this.passengerType = passengerType;
        this.metroCardId = metroCardId;
        this.checkStatus = checkStatus;
        this.stationId = stationId;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public String getMetroCardId() {
        return metroCardId;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public String getStationId() {
        return stationId;
    }
/*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Passenger other = (Passenger) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
*/
    @Override
    public String toString() {
        return "Passenger [id=" + id + " checkStatus=" + checkStatus + ", metroCardId=" + metroCardId
                + ", passengerType=" + passengerType + ", stationId=" + stationId + "]";
    }
    
    

}
