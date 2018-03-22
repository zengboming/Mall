package com.netease.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.IGoodsDao;
import com.netease.meta.Goods;
import com.netease.service.IGoodsService;

@Service("goodsService")
public class GoodsService implements IGoodsService {

	@Autowired
	public IGoodsDao goodsDao;
	
	@Override
	public List<Goods> getGoodsList() {
		return goodsDao.getGoodsList();
	}

	@Override
	public List<Goods> getNobuyGoodsList() {
		return goodsDao.getNotBuyGoodsList();
	}

	@Override
	public Goods getGoodsById(int id) {
		return goodsDao.selectGoodsById(id);
	}

	@Override
	public boolean addGoods(Goods goods) {
		int count = goodsDao.insertGoods(goods);
		return count == 1 ? true : false;
	}

	@Override
	public boolean editGoods(Goods goods) {
		int count = goodsDao.updateGoods(goods);
		return count == 1 ? true : false;
	}

	@Override
	public boolean removeGoodsById(int id) {
		int count = goodsDao.deleteGoodsById(id);
		return count == 1 ? true : false;
	}

	@Override
	public boolean changeGoodsStatusById(int id) {
		int count = goodsDao.updateGoodsStatus(id);
		return count == 1 ? true : false;
	}

}
