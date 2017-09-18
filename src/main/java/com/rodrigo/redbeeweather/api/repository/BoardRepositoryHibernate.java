package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Board;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


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
    public void delete(Board board) {
        entityManager.remove(board);
    }

    @Override
    public Board findByBoardId(Long boardId) {
        Board board = (Board) entityManager.createQuery("SELECT t from Board t where t.id= :boardId").setParameter("boardId", boardId).getSingleResult();
        return board;
    }

    @Override
    public void updateBoard(Board board) {
        entityManager.merge(board);
    }

    @Override
    public List<Board> getAllBoards() {
        List resultList = entityManager.createQuery("SELECT t from Board t", Board.class).getResultList();
        List<Board> boards = new ArrayList();
        boards.addAll(resultList);
        return boards;
    }
}
