package com.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comment.bean.CommentDTO;
import com.comment.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/write")
	@ResponseBody
	public Map<String, String> commentWrite(@ModelAttribute CommentDTO commentDTO) {
		Map<String, String> result = new HashMap<>();
		try {
			commentService.commentWrite(commentDTO);
			result.put("status", "success");
			result.put("message", "댓글 등록 완료");
		}
		catch (Exception e) {
			result.put("status", "error");
			result.put("message", "이미지 등록 실패");
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value="/commentList", method=RequestMethod.POST)
	@ResponseBody
	public List<CommentDTO> commentList(@RequestParam("seq_board") String seq_board) {
		List<CommentDTO> list  = commentService.commentList(seq_board);
		for(CommentDTO dto:list) {
			System.out.println(dto.getCommentContent());
		}
	    return list;
	}
	@RequestMapping(value="/hitUpdate", method=RequestMethod.POST)
	@ResponseBody
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
