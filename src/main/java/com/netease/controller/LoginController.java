package com.netease.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import com.netease.meta.User;
import com.netease.service.IUserService;

@Controller
public class LoginController{

	private static String userName = "";
	private static String userType = ""; 

	@Autowired
	public IUserService userService;
	
	public static String getUserName() {
		return userName;
	}

	public static String getUserType() {
		return userType;
	}
	
	@RequestMapping(value = "/header")
	public void getUser(@RequestBody String body, HttpServletResponse response) {
		JSONObject obj = JSONObject.parseObject(body);
		User user = userService.getUser(obj.getString("account"), 
				obj.getString("password"));
		//User user = userService.getUser("buyer", "reyub");
		JSONObject object = new JSONObject();
		object.put("name", user.getName());
		object.put("type", user.getType());
		String result = object.toJSONString();
		try {
			OutputStream out = response.getOutputStream();
			out.write(result.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response){ 
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		//password = userService.MD5Tools(userService.MD5Tools(password));
		User user = userService.getUser(account, password);
		try {
			JSONObject param = new JSONObject();
			if (user != null) {
				param.put("code", 200);
				param.put("result", "1");
				userName = user.getName();
				userType = user.getType();
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
	
	@RequestMapping(value = "/logout")
	public String logout() {
		userName = "";
		userType = "";
		return "login";
	}
	
	@RequestMapping(value = "/goLogin")
	public String goLogin() {
		return "login";
	}
}
