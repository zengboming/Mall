package com.netease.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netease.dao.IBuyDao;
import com.netease.meta.Buy;

@Repository
public class BuyDao implements IBuyDao{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Buy> getBuyList() {
		List<Buy> list =  this.jdbcTemplate.query("select * from buy", 
				new RowMapper<Buy>() {

					@Override
					public Buy mapRow(ResultSet rs, int rowNum) throws SQLException {
						Buy buy = new Buy();
						buy.setId(rs.getInt("id"));
						buy.setImg(rs.getString("img"));
						buy.setNumber(rs.getInt("number"));
						buy.setPrice(rs.getString("price"));
						buy.setTime(rs.getString("time"));
						buy.setTitle(rs.getString("title"));
						return buy;
					}
				});
		return list;
	}
	
	@Override
	public Buy getBuyById(int id) {
		Buy buy =  this.jdbcTemplate.queryForObject("select * from buy where id = ?",
				new Object[]{id},
				new RowMapper<Buy>() {

					@Override
					public Buy mapRow(ResultSet rs, int rowNum) throws SQLException {
						Buy buy = new Buy();
						buy.setId(rs.getInt("id"));
						buy.setImg(rs.getString("img"));
						buy.setNumber(rs.getInt("number"));
						buy.setPrice(rs.getString("price"));
						buy.setTime(rs.getString("time"));
						buy.setTitle(rs.getString("title"));
						return buy;
					}
				});
		return buy;
	}

	@Override
	public int insertBuy(Buy buy) {
		return this.jdbcTemplate.update("insert into "
				+ "buy (id,title, img, price, number, time)"
				+ "values (?,?, ?, ?, ?, ?)", 
				buy.getId(), buy.getTitle(), buy.getImg(), 
				buy.getPrice(), buy.getNumber(), buy.getTime());
	}

	@Override
	public int getPriceSum() {
		return this.jdbcTemplate.queryForObject("select SUM(price) from buy", 
				Integer.class);
	}

}
