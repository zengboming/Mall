package com.netease.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.meta.Buy;
import com.netease.meta.Goods;
import com.netease.service.IBuyService;
import com.netease.service.IGoodsService;
import com.netease.service.IHeaderService;

@Controller
public class GoodsController {

	@Autowired
	public IGoodsService goodsService;
	
	@Autowired
	public IBuyService buyService;
	
	@Autowired
	public IHeaderService headerSerVice;
	
	@RequestMapping(value = "/index") 
	public String getGoodsList(@RequestParam("type") String type, Model model) {
		JSONArray array = new JSONArray();
		if (type.equals("0")) {
			List<Goods> list = goodsService.getGoodsList();
			for (Goods goods : list) {
				JSONObject object = new JSONObject();
				object.put("id", goods.getId());
				object.put("image", goods.getImg());
				object.put("title", goods.getTitle());
				object.put("buy", goods.getBuy());
				object.put("price", goods.getPrice());
				array.add(object);
			}
		} else {
			List<Goods> list = goodsService.getNobuyGoodsList();
			for (Goods goods : list) {
				JSONObject object = new JSONObject();
				object.put("id", goods.getId());
				object.put("image", goods.getImg());
				object.put("title", goods.getTitle());
				object.put("buy", goods.getBuy());
				object.put("price", goods.getPrice());
				array.add(object);
			}
		}
		model.addAttribute("productList",array);
		headerSerVice.addUser(model);
		return "index";
	}

	@RequestMapping(value = "/noBuyGoodsList")
	public void getNotBuyGoodsList(HttpServletResponse response) {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JSONArray array = new JSONArray();
		List<Goods> list = goodsService.getNobuyGoodsList();
		for (Goods goods : list) {
			JSONObject object = new JSONObject();
			object.put("id", goods.getId());
			object.put("image", goods.getImg());
			object.put("title", goods.getTitle());
			object.put("abstract", goods.getAbstr());
			object.put("buy", goods.getBuy());
			object.put("number", goods.getNumber());
			object.put("price", goods.getPrice());
			object.put("content", goods.getContent());
			array.add(object);
		}
		String result = array.toJSONString();
		try {
			OutputStream out = response.getOutputStream();
			out.write(result.getBytes("UTF-8"));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@RequestMapping(value = "/show")
	public String getGoods(@RequestParam("id") String userId, Model model, HttpServletResponse response) {
		int id = Integer.valueOf(userId);
		Goods goods = goodsService.getGoodsById(id);
		JSONObject object = new JSONObject();
		object.put("id", userId);
		object.put("image", goods.getImg());
		object.put("title", goods.getTitle());
		object.put("abstract", goods.getAbstr());
		object.put("buy", goods.getBuy());
		object.put("number", Integer.toString(goods.getNumber()));
		object.put("price", goods.getPrice());
		object.put("content", goods.getContent());
		if (goods.getBuy().equals("1")) {
			Buy buy = buyService.getGoodsById(id);
			object.put("buyPrice", buy.getPrice());
		}
		model.addAttribute("product", object);
		headerSerVice.addUser(model);
		JSONArray array = new JSONArray();
		array.add(object);
		Cookie cookie = new Cookie("products", array.toJSONString());
		response.addCookie(cookie);
		return "show";
	}
	
	@RequestMapping(value = "/edit")
	public String editGoods(@RequestParam("id") String userId, Model model) {
		int id = Integer.valueOf(userId);
		Goods goods = goodsService.getGoodsById(id);
		JSONObject object = new JSONObject();
		object.put("id", goods.getId());
		object.put("image", goods.getImg()); 
		object.put("title", goods.getTitle());
		object.put("abstract", goods.getAbstr());
		object.put("buy", goods.getBuy());
		object.put("number", goods.getNumber());
		object.put("price", goods.getPrice());
		object.put("content", goods.getContent());
		model.addAttribute("product", object);
		headerSerVice.addUser(model);
		return "edit";
	}

	@RequestMapping(value = "/publicSubmit")
	public String addGoods(@RequestParam("title") String title,
			 			 @RequestParam("summary") String abstr,
			 			 @RequestParam("image") String image,
			 			 @RequestParam("detail") String content,
			 			 @RequestParam("price") String price,
			 			 Model model) throws Exception {
		Goods goods = new Goods();
		try {
			abstr = new String(abstr.getBytes("ISO-8859-1"), "UTF-8");
			title = new String(title.getBytes("ISO-8859-1"), "UTF-8");
			content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (!image.isEmpty()) {
			goods.setImg(image);
		}
		if (!title.isEmpty()) {
			goods.setTitle(title);
		}
		if (!abstr.isEmpty()) {
			goods.setAbstr(abstr);
		}
		if (!content.isEmpty()) {
			goods.setContent(content);
		}
//		if (!obj.getString("number").isEmpty()) {
		goods.setNumber(1);
//		}
		if (!price.isEmpty()) {
			goods.setPrice(price);
		}
		Boolean result = goodsService.addGoods(goods);
		if (result) {
			JSONObject object = new JSONObject();
			object.put("id", result.toString());
			model.addAttribute("product", object);
		}
		headerSerVice.addUser(model);
		return "publicSubmit";
	}
	
	@RequestMapping(value = "/public")
	public String getPublic(Model model) {
		headerSerVice.addUser(model);
		return "public";
	}
	
	@RequestMapping(value = "/editSubmit")
	public String editSubmit(@RequestParam("id") String id,
							 @RequestParam("title") String title,
							 @RequestParam("summary") String abstr,
							 @RequestParam("image") String image,
							 @RequestParam("detail") String content,
							 @RequestParam("price") String price,
							Model model) {
		Goods goods = new Goods();
		try {
			abstr = new String(abstr.getBytes("ISO-8859-1"), "UTF-8");
			title = new String(title.getBytes("ISO-8859-1"), "UTF-8");
			content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		goods.setId(Integer.valueOf(id));	
		goods.setImg(image);
		goods.setTitle(title); 
		goods.setAbstr(abstr);
		goods.setContent(content); 
		goods.setPrice(price);
		Boolean result = goodsService.editGoods(goods);
		
		if (result) {
			JSONObject object = new JSONObject();
			object.put("id", Integer.valueOf(id));
			model.addAttribute("product", object);
		}
		
		headerSerVice.addUser(model);
		return "editSubmit";
	}
	
	@RequestMapping(value = "/delete")
	public void deleteGoods(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Boolean result = goodsService.removeGoodsById(Integer.parseInt(id));
		try {
			JSONObject param = new JSONObject();
			if (result) {
				param.put("code", 200);
				param.put("result", "1");
				response.getWriter().write(param.toJSONString());
			} else {
				param.put("code", 404);
				param.put("result", "0");
				response.getWriter().write(param.toJSONString());
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
}
