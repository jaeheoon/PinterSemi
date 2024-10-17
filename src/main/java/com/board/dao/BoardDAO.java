package com.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.bean.BoardDTO;


@Mapper
public interface BoardDAO {

	public void boardWrite(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public List<BoardDTO> getBoardPagingList(@Param("startRow")int startRow, @Param("endRow") int endRow);

	public List<BoardDTO> getBoardPopularPagingList(@Param("startRow") int startRow, @Param("endRow") int endRow);

	public BoardDTO getBoard(long seq_board);

	public void boardUpdate(BoardDTO boardDTO);

	public void boardDelete(long seq_board);

	public void boardUpdateNotImage(BoardDTO boardDTO);

	public List<BoardDTO> getMyBoardList(long seq_member);

	public List<BoardDTO> searchBoardPagingList(Map<String, Object> map);

}
