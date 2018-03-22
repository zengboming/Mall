package com.netease.service;

import java.util.List;

import com.netease.meta.Cart;

public interface ICartService {

	List<Cart> getCartList();
	
	boolean addGoods(int goodsId);
	
	boolean deleteGoods();
}
