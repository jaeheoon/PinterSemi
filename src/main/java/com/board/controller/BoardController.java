package com.board.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.board.bean.BoardDTO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;	
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public void boardWrite(@ModelAttribute BoardDTO boardDTO, @RequestParam("image") MultipartFile image) {		  
	    boardService.boardWrite(boardDTO, image);
	}

	
	@RequestMapping("/writeForm")
	public String boardWriteForm() {
		return "/board/boardWriteForm";
	}
	
	@RequestMapping("/updateForm")
	public String updateForm() {
		return "/board/boardUpdateForm";
	}		
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@ModelAttribute BoardDTO boardDTO,@RequestParam("image") MultipartFile image) {
		boardService.boardUpdate(boardDTO, image);
	}
	@RequestMapping("/delete")
	public void delete(@RequestParam("seq_board")String seq_board) {
		boardService.boardDelete(seq_board);
	}
	@RequestMapping("/boardView")
	public Model getBoardView(@RequestParam("seq_board")String seq_board, Model model) {
		BoardDTO boardDTO = boardService.getBoard(seq_board);
		model.addAttribute("boardDTO", boardDTO);
		return model;
	}
	
	// 얘는 그냥 탐색 페이지
	@RequestMapping("/searchPage")
	public String getBoardPagingList(@RequestParam(value = "page", required = false)String page, Model model) {
		List<BoardDTO>list =  boardService.getBoardPagingList(page);
		model.addAttribute(list);
		
		return "/searchPage/searchPage";
	}
	
	@RequestMapping("/memberPage")
	public Model getMemberBoardList(@RequestParam("seq_member")String seq_member, Model model) {
		List<BoardDTO>list = boardService.getMyBoardList(seq_member);
		model.addAttribute(list);
		return model;
	}
	// 얘는 검색해서 나온 페이지ㅣ
	@RequestMapping("/searchingPage")
	public Model getsearchBoardPagingList(@RequestParam("keyword")String keyword,@RequestParam(value = "page", required = false)String page, Model model) {
		List<BoardDTO>list =  boardService.searchBoardPagingList(keyword, page);
		model.addAttribute(list);
		return model;
	}


}
