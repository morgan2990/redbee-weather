package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.model.Location;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;


@Transactional
@Repository
@ComponentScan(basePackages = {"com.rodrigo.redbeeweather.api.model"})

public class LocationRepositoryHibernate implements LocationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Location getById(Long locationId) {
        Location location = (Location) entityManager.createQuery("SELECT t from Location t where t.id= :locationId").setParameter("locationId", locationId).getSingleResult();
        return location;
    }

    @Override
    public void save(Location locationToSave) {
        entityManager.persist(locationToSave);
    }

    @Override
    public void delete(Long locationId) {
        Location location = getById(locationId);
        for (Board eachBoard: location.getBoards()) {
            eachBoard.getLocations().remove(location);
        }
        entityManager.remove(location);
    }

    @Override
    public Set<Location> getAll() {
        Set<Location> locations = new HashSet<>();
        locations.addAll(entityManager.createQuery("from Location").getResultList());
        return locations;

    }

    @Override
    public void updateLocation(Location locationToUpdate) {
        entityManager.merge(locationToUpdate);
    }
}
