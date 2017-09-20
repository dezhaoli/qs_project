package com.qs.warehouse.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.RoomRecord;

public interface RoomRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoomRecord record);

    int insertSelective(RoomRecord record);

    RoomRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomRecord record);

    int updateByPrimaryKey(RoomRecord record);
}