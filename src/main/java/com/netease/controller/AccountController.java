package com.netease.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.meta.Buy;
import com.netease.meta.Goods;
import com.netease.service.IBuyService;
import com.netease.service.ICartService;
import com.netease.service.IGoodsService;
import com.netease.service.IHeaderService;

@Controller
public class AccountController {

	@Autowired
	public IBuyService buyService;
	
	@Autowired
	public IGoodsService goodsService;
	
	@Autowired
	public IHeaderService headerSerVice;
	
	@Autowired
	public ICartService cartService;
	
	@RequestMapping(value = "/account")
	public String getBuyList(Model model) {
//		response.setContentType("application/json; charset=utf-8");  
//		response.setCharacterEncoding("UTF-8");
		List<Buy> list = buyService.getGoodsList();
		JSONArray array = new JSONArray();
		for (Buy buy : list) { 
			JSONObject object = new JSONObject();
			object.put("id", buy.getId());
			object.put("number", buy.getNumber()); 
			object.put("image", buy.getImg()); 
			object.put("buyPrice", buy.getPrice()); 
			object.put("title", buy.getTitle());   
			object.put("buyTime", buy.getTime());
			array.add(object);
		}
//		JSONObject object = new JSONObject();
//		object.put("total", buyService.getPriceSum());
//		object.put("list", array);
//		String result = object.toJSONString();
//		try {
//			OutputStream out = response.getOutputStream();
//			out.write(result.getBytes("UTF-8"));
//			out.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		model.addAttribute("buyList", array);
		model.addAttribute("sum", buyService.getPriceSum()); 
		
		headerSerVice.addUser(model);
		return "account";
	}
	
	@RequestMapping(value = "/buy")
	public void addBuy(@RequestBody String body, HttpServletResponse response) {
		JSONArray array = JSONArray.parseArray(body);
		for (int i = 0; i < array.size(); i++) {
			JSONObject object = array.getJSONObject(i);
			int id = object.getIntValue("id");
			int number = object.getIntValue("number");
			Goods goods = goodsService.getGoodsById(id);
			Buy buy = new Buy();
			if (!goods.getImg().isEmpty()) {
				buy.setImg(goods.getImg());
			}
			if (!goods.getPrice().isEmpty()) {
				buy.setPrice(goods.getPrice());
			}
			if (!goods.getTitle().isEmpty()) {
				buy.setTitle(goods.getTitle());
			}
			buy.setNumber(number);
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			buy.setTime(format.format(date));
			
			goodsService.changeGoodsStatusById(id);
			Boolean result = buyService.addGoods(buy);
			if (result) {
				JSONObject param = new JSONObject();
				param.put("code", 200);
				param.put("result", "1");
				cartService.deleteGoods();
				try {
					response.getWriter().write(param.toJSONString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
	}
}
