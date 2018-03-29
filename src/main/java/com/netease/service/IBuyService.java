package com.netease.service;

import java.util.List;

import com.netease.meta.Buy;

public interface IBuyService {

	List<Buy> getGoodsList();
	
	Buy getGoodsById(int id);
	
	boolean addGoods(Buy buy);
	
	int getPriceSum();
}
