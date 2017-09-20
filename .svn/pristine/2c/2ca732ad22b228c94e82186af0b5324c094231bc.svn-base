package com.qs.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.sync.model.GameRecord;
import com.qs.warehouse.mapper.GameRecordMapper;
import com.qs.warehouse.service.IGameRecordService;
@Service
public class GameRecordServiceImpl implements IGameRecordService {
	
	@Autowired
	private GameRecordMapper gameRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return gameRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GameRecord record) {
		
		return gameRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(GameRecord record) {

		return gameRecordMapper.insertSelective(record);
		
	}

	@Override
	public GameRecord selectByPrimaryKey(Long id) {
		
		return gameRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GameRecord record) {
		
		return gameRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GameRecord record) {
		
		return gameRecordMapper.updateByPrimaryKey(record);
	}

}
