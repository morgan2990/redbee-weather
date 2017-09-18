package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Board;

import java.util.List;


public interface BoardRepository {
    void save(Board boardToSave);

    void delete(Board boardId);

    Board findByBoardId(Long boardId);

    void updateBoard(Board board);

    List<Board> getAllBoards();
}
