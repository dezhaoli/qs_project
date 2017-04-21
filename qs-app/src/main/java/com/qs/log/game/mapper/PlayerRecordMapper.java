package com.qs.log.game.mapper;

import org.apache.ibatis.annotations.Param;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.PlayerRecord;

public interface PlayerRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlayerRecord record);

    int insertSelective(PlayerRecord record);

    PlayerRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerRecord record);

    int updateByPrimaryKey(PlayerRecord record);
    
    
    int getPlayCount(@Param("mid") int mid,@Param("gameType") byte gameType);
    
    
}