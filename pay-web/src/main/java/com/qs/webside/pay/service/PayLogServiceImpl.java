package com.qs.webside.pay.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.webside.pay.impl.IPayLog;
import com.qs.webside.pay.mapper.PayLogMapper;
import com.qs.webside.pay.model.PayLog;

@Service
public class PayLogServiceImpl implements  IPayLog{
 
	@Resource
	private PayLogMapper payLogMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(PayLog record) {
		return payLogMapper.insertSelective(record);
	}

	@Override
	public int insertSelective(PayLog record) {
		return 0;
	}

	@Override
	public PayLog selectByPrimaryKey(Integer id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(PayLog record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(PayLog record) {
		return 0;
	}

	@Override
	public List<PayLog> selectPayLogAll(Map<String ,Object> payLog) {
		return payLogMapper.selectPayLogAll(payLog);
	}

}
