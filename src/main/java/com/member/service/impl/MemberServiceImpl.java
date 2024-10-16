package com.member.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.bean.BoardDTO;
import com.board.dao.BoardDAO;
import com.member.bean.MemberDTO;
import com.member.dao.MemberDAO;
import com.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public boolean checkId(String id) {
		MemberDTO memberDTO = memberDAO.isExistId(id);
		if (memberDTO == null) return false;
		else return true;
	}
	
	@Override
	public void join(MemberDTO memberDTO) {
		
		memberDAO.join(memberDTO);
	}

	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}

	@Override
	public MemberDTO getMember(String id) {
		return memberDAO.getMember(id);
	}

	@Override
	public void update(MemberDTO memberDTO) {
		memberDAO.update(memberDTO);
	}

	@Override
	public void delete(MemberDTO memberDTO) {
		memberDAO.delete(memberDTO);
	}

	@Override
	public List<BoardDTO> getMypageSup(String seq) {
		if(seq != "") return boardDAO.getMyBoardList(Integer.parseInt(seq));
		else return null;
	}
}
