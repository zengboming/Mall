package com.netease.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.IUserDao;
import com.netease.meta.User;
import com.netease.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	public IUserDao userdao;

	@Override
	public String getUserName(String account, String password) {
		User user = userdao.getUserByAccount(account);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return user.getName();
			}
		}
		return null;
	}

}
