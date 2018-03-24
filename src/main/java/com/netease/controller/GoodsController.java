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
import com.netease.meta.Goods;
import com.netease.service.IGoodsService;

@Controller
public class GoodsController {

	@Autowired
	public IGoodsService goodsService;

	@RequestMapping(value = "/goodsList")
	public void getGoodsList(HttpServletResponse response) {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JSONArray array = new JSONArray();
		List<Goods> list = goodsService.getGoodsList();
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
		OutputStream out;
		try {
			out = response.getOutputStream();
			out.write(result.getBytes("UTF-8"));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	@RequestMapping(value = "/goods")
	public void getGoods(@RequestBody String body, HttpServletResponse response) {
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");

		JSONObject obj = JSONObject.parseObject(body);
		String id = obj.getString("id");
		Goods goods = goodsService.getGoodsById(Integer.parseInt(id));
		JSONObject object = new JSONObject();
		object.put("id", goods.getId());
		object.put("image", goods.getImg());
		object.put("title", goods.getTitle());
		object.put("abstract", goods.getAbstr());
		object.put("buy", goods.getBuy());
		object.put("number", goods.getNumber());
		object.put("price", goods.getPrice());
		object.put("content", goods.getContent());
		String result = object.toJSONString();
		try {
			OutputStream out = response.getOutputStream();
			out.write(result.getBytes("UTF-8"));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addGoods")
	public void addGoods(@RequestBody String body, HttpServletResponse response) {
		JSONObject obj = JSONObject.parseObject(body);
		Goods goods = new Goods();
		if (!obj.getString("image").isEmpty()) {
			goods.setImg(obj.getString("img"));
		}
		if (!obj.getString("title").isEmpty()) {
			goods.setTitle(obj.getString("title"));
		}
		if (!obj.getString("abstract").isEmpty()) {
			goods.setAbstr(obj.getString("abstract"));
		}
		if (!obj.getString("content").isEmpty()) {
			goods.setContent(obj.getString("content"));
		}
		if (!obj.getString("number").isEmpty()) {
			goods.setNumber(obj.getIntValue("number"));
		}
		if (!obj.getString("price").isEmpty()) {
			goods.setPrice(obj.getString("price"));
		}
		Boolean result = goodsService.addGoods(goods);
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/editGoods")
	public void editGoods(@RequestBody String body, HttpServletResponse response) {
		JSONObject obj = JSONObject.parseObject(body);
		Goods goods = new Goods();
		if (!obj.getString("id").isEmpty()) {
			goods.setId(obj.getIntValue("id"));	
		}
		if (!obj.getString("image").isEmpty()) {
			goods.setImg(obj.getString("image"));
		}
		if (!obj.getString("title").isEmpty()) {
			goods.setTitle(obj.getString("title"));
		}
		if (!obj.getString("abstract").isEmpty()) {
			goods.setAbstr(obj.getString("abstract"));
		}
		if (!obj.getString("content").isEmpty()) {
			goods.setContent(obj.getString("content"));
		}
		if (!obj.getString("number").isEmpty()) {
			goods.setNumber(obj.getIntValue("number"));
		}
		if (!obj.getString("price").isEmpty()) {
			goods.setPrice(obj.getString("price"));
		}
		Boolean result = goodsService.editGoods(goods);
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deleteGoods")
	public void deleteGoods(@RequestBody String body, HttpServletResponse response) {
		JSONObject object = JSONObject.parseObject(body);
		String id = object.getString("id");
		Boolean result = goodsService.removeGoodsById(Integer.parseInt(id));
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
