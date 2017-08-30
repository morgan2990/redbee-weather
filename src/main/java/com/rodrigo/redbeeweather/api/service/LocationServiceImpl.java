package com.rodrigo.redbeeweather.api.service;

import com.rodrigo.redbeeweather.api.model.Location;
import com.rodrigo.redbeeweather.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    public LocationRepository getRepository() {
        return repository;
    }

    public void setRepository(LocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Location retrieveLocation(String locationId) {
        return repository.getById(locationId);
    }

    @Override
    public void saveLocation(Location locationToSave) {
        repository.save(locationToSave);
    }

    @Override
    public void deleteLocation(String locationId) {
        repository.delete(locationId);
    }
}
