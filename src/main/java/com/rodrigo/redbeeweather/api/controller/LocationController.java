package com.rodrigo.redbeeweather.api.controller;


import com.rodrigo.redbeeweather.api.model.Location;
import com.rodrigo.redbeeweather.api.service.LocationService;
import com.rodrigo.redbeeweather.api.service.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location/{locationId}")
public class LocationController {

    @Autowired
    private LocationService locationService;

    public LocationService getLocationService() {
        return locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public Location retrieveLocation (@PathVariable String locationId){
        return locationService.retrieveLocation(locationId);
    }

    @RequestMapping(method= RequestMethod.POST)
    public void saveLocation (@RequestBody Location locationToSave){
        locationService.saveLocation(locationToSave);
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public void deleteLocation (@PathVariable String LocationId){
        locationService.deleteLocation(LocationId);
    }
}
