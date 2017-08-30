package com.rodrigo.redbeeweather.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private Long locationId;
    @Column
    private String name;
    @Column
    private String woeId; //Yahoo weather parameter for location


    public Location(Long locationId, String name, String woeId) {
        this.locationId = locationId;
        this.name = name;
        this.woeId =woeId;

    }

    public Location() {

    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWoeId(String woeId) {
        this.woeId = woeId;
    }

    public String getName() {
        return name;
    }

    public String getWoeId() {
        return woeId;
    }

    public Long getLocationId() {
        return locationId;
    }
}
