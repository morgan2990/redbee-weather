package com.rodrigo.redbeeweather.api.service;

import com.rodrigo.redbeeweather.api.model.Location;

import java.io.IOException;
import java.util.Set;

public interface WeatherService {

    void populateWeatherCondition (Long requestedBy, Location location) throws IOException;

    void populateWeatherCondition (Location location) throws IOException;

}
