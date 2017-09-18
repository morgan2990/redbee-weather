package com.rodrigo.redbeeweather.api.controller;


import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardService getBoardService() {
        return boardService;
    }

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "{boardId}")
    public Board retrieveBoard(@PathVariable String boardId) {
        return boardService.retrieveBoard(Long.valueOf(boardId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Board saveBoard(@RequestBody Board boardToSave) {
        return boardService.saveBoard(boardToSave);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="{boardId}")
    public Board deleteBoard(@PathVariable String boardId) {
        return boardService.deleteBoard(Long.valueOf(boardId));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Board updateBoard(@RequestBody Board boardToSave) {
        return boardService.updateBoard(boardToSave);

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Board> retrieveAllBoards() {
        return boardService.retrieveAllBoards();
    }

}


