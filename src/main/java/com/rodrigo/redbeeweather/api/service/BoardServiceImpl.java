package com.rodrigo.redbeeweather.api.service;

import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.model.Location;
import com.rodrigo.redbeeweather.api.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private LocationService locationService;

    public BoardRepository getBoardRepository() {
        return boardRepository;
    }

    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public LocationService getLocationService() {
        return locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void deleteBoard(String boardId) {
        boardRepository.delete(boardId);
    }

    @Override
    public void saveBoard(Board boardToSave) {
        boardRepository.save(boardToSave);
    }

    @Override
    public Board retrieveBoard(String boardId) {
        return boardRepository.find(boardId);
    }

    @Override
    public Board addLocationToBoard(String locationId, String boardId) {
        Location locationToAdd = locationService.retrieveLocation(locationId);
        Board destinationBoard = boardRepository.find(boardId);
        destinationBoard.getLocations().add(locationToAdd);
        return destinationBoard;
    }
}
