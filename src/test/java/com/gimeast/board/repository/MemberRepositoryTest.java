package com.gimeast.board.repository;

import com.gimeast.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    
    @Test
    void 더미_데이터_생성() throws Exception {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user"+i + "@gmail.com")
                    .password("1111")
                    .name("name"+i)
                    .build();
            memberRepository.save(member);
        });

    }



}