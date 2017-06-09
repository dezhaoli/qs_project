package com.qs.webside.activity.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.webside.activity.mapper.ActiAwardAddressMapper;
import com.qs.webside.activity.model.ActiAwardAddress;
import com.qs.webside.activity.service.IActiAwardAddressService;

@Service
public class ActiAwardAddressServiceImpl implements IActiAwardAddressService {

	@Resource
	private ActiAwardAddressMapper  actiAwardAddressMapper ;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return actiAwardAddressMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ActiAwardAddress record) {
		
		return actiAwardAddressMapper.insert(record);
	}

	@Override
	public int insertSelective(ActiAwardAddress record) {
		
		return actiAwardAddressMapper.insertSelective(record);
	}

	@Override
	public ActiAwardAddress selectByPrimaryKey(Integer id) {
		
		return actiAwardAddressMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActiAwardAddress record) {
		
		return actiAwardAddressMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ActiAwardAddress record) {
		
		return actiAwardAddressMapper.updateByPrimaryKey(record);
	}

	@Override
	public ActiAwardAddress selectByMidKey(Integer mid) {
		return actiAwardAddressMapper.selectByMidKey(mid);
	}

	
}
