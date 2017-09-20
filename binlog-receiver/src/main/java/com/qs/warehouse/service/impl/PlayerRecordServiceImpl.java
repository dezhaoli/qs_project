package com.qs.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.sync.model.PlayerRecord;
import com.qs.warehouse.mapper.PlayerRecordMapper;
import com.qs.warehouse.service.IPlayerRecordService;


@Service
public class PlayerRecordServiceImpl implements IPlayerRecordService {
	
	@Autowired
	private PlayerRecordMapper playerRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return playerRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PlayerRecord record) {
		
		return playerRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(PlayerRecord record) {

		return playerRecordMapper.insertSelective(record);
	}

	@Override
	public PlayerRecord selectByPrimaryKey(Long id) {
		
		return playerRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PlayerRecord record) {
		
		return playerRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PlayerRecord record) {
		
		return playerRecordMapper.updateByPrimaryKey(record);
	}

	
}
