package com.ncp.PinterSemi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.conf.KakaoConfiguration;

@Controller
public class MainController {
	@Autowired
	private KakaoConfiguration kakaoConfiguration;
	
	@RequestMapping(value="/")
	public String index(Model model) {
		model.addAttribute("REST_API_KEY", kakaoConfiguration.getClientId());
		return "/index";
	}
	
}
