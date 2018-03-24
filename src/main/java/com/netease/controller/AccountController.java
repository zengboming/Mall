package com.netease.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.meta.Buy;
import com.netease.service.IBuyService;
import com.netease.service.IGoodsService;

@Controller
public class AccountController {

	@Autowired
	public IBuyService buyService;
	
	@Autowired
	public IGoodsService goodsService;
	
	@RequestMapping(value = "/getBuy")
	public void getBuyList(HttpServletResponse response) {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		List<Buy> list = buyService.getGoodsList();
		JSONArray array = new JSONArray();
		for (Buy buy : list) {
			JSONObject object = new JSONObject();
			object.put("id", buy.getId());
			object.put("number", buy.getNumber());
			object.put("image", buy.getImg());
			object.put("price", buy.getPrice());
			object.put("title", buy.getTitle());
			object.put("time", buy.getTime());
			array.add(object);
		}
		JSONObject object = new JSONObject();
		object.put("total", buyService.getPriceSum());
		object.put("list", array);
		String result = object.toJSONString();
		try {
			OutputStream out = response.getOutputStream();
			out.write(result.getBytes("UTF-8"));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addBuy")
	public void addBuy(@RequestBody String body, HttpServletResponse response) {
		JSONObject obj = JSONObject.parseObject(body);
		JSONArray array = obj.getJSONArray("list");
		for (int i = 0; i < array.size(); i++) {
			JSONObject object = array.getJSONObject(i);
			Buy buy = new Buy();
			if (!object.getString("image").isEmpty()) {
				buy.setImg(object.getString("image"));
			}
			if (!object.getString("price").isEmpty()) {
				buy.setPrice(object.getString("price"));
			}
			if (!object.getString("title").isEmpty()) {
				buy.setTitle(object.getString("title"));
			}
			if (!object.getString("number").isEmpty()) {
				buy.setNumber(object.getIntValue("number"));
			}
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			buy.setTime(format.format(date));
			
			goodsService.changeGoodsStatusById(object.getIntValue("id"));
			
			Boolean result = buyService.addGoods(buy);
			if (!result) {
				try {
					response.getWriter().write(result.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		
		try {
			response.getWriter().write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
