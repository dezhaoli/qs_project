package com.qs.webside.pay.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.qs.webside.pay.service.IPayLogService;
import org.springframework.stereotype.Service;

import com.qs.webside.pay.mapper.PayLogMapper;
import com.qs.webside.pay.model.PayLog;

@Service
public class PayLogServiceServiceImpl implements IPayLogService {
 
	@Resource
	private PayLogMapper payLogMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(PayLog record) {
		return payLogMapper.insert(record);
	}

	@Override
	public int insertSelective(PayLog record) {
		return payLogMapper.insertSelective(record);
	}

	@Override
	public PayLog selectByPrimaryKey(Integer id) {
		return payLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PayLog record) {
		return payLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PayLog record) {
		return payLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PayLog> selectPayLogAll(Map<String ,Object> payLog) {
		return payLogMapper.selectPayLogAll(payLog);
	}

}
