package com.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.board.bean.BoardDTO;
import com.board.dao.BoardDAO;
import com.board.service.BoardService;

public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	@Override
	public void boardWrite(BoardDTO boardDTO) {
		boardDAO.boardWrite(boardDTO);		
	}

	@Override
	public List<BoardDTO> getBoardList() {
		boardDAO.getBoardList();
		return null;
	}

	@Override
	public List<BoardDTO> getBoardPagingList(int startRow, int endRow) {
		boardDAO.getBoardPagingList(startRow, endRow);
		return null;
	}

	@Override
	public List<BoardDTO> getBoardPopularPagingList(int startRow, int endRow) {
		return boardDAO.getBoardPopularPagingList(startRow, endRow);
	}

	@Override
	public BoardDTO getBoard(long seq_board) {
		return boardDAO.getBoard(seq_board);
	}

	@Override
	public void boardUpdate(BoardDTO boardDTO) {
		boardDAO.boardUpdate(boardDTO);
		
	}

	@Override
	public void boardDelete(long seq_board) {
		boardDAO.boardDelete(seq_board);		
	}

	@Override
	public void boardUpdateNotImage(BoardDTO boardDTO) {
		boardDAO.boardUpdateNotImage(boardDTO);
		
	}

	@Override
	public List<BoardDTO> getMyBoardList(long seq_member) {
		return boardDAO.getMyBoardList(seq_member);
	}

	@Override
	public List<BoardDTO> searchBoardPagingList(Map<String, Object> map) {		
		return boardDAO.searchBoardPagingList(map);
	}

}
