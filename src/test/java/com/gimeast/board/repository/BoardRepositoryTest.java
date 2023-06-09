package com.gimeast.board.repository;

import com.gimeast.board.entity.Board;
import com.gimeast.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void 더미_데이터_생성() throws Exception {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder()
                    .email("user"+i + "@gmail.com")
                    .build();

            Board board = Board.builder()
                    .title("title..."+i)
                    .content("content..."+i)
                    .member(member)
                    .build();

            boardRepository.save(board);
        });

    }

    @Test
    void 조회1() throws Exception {
        //given
        Optional<Board> result = boardRepository.findById(100L);

        //when
        Board board = result.get();

        //then
        System.out.println(board);
        System.out.println(board.getMember());

    }





}