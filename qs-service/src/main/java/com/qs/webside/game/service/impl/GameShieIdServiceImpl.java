package com.qs.webside.game.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.webside.game.mapper.GameSheIdMapper;
import com.qs.webside.game.model.GameShield;
import com.qs.webside.game.service.GameShieIdService;

@Service
public class GameShieIdServiceImpl implements GameShieIdService{
	
	@Autowired
	private GameSheIdMapper gameSheIdMapper;
	
	@Override
	public List<GameShield> queryListByPage(Map<String, Object> parameters) {
		return gameSheIdMapper.queryListByPage(parameters);
	}

	@Override
	public int add(GameShield record) {
		return gameSheIdMapper.insertSelective(record);
	}

	@Override
	public int update(GameShield record) {
		return gameSheIdMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Integer id) {
		return gameSheIdMapper.deleteByPrimaryKey(id);
	}

	@Override
	public GameShield findById(Integer id) {
		return gameSheIdMapper.selectByPrimaryKey(id);
	}

}
