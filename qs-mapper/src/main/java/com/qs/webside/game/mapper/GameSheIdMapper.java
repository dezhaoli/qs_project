package com.qs.webside.game.mapper;

import com.qs.common.base.basemapper.BaseMapper;
import com.qs.webside.game.model.GameShield;

public interface GameSheIdMapper extends BaseMapper<GameShield, Integer>{
	
	int insertSelective(GameShield record);
	
	int updateByPrimaryKeySelective(GameShield record);
	
	int deleteByPrimaryKey(Integer id);
	
	GameShield selectByPrimaryKey(Integer id);
}
