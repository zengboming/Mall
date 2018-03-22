package com.netease.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.ICartDao;
import com.netease.meta.Cart;
import com.netease.service.ICartService;

@Service("cartService")
public class CartService implements ICartService {

	@Autowired
	public ICartDao cartDao;
	
	@Override
	public List<Cart> getCartList() {
		return cartDao.getCartList();
	}

	@Override
	public boolean addGoods(int goodsId) {
		int count = cartDao.insertCart(goodsId);
		return count == 1 ? true : false;
	}

	@Override
	public boolean deleteGoods() {
		int count = cartDao.deleteCart();
		return count > 0 ? true : false;
	}

}
