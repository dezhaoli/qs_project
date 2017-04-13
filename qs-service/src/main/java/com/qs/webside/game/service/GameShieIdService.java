package com.qs.webside.game.service;

import java.util.List;
import java.util.Map;

import com.qs.webside.game.model.GameShield;

public interface GameShieIdService {
	
	public List<GameShield> queryListByPage(Map<String, Object> parameters);
	
	public int add(GameShield record);
	
	public int update(GameShield record);
	
	public int delete(Integer id);
	
	public GameShield findById(Integer id);
	
}
