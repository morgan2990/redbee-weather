package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Location;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional
@Repository
@ComponentScan(basePackages = {"com.rodrigo.redbeeweather.api.model"})

public class LocationRepositoryHibernate implements LocationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Location getById(String locationId) {
        return entityManager.find(Location.class, locationId);
    }

    @Override
    public void save(Location locationToSave) {
        entityManager.persist(locationToSave);
    }

    @Override
    public void delete(String locationId) {
        entityManager.remove(getById(locationId));
    }
}
