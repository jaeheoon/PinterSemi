package com.board.service;

import java.util.List;
import java.util.Map;

import com.board.bean.BoardDTO;

public interface BoardService {
	public void boardWrite(BoardDTO boardDTO);
	public List<BoardDTO> getBoardList();
	public List<BoardDTO>getBoardPagingList(int startRow, int endRow);
	public List<BoardDTO>getBoardPopularPagingList(int startRow, int endRow);
	public BoardDTO getBoard(long seq_board);
	public void boardUpdate(BoardDTO boardDTO);
	public void boardDelete(long seq_board);
	public void boardUpdateNotImage(BoardDTO boardDTO);
	public List<BoardDTO> getMyBoardList(long seq_member);
	public List<BoardDTO> searchBoardPagingList(Map<String, Object> map);
}
