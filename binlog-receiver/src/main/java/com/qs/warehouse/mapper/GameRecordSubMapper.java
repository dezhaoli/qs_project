package com.qs.warehouse.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.GameRecordSub;

public interface GameRecordSubMapper extends IBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameRecordSub record);

    int insertSelective(GameRecordSub record);

    GameRecordSub selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameRecordSub record);

    int updateByPrimaryKey(GameRecordSub record);
}