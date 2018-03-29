package com.netease.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONObject;
import com.netease.controller.LoginController;
import com.netease.service.IHeaderService;

@Service("headerService")
public class HeaderService implements IHeaderService{

	@Override
	public void addUser(Model model) {
		if (!LoginController.getUserName().isEmpty()) {
			JSONObject user = new JSONObject();
			user.put("usertype", LoginController.getUserType());
			user.put("username", LoginController.getUserName());
			model.addAttribute("user", user);
		}
	}

}
