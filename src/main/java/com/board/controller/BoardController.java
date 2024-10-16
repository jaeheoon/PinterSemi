package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.bean.BoardDTO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/writeForm")
	public String boardWriteForm() {
		return "/board/writeForm";
	}
	
	@RequestMapping("/write")
	public void boardWrite(@RequestParam("data")BoardDTO boardDTO) {
		boardService.boardWrite(boardDTO);
	}
	
	@RequestMapping("/boardList")
	public Model getBoardList(Model model){
		List<BoardDTO>list = boardService.getBoardList();	
		model.addAttribute("list",list);
		return model;
	}
	
	@RequestMapping("/searchPage")
	public String searchPage() {
		return "/searchPage/searchPage";
	}

}
