package com.gimeast.board.repository;

import com.gimeast.board.entity.Board;
import com.gimeast.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying
    @Query(value = "delete from Reply  r where r.board.bno = :bno")
    void deleteByBno(Long bno);

    List<Reply> getRepliesByBoardOrderByRno(Board board);

}
