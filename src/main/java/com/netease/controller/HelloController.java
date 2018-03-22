package com.netease.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.sym.Name;
import com.netease.dao.IUserDao;
import com.netease.meta.User;
import com.netease.service.impl.UserService;

@Controller
public class HelloController {
	
//	@Autowired
//	public IUserDao udao;
	@Autowired
	public UserService userService;
	
	@RequestMapping(value = "/spring")
	public void spring(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write("Hello Spring Web!啊啊啊");
		
		//ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

//		JDBCDao dao = context.getBean("JDBCDao", JDBCDao.class);
//		System.out.println(dao.count());
//		List<User> users = dao.getUserList();
//		for (User user : users) {
//			System.out.println(user.getId() + " " + user.getFirstName() + " " + user.getLastName());
//		}
//
//		writer.write("count =" + dao.count());
//		((ConfigurableApplicationContext) context).close();
	}

	@RequestMapping(value = "/test")
	public String test(ModelMap map) throws IOException {
//		User user = udao.getUserByAccount("buyer");
//		map.addAttribute("name", user.getName());
//		map.addAttribute("password", user.getPassword());
		String name = userService.getUserName("buyer", "reyub");
		map.addAttribute("name", name);
		map.addAttribute("password", "*****");
		return "test";
	}
}
