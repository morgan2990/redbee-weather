package com.rodrigo.redbeeweather.api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "LOCATION")
public class Location {

    @Id
    @GeneratedValue
    @Column (name = "LOCATION_ID")
    private Long id;
    @Column
    protected String name;
    @Column
    private String woeId; //Yahoo weather parameter for location
    @ManyToMany(  mappedBy = "locations")
    private Set<Board> boards;
    @Transient
    private WeatherCondition weatherCondition;


    public Location(Long locationId, String name, String woeId) {
        this.name = name;
        this.woeId =woeId;
        this.boards=new HashSet<Board>();
    }

    public Location() {
        this.boards=new HashSet<Board>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Board> getBoards() {
        return boards;
    }

    public void setBoards(Set<Board> boards) {
        this.boards = boards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (id != null ? !id.equals(location.id) : location.id != null) return false;
        if (name != null ? !name.equals(location.name) : location.name != null) return false;
        return woeId != null ? woeId.equals(location.woeId) : location.woeId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (woeId != null ? woeId.hashCode() : 0);
        return result;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}
