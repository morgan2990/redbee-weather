package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Location;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

public class LocationRepositoryInMap implements LocationRepository {

    private Map<String, Location> locationMap;

    public LocationRepositoryInMap(){
        this.locationMap= new HashMap<String, Location>();
    }


    @Override
    public Location getById(String locationId) {
        return locationMap.get(locationId);
    }

    @Override
    public void save(Location locationToSave) {
        locationMap.put(locationToSave.getLocationId().toString(), locationToSave);
    }

    @Override
    public void delete(String locationId) {
        locationMap.remove(locationId);

    }
}
