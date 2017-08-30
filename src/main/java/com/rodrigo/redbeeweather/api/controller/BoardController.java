package com.rodrigo.redbeeweather.api.controller;


import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/{boardId}")
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardService getBoardService() {
        return boardService;
    }

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public Board retrieveBoard (@PathVariable String boardId){
        return boardService.retrieveBoard(boardId);
    }

    @RequestMapping(method= RequestMethod.POST)
    public void saveBoard (@RequestBody Board boardToSave){
        boardService.saveBoard(boardToSave);
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public void deleteBoard (@PathVariable String boardId){
        boardService.deleteBoard(boardId);
    }

    @RequestMapping(method= RequestMethod.PUT)
    public Board addLocationToBoard (@RequestBody String locationId, @PathVariable String boardId){
        return boardService.addLocationToBoard(locationId, boardId);

    }
}
