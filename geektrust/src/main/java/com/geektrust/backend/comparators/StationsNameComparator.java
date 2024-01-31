package com.geektrust.backend.comparators;

import java.util.Comparator;
import com.geektrust.backend.entities.Station;

public class StationsNameComparator implements Comparator<Station>{

    @Override
    public int compare(Station station1, Station station2) {
        return station2.getStationName().compareTo(station1.getStationName());
    }
  
}
