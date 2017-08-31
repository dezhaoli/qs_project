package com.qs.warehouse.service;

import com.aliyun.drc.clusterclient.message.ClusterMessage;
import com.qs.warehouse.model.GameRecord;
import com.qs.warehouse.model.GoldLog;

public interface IGoldLogService {

	   int deleteByPrimaryKey(Long id);

	    int insert(GoldLog record);

	    int insertSelective(GoldLog record);

	    GoldLog selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(GoldLog record);

	    int updateByPrimaryKey(GoldLog record);
}
