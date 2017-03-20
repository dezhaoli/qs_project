package com.qs.log.game.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.GameRecordShare;

public interface GameRecordShareMapper extends IBaseMapper<GameRecordShare,Integer> {
    int deleteByPrimaryKey(Integer sid);

    int insert(GameRecordShare record);

    int insertSelective(GameRecordShare record);

    GameRecordShare selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(GameRecordShare record);

    int updateByPrimaryKey(GameRecordShare record);
    /**
     * 获取牌局回放数据
     * @param sid
     * @return
     */
    public Map<String, Object> getShareGameRecord(Integer sid);
 
}