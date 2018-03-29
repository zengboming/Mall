package com.netease.dao;

import java.util.List;

import com.netease.meta.Buy;

public interface IBuyDao {

	List<Buy> getBuyList();
	
	Buy getBuyById(int id);
	
	int insertBuy(Buy buy);
	
	int getPriceSum();
}
