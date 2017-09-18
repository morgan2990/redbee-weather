package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Location;

import java.util.Set;

public interface LocationRepository {
    Location getById(Long locationId);

    void save(Location locationToSave);

    void delete(Long locationId);

    Set<Location> getAll();

    void updateLocation (Location locationToUpdate);

}
