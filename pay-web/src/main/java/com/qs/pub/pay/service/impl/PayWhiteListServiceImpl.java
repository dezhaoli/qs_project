package com.qs.pub.pay.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.pay.mapper.PayWhiteListMapper;
import com.qs.pub.pay.model.PayWhiteList;
import com.qs.pub.pay.service.IPayWhiteListService;

@Service
public class PayWhiteListServiceImpl implements IPayWhiteListService {

	@Resource
	private PayWhiteListMapper payWhiteListMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return payWhiteListMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PayWhiteList record) {
		
		return 0;
	}

	@Override
	public int insertSelective(PayWhiteList record) {
		
		return payWhiteListMapper.insertSelective(record);
	}

	@Override
	public PayWhiteList selectByPrimaryKey(Integer id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(PayWhiteList record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(PayWhiteList record) {
		
		return 0;
	}

	@Override
	public List<PayWhiteList> selectPayWhiteListAll(Map<String, Object> param) {
		return payWhiteListMapper.selectPayWhiteListAll(param);
	}

}
