package com.qs.warehouse.service;

import com.qs.sync.model.GameRecordSub;

public interface IGameRecordSubService {

	int deleteByPrimaryKey(Long id);

    int insert(GameRecordSub record);

    int insertSelective(GameRecordSub record);

    GameRecordSub selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameRecordSub record);

    int updateByPrimaryKey(GameRecordSub record);
}
