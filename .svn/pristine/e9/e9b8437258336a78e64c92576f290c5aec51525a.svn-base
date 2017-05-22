package com.qs.log.game.service;

import com.qs.log.game.model.RoomRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/22 16:10.
 * Description:房间记录表
 */
public interface IRoomRecordService {

    int deleteByPrimaryKey(Long id);

    int insert(RoomRecord record);

    int insertSelective(RoomRecord record);

    RoomRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomRecord record);

    int updateByPrimaryKey(RoomRecord record);

    List<RoomRecord> queryListByPage(Map<String, Object> parameter);

    /**
     * 根据房间编号查询
     * @param uuid
     * @return
     */
    RoomRecord queryByUuid(String uuid);

}
