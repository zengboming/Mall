package com.netease.service;

import java.util.List;

import com.netease.meta.Buy;

public interface IBuyService {

	List<Buy> getGoodsList();
	
	boolean addGoods(Buy buy);
	
	int getPriceSum();
}
