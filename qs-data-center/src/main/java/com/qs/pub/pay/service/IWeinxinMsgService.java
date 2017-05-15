package com.qs.pub.pay.service;

import java.util.List;

import com.qs.pub.pay.model.WeixinMsg;

/**
 * 微信错误信息
 * @author moys
 *
 */
public interface IWeinxinMsgService {
	void save(WeixinMsg record);
	WeixinMsg findById(String id);
	void delete(String id);
	List<WeixinMsg> getCacheList(String key);
}
