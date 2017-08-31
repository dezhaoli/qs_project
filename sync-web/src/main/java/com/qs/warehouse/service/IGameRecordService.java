package com.qs.warehouse.service;

import com.aliyun.drc.clusterclient.message.ClusterMessage;
import com.qs.warehouse.model.GameRecord;

public interface IGameRecordService {

	  int deleteByPrimaryKey(Long id);

	    int insert(GameRecord record);

	    int insertSelective(GameRecord record);

	    GameRecord selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(GameRecord record);

	    int updateByPrimaryKey(GameRecord record);
}
