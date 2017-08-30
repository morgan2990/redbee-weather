package com.rodrigo.redbeeweather.api.service;

import com.rodrigo.redbeeweather.api.model.Board;

public interface BoardService {
    void deleteBoard(String boardId);

    void saveBoard(Board boardToSave);

    Board retrieveBoard(String boardId);

    Board addLocationToBoard(String locationId, String boardId);
}
