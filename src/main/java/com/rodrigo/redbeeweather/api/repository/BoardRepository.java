package com.rodrigo.redbeeweather.api.repository;

import com.rodrigo.redbeeweather.api.model.Board;


public interface BoardRepository {
    void save(Board boardToSave);

    void delete(String boardId);

    Board find(String boardId);

}
