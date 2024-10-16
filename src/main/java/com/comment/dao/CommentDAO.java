package com.comment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.comment.bean.CommentDTO;

@Mapper
public interface CommentDAO  {
	public void commentWrite(CommentDTO commentDTO);	
    public List<CommentDTO> commentList(long seq_board);
	public void hitUpdate(long seq_board);
	public void commentDelete(long seq_comment);
	public void commentUpdate(Map<String, Object> map);		
}
