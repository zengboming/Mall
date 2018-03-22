package com.netease.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netease.dao.IGoodsDao;
import com.netease.meta.Goods;

@Repository
public class GoodsDao implements IGoodsDao{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Goods> getGoodsList() {
		List<Goods> list =  this.jdbcTemplate.query("select * from goods", 
				new RowMapper<Goods>() {

					@Override
					public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
						Goods goods = new Goods();
						goods.setId(rs.getInt("id"));
						goods.setTitle(rs.getString("title"));
						goods.setPrice(rs.getString("price"));
						goods.setAbstr(rs.getString("abstract"));
						goods.setBuy(rs.getString("buy"));
						goods.setContent(rs.getString("content"));
						goods.setImg(rs.getString("img"));
						goods.setNumber(rs.getInt("number"));
						return goods;
					}
				});
		return list;
	}

	@Override
	public Goods selectGoodsById(int id) {
		Goods goods = this.jdbcTemplate.queryForObject("select * from goods where id = ?", 
				new Object[]{id},
				new RowMapper<Goods>() {

					@Override
					public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
						Goods goods = new Goods();
						goods.setId(id);
						goods.setTitle(rs.getString("title"));
						goods.setPrice(rs.getString("price"));
						goods.setAbstr(rs.getString("abstract"));
						goods.setBuy(rs.getString("buy"));
						goods.setContent(rs.getString("content"));
						goods.setImg(rs.getString("img"));
						goods.setNumber(rs.getInt("number"));
						return goods;
					}
				});
		return goods;
	}

	@Override
	public int insertGoods(Goods goods) {
		return this.jdbcTemplate.update("insert into "
				+ "goods (title, img, price, abstract, content, number, buy)"
				+ "values (?, ?, ?, ?, ?, ?, ?)", 
				goods.getTitle(), goods.getImg(), goods.getPrice(), goods.getAbstr(),
				goods.getContent(), goods.getNumber(), goods.getBuy());
	}

	@Override
	public int deleteGoodsById(int id) {
		return this.jdbcTemplate.update("delete from goods where id = ?", id);
	}

	@Override
	public int updateGoods(Goods goods) {
		return this.jdbcTemplate.update("update goods set title = ? abstract = ?"
				+ " img = ? content = ? price = ? where id = ?", 
				goods.getTitle(), goods.getAbstr(), goods.getImg(), 
				goods.getContent(), goods.getPrice(), goods.getId());
	}

	@Override
	public int updateGoodsStatus(int id) {
		return this.jdbcTemplate.update("update goods set buy = 1 "
				+ "where id = ?", id);
	}

	@Override
	public List<Goods> getNotBuyGoodsList() {
		List<Goods> list =  this.jdbcTemplate.query("select * from goods where buy = ?",
				new Object[]{"0"},
				new RowMapper<Goods>() {

					@Override
					public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
						Goods goods = new Goods();
						goods.setId(rs.getInt("id"));
						goods.setTitle(rs.getString("title"));
						goods.setPrice(rs.getString("price"));
						goods.setAbstr(rs.getString("abstract"));
						goods.setBuy(rs.getString("buy"));
						goods.setContent(rs.getString("content"));
						goods.setImg(rs.getString("img"));
						goods.setNumber(rs.getInt("number"));
						return goods;
					}
				});
		return list;
	}
	

}
