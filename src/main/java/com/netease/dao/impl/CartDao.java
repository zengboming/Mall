package com.netease.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netease.dao.ICartDao;
import com.netease.meta.Cart;

@Repository
public class CartDao implements ICartDao{

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Cart> getCartList() {
		List<Cart> list =  this.jdbcTemplate.query("select * from cart, goods "
				+ "where cart.goods_id = goods.id", 
				new RowMapper<Cart>() {

					@Override
					public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
						Cart cart = new Cart();
						cart.setId(rs.getInt("goods_id"));
						cart.setNumber(rs.getInt("number"));
						cart.setPrice(rs.getString("price"));
						cart.setTitle(rs.getString("title"));
						return cart;
					}
				});
		return list;
	}

	@Override
	public int deleteCart() {
		return this.jdbcTemplate.update("delete from cart");
	}

	@Override
	public int insertCart(int goodsId) {
		return this.jdbcTemplate.update("insert into cart (goods_id) values (?)",
				goodsId);
	}

}
