package com.rodrigo.redbeeweather.api.service;

import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.model.Location;
import com.rodrigo.redbeeweather.api.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private WeatherService weatherService;

    @Override
    public Board deleteBoard(Long boardId) {
        Board boardToDelete = retrieveBoard(boardId);
        boardRepository.delete(boardToDelete);
        return boardToDelete;
    }

    @Override
    public Board saveBoard(Board boardToSave) {
        boardRepository.save(boardToSave);
        return boardToSave;
    }

    @Override
    public Board retrieveBoard(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId);
        populateWeatherConditions(boardId, board);
        return board;
    }

    private void populateWeatherConditions(Long boardId, Board board) {
        for (Location eachLocation:board.getLocations()
             ) {
            try {
                weatherService.populateWeatherCondition(boardId, eachLocation);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Board> retrieveAllBoards(){
        List <Board> boards = boardRepository.getAllBoards();
        return boards;
    }

    @Override
    public void addLocationToBoard(Long locationId, Long boardId) {
        Location locationToAdd = locationService.retrieveLocation(locationId);
        Board destinationBoard = boardRepository.findByBoardId(boardId);
        destinationBoard.getLocations().add(locationToAdd);
        updateBoard(destinationBoard);

    }

    @Override
    public void removeLocationFromBoard(Location locationToDelete, Board eachBoard) {
        eachBoard.getLocations().remove(locationToDelete);
        boardRepository.updateBoard(eachBoard);
    }


    @Override
    public Board updateBoard(Board board){
        boardRepository.updateBoard(board);
        return board;
    }

    @Override
    public void removeLocationFromBoard(Long locationId, Long boardId) {
        Location locationToRemove = locationService.retrieveLocation(locationId);
        Board destinationBoard = boardRepository.findByBoardId(boardId);
        destinationBoard.getLocations().remove(locationId);
        updateBoard(destinationBoard);
    }
}
