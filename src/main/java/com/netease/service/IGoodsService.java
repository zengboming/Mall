package com.netease.service;

import java.util.List;

import com.netease.meta.Goods;

public interface IGoodsService {

	List<Goods> getGoodsList();
	
	List<Goods> getNobuyGoodsList();
	
	Goods getGoodsById(int id);
	
	boolean addGoods(Goods goods);
	
	boolean editGoods(Goods goods);
	
	boolean removeGoodsById(int id);
	
	boolean changeGoodsStatusById(int id);
}
