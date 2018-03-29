package com.netease.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.meta.Cart;
import com.netease.service.ICartService;
import com.netease.service.IHeaderService;

@Controller
public class CartController {

	@Autowired
	public ICartService cartService;
	
	@Autowired
	public IHeaderService headerService;
	
	@RequestMapping(value = "/add") 
	public void addCart(@RequestBody String body, HttpServletResponse response) {
		JSONObject obj = JSONObject.parseObject(body);
		int goodsId = obj.getIntValue("goodsId");
		Boolean result = cartService.addGoods(goodsId);
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deleteCart")
	public void deleteCart(HttpServletResponse response) {
		Boolean result = cartService.deleteGoods();
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/cart")
	public String getCartList(Model model, HttpServletResponse response) {
		List<Cart> list = cartService.getCartList();
		JSONArray array = new JSONArray();
		for (Cart cart : list) {
			JSONObject object = new JSONObject();
			object.put("id", cart.getId());
			object.put("title", cart.getTitle()); 
			object.put("price", cart.getPrice());
			object.put("number", cart.getNumber());
			array.add(object);
		}
		model.addAttribute("products", array);
		//Cookie products = new Cookie("products", array.toJSONString());
		//response.addCookie(products);
		headerService.addUser(model);
		
		return "settleAccount";
	}
}
