package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class BoardRepositoryImpl implements BoardRepository{

    private Map<String,Board> boardMap;

    public BoardRepositoryImpl() {
        this.boardMap=new HashMap<String, Board>();
        }

    @Override
    public void save(Board boardToSave) {
        boardMap.put(boardToSave.getId().toString(), boardToSave);

    }

    @Override
    public void delete(Board boardId) {
        boardMap.remove(boardId.getId());

    }

    @Override
    public Board findByBoardId(Long boardId) {
        return boardMap.get(boardId);
    }

    @Override
    public void updateBoard(Board board) {

    }

    @Override
    public List<Board> getAllBoards() {
        return new ArrayList<>();
    }
}
