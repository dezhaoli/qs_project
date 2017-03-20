package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.GameRecord;

public interface GameRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameRecord record);

    int insertSelective(GameRecord record);

    GameRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameRecord record);

    int updateByPrimaryKey(GameRecord record);
}