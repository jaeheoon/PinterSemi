package com.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.bean.UserDTO;
import com.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
	}
	
	@RequestMapping(value = "/checkId", method = RequestMethod.GET)
	@ResponseBody
	public String checkId(String id) {
		UserDTO userDTO = userService.findId(id);
		return userDTO != null ? "exist" : "nonExist";
	}
	
	//required = false - 파라미터가 없어도 에러를 발생시키지 않는다
	//defaultValue = "1" 파라미터가 없으면 기본값 1로 준다
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(required = false, defaultValue = "1") String page,
					   Model model) {
		Map<String, Object> userMap = userService.list();
		
		model.addAttribute("list", userMap.get("list"));
		
		return "/user/list";	// /WEB-INF/user/list.jsp
	}
}
