package com.qs.warehouse.service;

import com.qs.sync.model.GameRecord;

public interface IGameRecordService {

	  int deleteByPrimaryKey(Long id);

	    int insert(GameRecord record);

	    int insertSelective(GameRecord record);

	    GameRecord selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(GameRecord record);

	    int updateByPrimaryKey(GameRecord record);
}
