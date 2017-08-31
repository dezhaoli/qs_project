package com.qs.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.drc.clusterclient.message.ClusterMessage;
import com.qs.warehouse.mapper.GameRecordSubMapper;
import com.qs.warehouse.model.GameRecordSub;
import com.qs.warehouse.service.IGameRecordSubService;

@Service
public class GameRecordSubServiceImpl implements IGameRecordSubService {
	
	@Autowired
	private GameRecordSubMapper gameRecordSubMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return gameRecordSubMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GameRecordSub record) {
		
		return gameRecordSubMapper.insert(record);
	}

	@Override
	public int insertSelective(GameRecordSub record) {
		
		return gameRecordSubMapper.insertSelective(record);
	}

	@Override
	public GameRecordSub selectByPrimaryKey(Long id) {
		
		return gameRecordSubMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GameRecordSub record) {
		
		return gameRecordSubMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GameRecordSub record) {
		
		return gameRecordSubMapper.updateByPrimaryKey(record);
	}

}
