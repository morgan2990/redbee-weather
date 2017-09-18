package com.rodrigo.redbeeweather.api.controller;


import com.rodrigo.redbeeweather.api.model.Location;
import com.rodrigo.redbeeweather.api.service.LocationService;
import com.rodrigo.redbeeweather.api.service.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    public LocationService getLocationService() {
        return locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(method= RequestMethod.GET, value="/{locationId}")
    public Location retrieveLocation (@PathVariable String locationId){
        return locationService.retrieveLocation(Long.valueOf(locationId));
    }

    @RequestMapping(method= RequestMethod.GET)
    public Set<Location> retrieveAllLocations (){
        return locationService.retrieveAllLocations();
    }

    @RequestMapping(method= RequestMethod.POST)
    public Location saveLocation (@RequestBody Location locationToSave){
        return locationService.saveLocation(locationToSave);
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public Location deleteLocation (@PathVariable String locationId){
        return locationService.deleteLocation(Long.valueOf(locationId));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public Location updateLocation (@RequestBody Location locationToUpdate){
        return locationService.updateLocation(locationToUpdate);
    }
}
