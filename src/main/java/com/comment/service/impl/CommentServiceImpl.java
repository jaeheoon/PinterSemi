package com.comment.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comment.bean.CommentDTO;
import com.comment.dao.CommentDAO;
import com.comment.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO commentDAO;
	@Override
	public void commentWrite(CommentDTO commentDTO) {
		commentDAO.commentWrite(commentDTO);		
	}

	@Override
	public List<CommentDTO> commentList(String seq_board) {
		return commentDAO.commentList(Long.parseLong(seq_board));
	}

	@Override
	public void hitUpdate(long seq_board) {
		commentDAO.hitUpdate(seq_board);
		
	}

	@Override
	public void commentDelete(long seq_comment) {
		commentDAO.commentDelete(seq_comment);
		
	}

	@Override
	public void commentUpdate(Map<String, Object> map) {
		commentDAO.commentUpdate(map);		
	}

}
