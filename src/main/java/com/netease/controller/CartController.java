package com.netease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.netease.service.ICartService;

@Controller
public class CartController {

	@Autowired
	public ICartService cartService;
}
