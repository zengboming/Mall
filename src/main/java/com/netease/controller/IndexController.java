package com.netease.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netease.service.IGoodsService;

@Controller
public class IndexController {

	@Autowired
	public IGoodsService goodsService;
	
	@RequestMapping(value = "/show")
	public String login(ModelMap map) throws IOException {
		
		return "test";
	}
}
