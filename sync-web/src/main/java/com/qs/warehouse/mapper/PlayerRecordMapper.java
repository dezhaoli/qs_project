package com.qs.warehouse.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.warehouse.model.PlayerRecord;

public interface PlayerRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlayerRecord record);

    int insertSelective(PlayerRecord record);

    PlayerRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerRecord record);

    int updateByPrimaryKey(PlayerRecord record);
}