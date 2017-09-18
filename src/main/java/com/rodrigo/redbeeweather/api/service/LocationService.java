package com.rodrigo.redbeeweather.api.service;

import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.model.Location;

import java.util.Set;

public interface LocationService {
    Location retrieveLocation(Long locationId);

    Location saveLocation(Location locationToSave);

    Location deleteLocation(Long locationId);

    void removeBoardFromLocation(Board eachBoard, Location locationToDelete);

    Set<Location> getAllLocations();

    Location updateLocation(Location locationToUpdate);

    Set<Location> retrieveAllLocations();
}
