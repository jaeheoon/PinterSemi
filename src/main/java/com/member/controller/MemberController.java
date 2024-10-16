package com.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.bean.BoardDTO;
import com.member.bean.MemberDTO;
import com.member.kakao.service.KaKaoService;
import com.member.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private KaKaoService kaKaoService;
	
	@RequestMapping(value = "/checkId")
	@ResponseBody
	public String checkId(String id) {
		String check = "exist";
		if(!memberService.checkId(id)) check = "non_exist";
		return check;
	}
	
	@RequestMapping(value = "/join")
	@ResponseBody
	public void join(@ModelAttribute MemberDTO memberDTO,
		             @RequestParam("email1") String email1,
		             @RequestParam("email2") String email2,
		             @RequestParam("tel1") String tel1,
		             @RequestParam("tel2") String tel2,
		             @RequestParam("tel3") String tel3,
		             @RequestParam("addr1") String addr1,
		             @RequestParam("addr2") String addr2) {
		String email = email1 + "@" + email2;
		String phoneNumber = tel1 + "-" + tel2 + "-" + tel3;
		String address = addr1 + "," + addr2;
        memberDTO.setEmail(email);
        memberDTO.setPhoneNumber(phoneNumber);
        memberDTO.setAddress(address);
		memberService.join(memberDTO);
	}
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public String login(@RequestParam String id, @RequestParam String password, HttpSession session) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		MemberDTO memberDTO = memberService.login(map);
		String check = "fail";
		if(memberDTO != null) {
			session.setAttribute("memDTO", memberDTO);
			check = "success";
		}
		return check;
	}
	
	@RequestMapping(value = "/logout")
	@ResponseBody
	public void logout(HttpSession session) {
		session.removeAttribute("memDTO");
	}
	
	@RequestMapping(value = "/mypageForm")
	public String mypageForm() {
		return "/member/mypageForm";
	}
	
	@RequestMapping(value = "/mypageSup")
	@ResponseBody
	public List<BoardDTO> mypageSup(@RequestParam("seq_member") String seq) {
		List<BoardDTO> list = memberService.getMypageSup(seq);
		return list;
	}
	
	@RequestMapping(value = "/updateForm")
	public String updateForm() {
		return "/member/updateForm";
	}
	
	@RequestMapping(value = "/kakao/login")
	public String getCI(@RequestParam String code, Model model) throws IOException {
		System.out.println("code = " + code);
		String access_token = kaKaoService.getToken(code); 
		System.out.println("access_token : " + access_token);
        Map<String, Object> userInfo = kaKaoService.getUserInfo(access_token);
        System.out.println("id : " + userInfo.get("id"));
        System.out.println("nickname : " + userInfo.get("nickname"));
        System.out.println("email : " + userInfo.get("email"));
        System.out.println("profile_image : " + userInfo.get("profile_image"));
        
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);
        
		return "redirect:/";
	}
}
