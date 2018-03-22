package com.netease.dao;

import java.util.List;

import com.netease.meta.Buy;

public interface IBuyDao {

	List<Buy> getBuyList();
	
	int insertBuy(Buy buy);
	
	int getPriceSum();
}
