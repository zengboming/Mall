package com.netease.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netease.service.IUserService;

@Controller
public class LoginController {

	@Autowired
	public IUserService userService;
	
	@RequestMapping(value = "/login")
	public String login(ModelMap map) throws IOException {
		String name = userService.getUserName("buyer", "reyub");
		map.addAttribute("name", name);
		map.addAttribute("password", "*****");
		return "test";
	}
}
