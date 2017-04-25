package com.qs.pub.pay.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.pay.model.PayLog;

public interface IPayLogService {
	int deleteByPrimaryKey(Integer id);

	int insert(PayLog record);

	int insertSelective(PayLog record);

	PayLog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(PayLog record);

	int updateByPrimaryKey(PayLog record);
	/**
	 * 根据条件参数对象查询支付日志信息
	 * @param payLog
	 * @return
	 * @author:zyy
	 * @time:2017年4月5日
	 */
	List<PayLog> selectPayLogAll(Map<String ,Object> payLog);
	
	   /**
     * 判断支付日志是否存在
     * @param record
     * @return
     */
    PayLog findPayLogByCondition(PayLog record);
}
