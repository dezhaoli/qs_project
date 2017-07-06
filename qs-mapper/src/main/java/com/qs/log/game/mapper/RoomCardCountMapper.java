package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.RoomCardCount;

public interface RoomCardCountMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomCardCount record);

    int insertSelective(RoomCardCount record);

    RoomCardCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomCardCount record);

    int updateByPrimaryKey(RoomCardCount record);
}