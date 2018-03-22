package com.netease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.netease.service.IBuyService;

@Controller
public class AccountController {

	@Autowired
	public IBuyService buyService;
}
