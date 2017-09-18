package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Location;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LocationRepositoryInMap implements LocationRepository {

    private Map<String, Location> locationMap;

    public LocationRepositoryInMap(){
        this.locationMap= new HashMap<String, Location>();
    }


    @Override
    public Location getById(Long locationId) {
        return locationMap.get(locationId);
    }

    @Override
    public void save(Location locationToSave) {
        locationMap.put(locationToSave.getId().toString(), locationToSave);
    }

    @Override
    public void delete(Long locationId) {
        locationMap.remove(locationId.toString());

    }

    @Override
    public Set<Location> getAll() {
        Set <Location> locationSet = new HashSet<>();
        for (Map.Entry eachEntry: locationMap.entrySet()
             ) {
            locationSet.add((Location) eachEntry.getValue());
        }

        return locationSet;
    }

    @Override
    public void updateLocation(Location locationToUpdate) {
        locationMap.remove(locationToUpdate.getId().toString());
        locationMap.put(locationToUpdate.getId().toString(), locationToUpdate);

    }
}
