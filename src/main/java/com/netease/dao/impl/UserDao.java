package com.netease.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netease.dao.IUserDao;
import com.netease.meta.User;

@Repository
public class UserDao implements IUserDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
//	public String getUserName(String account) {
//		return this.jdbcTemplate.queryForObject("select name from user where account = ?",
//				new String[]{account}, String.class);
//	}
	
	@Override
	public User getUserByAccount(String account) {
		User user = this.jdbcTemplate.queryForObject("select * from user where account = ?", 
				new String[]{account}, 
				new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}	
		});
		return user;
	}
}
