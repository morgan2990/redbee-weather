package com.rodrigo.redbeeweather.api.controller;

import com.rodrigo.redbeeweather.api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boardLocation/{boardId}")
public class BoardLocationController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(method= RequestMethod.POST)
    public void addLocationToBoard (@RequestBody String locationId, @PathVariable String boardId){
        boardService.addLocationToBoard(Long.valueOf(locationId), Long.valueOf(boardId));

    }

    @RequestMapping(method= RequestMethod.DELETE)
    public void removeLocationFromBoard (@RequestBody String locationId, @PathVariable String boardId){
        boardService.removeLocationFromBoard(Long.valueOf(locationId), Long.valueOf(boardId));

    }

    
}
