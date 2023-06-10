package com.gimeast.board.service.impl;

import com.gimeast.board.dto.BoardDto;
import com.gimeast.board.dto.PageRequestDto;
import com.gimeast.board.dto.PageResultDto;
import com.gimeast.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    @Rollback(value = false)
    void 글등록() throws Exception {
        //given
        BoardDto dto = BoardDto.builder()
                .title("test title....")
                .content("content....")
                .memberEmail("user11@gmail.com")
                .build();

        //when
        Long bno = boardService.register(dto);

        //then
        System.out.println("bno : " + bno);
    }

    @Test
    void 목록조회() throws Exception {
        //given
        PageRequestDto pageRequestDto = new PageRequestDto();

        //when
        PageResultDto<BoardDto, Object[]> result = boardService.getList(pageRequestDto);

        //then
        for (BoardDto dto : result.getDtoList()) {
            System.out.println(dto);
        }

    }

    @Test
    void 상세조회() throws Exception {
        //given
        Long bno = 100L;

        //when
        BoardDto boardDto = boardService.get(bno);

        //then
        System.out.println(boardDto);

    }

}