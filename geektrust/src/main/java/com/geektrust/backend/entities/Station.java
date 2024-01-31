package com.geektrust.backend.entities;

import java.util.HashMap;

public class Station extends BaseEntity{
    private final String stationName;
    private float collectedAmount;
    private float discountGiven;
    private HashMap<String, PassengerCount> passengerDetails;

    public Station(String id, String stationName, float collectedAmount, float discountGiven,
            HashMap<String, PassengerCount> passengerDetails) {
        this.id = id;
        this.stationName = stationName;
        this.collectedAmount = collectedAmount;
        this.discountGiven = discountGiven;
        this.passengerDetails = passengerDetails;
    }

    public Station(String stationName, float collectedAmount, float discountGiven,
            HashMap<String, PassengerCount> passengerDetails) {
        this.stationName = stationName;
        this.collectedAmount = collectedAmount;
        this.discountGiven = discountGiven;
        this.passengerDetails = passengerDetails;
    }

    public String getStationName() {
        return stationName;
    }

    public float getCollectedAmount() {
        return collectedAmount;
    }

    public float getDiscountGiven() {
        return discountGiven;
    }

    public HashMap<String, PassengerCount> getPassengerDetails() {
        return passengerDetails;
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
        Station other = (Station) obj;
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
        return "Station [id=" + id + " collectedAmount=" + collectedAmount + ", discountGiven=" + discountGiven
                + ", passengerDetails=" + passengerDetails + ", stationName=" + stationName + "]";
    }

}
