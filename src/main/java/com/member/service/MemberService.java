package com.member.service;

import java.util.List;
import java.util.Map;

import com.board.bean.BoardDTO;
import com.member.bean.MemberDTO;

public interface MemberService {
	public boolean checkId(String id);
	public void join(MemberDTO memberDTO);
	public MemberDTO login(Map<String, String> map);
	public MemberDTO getMember(String id);
	public void update(MemberDTO memberDTO);
	public void delete(MemberDTO memberDTO);
	public List<BoardDTO> getMypageSup(String seq);
}
