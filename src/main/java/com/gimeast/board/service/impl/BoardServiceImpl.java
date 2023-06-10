package com.gimeast.board.service.impl;

import com.gimeast.board.dto.BoardDto;
import com.gimeast.board.dto.PageRequestDto;
import com.gimeast.board.dto.PageResultDto;
import com.gimeast.board.entity.Board;
import com.gimeast.board.entity.Member;
import com.gimeast.board.repository.BoardRepository;
import com.gimeast.board.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.function.Function;

@Service
@Transactional
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Long register(BoardDto dto) {
        log.info("[register] dto: " + dto);

        Board entity = dtoToEntity(dto);

        boardRepository.save(entity);

        return entity.getBno();
    }

    @Override
    public PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {

        Function<Object[], BoardDto> fn = (en -> entityToDto((Board)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDto.getPageable(Sort.by("bno").descending()));

        return new PageResultDto<>(result, fn);
    }

    @Override
    public BoardDto get(Long bno) {

        Object board = boardRepository.getBoardByBno(bno);

        Object[] arr = (Object[]) board;

        BoardDto result = entityToDto((Board) arr[0], (Member) arr[1], (Long) arr[2]);

        return result;
    }
}
