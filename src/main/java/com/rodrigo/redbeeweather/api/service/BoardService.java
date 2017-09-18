package com.rodrigo.redbeeweather.api.service;

import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.model.Location;

import java.util.List;

public interface BoardService {
    Board deleteBoard(Long boardId);

    Board saveBoard(Board boardToSave);

    Board retrieveBoard(Long boardId);

    void addLocationToBoard(Long locationId, Long boardId);

    void removeLocationFromBoard(Location locationToDelete, Board eachBoard);

    Board updateBoard(Board board);

    void removeLocationFromBoard(Long locationId, Long boardId);

    List<Board> retrieveAllBoards();
}
