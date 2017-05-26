package com.qs.pub.sync.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.sync.model.SyncCreateRoom;

public interface CreateRoomMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SyncCreateRoom record);
}