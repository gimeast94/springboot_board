package com.gimeast.board.controller;

import com.gimeast.board.dto.BoardDto;
import com.gimeast.board.dto.PageRequestDto;
import com.gimeast.board.dto.PageResultDto;
import com.gimeast.board.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.IModel;

@Controller
@RequestMapping("/board")
@Log4j2
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public void list(@ModelAttribute PageRequestDto pageRequestDto, Model model) {
        PageResultDto<BoardDto, Object[]> list = boardService.getList(pageRequestDto);

        model.addAttribute("result", list);
    }






}
