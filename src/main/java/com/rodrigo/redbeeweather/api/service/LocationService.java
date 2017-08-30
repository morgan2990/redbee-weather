package com.rodrigo.redbeeweather.api.service;

import com.rodrigo.redbeeweather.api.model.Location;

public interface LocationService {
    Location retrieveLocation(String locationId);

    void saveLocation(Location locationToSave);

    void deleteLocation(String locationId);

}
