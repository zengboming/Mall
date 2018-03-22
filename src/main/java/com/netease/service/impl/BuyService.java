package com.netease.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.IBuyDao;
import com.netease.meta.Buy;
import com.netease.service.IBuyService;

@Service("buyService")
public class BuyService implements IBuyService {

	@Autowired
	public IBuyDao buyDao;
	
	@Override
	public List<Buy> getGoodsList() {
		return buyDao.getBuyList();
	}

	@Override
	public boolean addGoods(Buy buy) {
		int count = buyDao.insertBuy(buy);
		return count == 1 ? true : false;
	}

	@Override
	public int getPriceSum() {
		return buyDao.getPriceSum();
	}

}
