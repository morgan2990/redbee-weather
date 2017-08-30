package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Board;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional
@Repository
@ComponentScan(basePackages = {"com.rodrigo.redbeeweather.api.model"})
public class BoardRepositoryHibernate implements BoardRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Board boardToSave) {
        entityManager.persist(boardToSave);
    }

    @Override
    public void delete(String boardId) {
        entityManager.remove(find(boardId));
    }

    @Override
    public Board find(String boardId) {
        return entityManager.find(Board.class, boardId);
    }
}
