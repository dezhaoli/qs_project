package com.qs.pub.game.service;

import java.util.List;
import java.util.Map;

import com.qs.cfg.acti.model.Store;

public interface StatService {
    
	/**
	 * 
	 * @标题: addBizCharge 
	 * @描述:增加商务收入
	 *
	 * @参数信息
	 *    @return
	 *
	 * @返回类型 int
	 * @开发者 moyousheng
	 * @可能抛出异常
	 */
	public int addBizCharge(short bizid,float money);

  
 
}