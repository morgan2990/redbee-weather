package com.rodrigo.redbeeweather.api.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BOARD")
public class Board {

    @Id @GeneratedValue
    @Column(name="board_id")
    private Long boardId;
    @OneToMany
    @JoinTable(name="BOARD_LOCATION", joinColumns = {@JoinColumn (name= "BOARD_ID")}, inverseJoinColumns = { @JoinColumn(name="LOCATION_ID")})
    private Set<Location> locations;
    @Column
    private String name;

    public Board(Long boardId, String name) {
        this.boardId = boardId;
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

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public void setName(String name) {
        this.name = name;
    }
}
