package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.PlayerRecord;

public interface PlayerRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlayerRecord record);

    int insertSelective(PlayerRecord record);

    PlayerRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerRecord record);

    int updateByPrimaryKey(PlayerRecord record);
}