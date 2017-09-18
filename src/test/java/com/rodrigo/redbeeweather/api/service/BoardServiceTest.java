package com.rodrigo.redbeeweather.api.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import com.rodrigo.redbeeweather.api.model.Board;
import com.rodrigo.redbeeweather.api.repository.BoardRepository;
import com.rodrigo.redbeeweather.api.repository.BoardRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


/*@RunWith(SpringRunner.class)
@SpringBootTest*/

public class BoardServiceTest {
/*
    @MockBean
    private BoardRepository boardRepository;


    private BoardService service;

    @Before
    public void setup(){
        service=new BoardServiceImpl();
        ((BoardServiceImpl)service).setBoardRepository(boardRepository);
    }


    @Test
    public void shouldRetrieveBoard (){
        given(boardRepository.findByBoardId(anyLong()));
        Board testBoard = new Board(1L, "Rodrigo");
        service.saveBoard(testBoard);
        assertThat(service.retrieveBoard(1L)).isEqualTo(testBoard);

    }*/
}
