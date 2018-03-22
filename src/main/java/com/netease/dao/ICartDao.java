package com.netease.dao;

import java.util.List;

import com.netease.meta.Cart;

public interface ICartDao {

	List<Cart> getCartList();
	
	int deleteCart();
	
	int insertCart(int goodsId);
}
