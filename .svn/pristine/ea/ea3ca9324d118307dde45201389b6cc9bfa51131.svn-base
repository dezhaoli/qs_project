package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.RoomRecord;

public interface RoomRecordMapper extends IBaseMapper<RoomRecord,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(RoomRecord record);

    int insertSelective(RoomRecord record);

    RoomRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomRecord record);

    int updateByPrimaryKey(RoomRecord record);
    /**
     * 获取牌局回放数据
     * @param uuid
     * @return
     */
    RoomRecord getPaiJuData(RoomRecord roomRecord);
}