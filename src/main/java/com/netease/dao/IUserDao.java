package com.netease.dao;

import com.netease.meta.User;

public interface IUserDao {

	User getUserByAccount(String account);
}
