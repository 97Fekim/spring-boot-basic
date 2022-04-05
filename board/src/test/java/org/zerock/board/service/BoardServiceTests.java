package org.zerock.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;

import javax.transaction.Transactional;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        BoardDTO dto = BoardDTO.builder()
                .title("Test....")
                .content("Test....")
                .writerEmail("user4@gmail.com")
                .build();
        Long bno = boardService.register(dto);
    }

    @Test
    public void testGetList(){
        PageRequestDTO requestDTO = new PageRequestDTO();   // default 0,10

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(requestDTO);

        for(BoardDTO boardDTO : result.getDtoList()){
            System.out.println(boardDTO);
        }
    }

    @Test
    public void testGet(){
        System.out.println(boardService.get(50L));
    }

    @Test
    public void testDelete(){
        boardService.removeWithReplies(97L);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("modified title")
                .content("modified content")
                .build();
        boardService.modify(boardDTO);
    }
}
