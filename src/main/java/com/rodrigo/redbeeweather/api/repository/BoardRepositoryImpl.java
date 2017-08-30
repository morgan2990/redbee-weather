package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Board;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;



public class BoardRepositoryImpl implements BoardRepository{

    private Map<String,Board> boardMap;

    public BoardRepositoryImpl() {
        this.boardMap=new HashMap<String, Board>();
        }

    @Override
    public void save(Board boardToSave) {
        boardMap.put(boardToSave.getBoardId().toString(), boardToSave);

    }

    @Override
    public void delete(String boardId) {
        Board boardToRemove = boardMap.get(boardId);
        boardMap.remove(boardId);

    }

    @Override
    public Board find(String boardId) {
        return boardMap.get(boardId);
    }
}
