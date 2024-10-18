package com.boardscrap.service;

import java.util.List;

import com.board.bean.BoardDTO;

public interface BoardScrapService {

	public void scrap(String seq_board, String seq_member);

	public List<BoardDTO> getScrapBoard(String seq_member);
}
