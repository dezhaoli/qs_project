package com.qs.pub.pay.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.pay.mapper.PayLogMapper;
import com.qs.pub.pay.model.PayLog;
import com.qs.pub.pay.service.IPayLogService;

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

	@Override
	public PayLog findPayLogByCondition(PayLog record) {
		// TODO Auto-generated method stub
		return payLogMapper.findPayLogByCondition(record);
	}

}
