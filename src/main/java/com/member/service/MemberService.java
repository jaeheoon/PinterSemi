package com.member.service;

import java.util.List;
import java.util.Map;

<<<<<<< HEAD
=======
import org.springframework.web.multipart.MultipartFile;

>>>>>>> d5e248897a913a9ec5d8517b9cc2a626720df8f6
import com.board.bean.BoardDTO;
import com.member.bean.MemberDTO;

public interface MemberService {
	public boolean checkId(String id);
	public void join(MemberDTO memberDTO);
	public MemberDTO login(Map<String, String> map);
	public MemberDTO getMember(String id);
	public void update(MemberDTO memberDTO, MultipartFile userProfileImg);
	public void delete(MemberDTO memberDTO);
	public List<BoardDTO> getMypageSup(String seq);
}
