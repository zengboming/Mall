package com.netease.dao;

import java.util.List;

import com.netease.meta.Goods;

public interface IGoodsDao {

	List<Goods> getGoodsList();
	
	List<Goods> getNotBuyGoodsList();
	
	int insertGoods(Goods goods);
	
	int deleteGoodsById(int id);
	
	int updateGoods(Goods goods);

	Goods selectGoodsById(int id);
	
	int updateGoodsStatus(int id);
}
