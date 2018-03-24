package com.netease.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.meta.Cart;
import com.netease.service.ICartService;

@Controller
public class CartController {

	@Autowired
	public ICartService cartService;
	
	@RequestMapping(value = "/addCart")
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
	
	@RequestMapping(value = "/getCart")
	public void getCartList(HttpServletResponse response) {
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
		String result = array.toJSONString();
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			OutputStream out = response.getOutputStream();
			out.write(result.getBytes("UTF-8"));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
