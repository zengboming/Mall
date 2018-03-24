package com.netease.service;

import com.netease.meta.User;

public interface IUserService {

	User getUser(String account, String password);
	String MD5Tools(String password);
}
