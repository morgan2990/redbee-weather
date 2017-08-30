package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Location;

public interface LocationRepository {
    Location getById(String locationId);

    void save(Location locationToSave);

    void delete(String locationId);
}
