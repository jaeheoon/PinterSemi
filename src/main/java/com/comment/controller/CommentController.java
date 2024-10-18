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
		} catch (Exception e) {
			result.put("status", "error");
			result.put("message", "댓글 등록 실패");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/commentList", method = RequestMethod.POST)
	@ResponseBody
	public List<CommentDTO> commentList(@RequestParam("seq_board") String seq_board) {
		return commentService.commentList(seq_board);
	}

	@RequestMapping(value = "/hitUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void hitUpdate(@RequestParam("seq_board") long seq_board) {
		commentService.hitUpdate(seq_board);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, String> commentDelete(@RequestParam("seq_comment") long seq_comment) {
		Map<String, String> result = new HashMap<>();
		try {
			commentService.commentDelete(seq_comment);
			result.put("status", "success");
			result.put("message", "댓글 삭제 완료");
		} catch (Exception e) {
			result.put("status", "error");
			result.put("message", "댓글 삭제 실패");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String, String> commentUpdate(@RequestParam("seq_comment") String seqComment,
			@RequestParam("seq_board") String seqBoard, @RequestParam("commentContent") String commentContent) {
		Map<String, String> result = new HashMap<>();

		try {
			Map<String, Object> data = new HashMap<>();
			data.put("seq_comment", seqComment);
			data.put("seq_board", seqBoard);
			data.put("commentContent", commentContent);

			commentService.commentUpdate(data);
			result.put("status", "success");
			result.put("message", "댓글 수정 완료");
		} catch (Exception e) {
			result.put("status", "error");
			result.put("message", "댓글 수정 실패");
			e.printStackTrace();
		}

		return result;
	}

}
