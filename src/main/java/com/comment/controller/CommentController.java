package com.comment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comment.bean.CommentDTO;
import com.comment.service.CommentService;

@Controller
@RequestMapping("/pin/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/write")
	public void commentWrite(@ModelAttribute CommentDTO commentDTO) {
		commentService.commentWrite(commentDTO);
	}
	@RequestMapping("/commentList")
	public Model commentList(@RequestParam("seq_board")long seq_board, Model model){
		List<CommentDTO>list = commentService.commentList(seq_board);
		model.addAttribute("list", list);
		return model;
	}
	@RequestMapping("/hitUpdate")
	public void hitUpdate(@RequestParam("seq_board")long seq_board) {
		commentService.hitUpdate(seq_board);
	}
	@RequestMapping("/delete")
	public void commentDelete(@RequestParam("seq_comment")long seq_comment) {
		commentService.commentDelete(seq_comment);
	}
	@RequestMapping("/update")
	public void commentUpdate(@RequestParam("data")Map<String, Object> data) {
		commentService.commentUpdate(data);
	}
}
