package com.rodrigo.redbeeweather.api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name="BOARD")
public class Board {

    @Id @GeneratedValue
    @Column (name = "BOARD_ID")
    private Long id;
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name="BOARD_LOCATION", joinColumns = {@JoinColumn (name= "BOARD_ID")}, inverseJoinColumns = { @JoinColumn(name="LOCATION_ID")})
    private Set<Location> locations;
    @Column
    private String name;

    public Board(String name) {
        this.locations = new HashSet<Location>();
        this.name = name;

    }

    public Board() {
        this.locations = new HashSet<Location>();
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public String getName() {
        return name;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (id != null ? !id.equals(board.id) : board.id != null) return false;
        return name != null ? name.equals(board.name) : board.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
