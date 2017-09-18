package com.rodrigo.redbeeweather.api.service;

import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.model.Location;
import com.rodrigo.redbeeweather.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private WeatherService weatherService;

    @Override
    public Location retrieveLocation(Long locationId) {
        Location location = repository.getById(locationId);
        try {
            weatherService.populateWeatherCondition(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }


    public Location retrieveLocation(Long requestedBy, Long locationId) {
        Location location = repository.getById(locationId);
        try {
            weatherService.populateWeatherCondition(requestedBy, location);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public Location saveLocation(Location locationToSave) {
        repository.save(locationToSave);
        return locationToSave;
    }

    @Override
    public Location deleteLocation(Long locationId) {
        Location locationToDelete= repository.getById(locationId);
        for (Board eachBoard: locationToDelete.getBoards()) {
            boardService.removeLocationFromBoard(locationToDelete, eachBoard);
            this.removeBoardFromLocation(eachBoard, locationToDelete);
        }
        repository.delete(locationId);
        return locationToDelete;
    }

    @Override
    public void removeBoardFromLocation(Board eachBoard, Location locationToDelete) {
        locationToDelete.getBoards().remove(eachBoard);
        updateLocation(locationToDelete);
    }

    @Override
    public Set<Location> getAllLocations() {
        return repository.getAll();
    }

    @Override
    public Location updateLocation(Location locationToUpdate) {

        repository.updateLocation(locationToUpdate);
        return locationToUpdate;
    }

    @Override
    public Set<Location> retrieveAllLocations() {
        Set<Location> locations = repository.getAll();
        for (Location eachLocation: locations
             ) {
            try {
                weatherService.populateWeatherCondition(eachLocation);
                eachLocation.setBoards(new HashSet<>());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        return locations;
    }
}
