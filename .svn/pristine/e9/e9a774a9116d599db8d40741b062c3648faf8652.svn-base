package com.qs.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.sync.model.GoldLog;
import com.qs.warehouse.mapper.GoldLogMapper;
import com.qs.warehouse.service.IGoldLogService;
@Service
public class GoldLogServiceImpl implements IGoldLogService {
	
	@Autowired
	private GoldLogMapper goldLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return goldLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GoldLog record) {
		
		return goldLogMapper.insert(record);
	}

	@Override
	public int insertSelective(GoldLog record) {

		return goldLogMapper.insertSelective(record);
	}

	@Override
	public GoldLog selectByPrimaryKey(Long id) {
		
		return goldLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GoldLog record) {
		
		return goldLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GoldLog record) {
		
		return goldLogMapper.updateByPrimaryKey(record);
	}

	
}
