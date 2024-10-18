package com.boardscrap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.bean.BoardDTO;

@Mapper
public interface BoardScrapDAO {
	public void scrap(BoardDTO boardDTO);
	public List<Long> getScrapBoard(String seq_member);
}
