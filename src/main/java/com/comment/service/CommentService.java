package com.comment.service;

import java.util.List;
import java.util.Map;

import com.comment.bean.CommentDTO;

public interface CommentService {
	public void commentWrite(CommentDTO commentDTO);
	public List<CommentDTO> commentList(String seq_board);
	public void hitUpdate(long seq_board);
	public void commentDelete(long seq_comment);
	public void commentUpdate(Map<String, Object> map);	
}
