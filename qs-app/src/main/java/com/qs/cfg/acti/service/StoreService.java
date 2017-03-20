package com.qs.cfg.acti.service;

import java.util.List;
import java.util.Map;

import com.qs.cfg.acti.model.Store;

public interface StoreService {
	/**
	 * 通过商城id查询金币
	 * @param storeId
	 * @return
	 */
	public Integer getGoldByPayMoney(Integer money);

  

 
}