package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.Club;

public interface ClubMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer clubid);

    int insert(Club record);

    int insertSelective(Club record);

    Club selectByPrimaryKey(Integer clubid);

    int updateByPrimaryKeySelective(Club record);

    int updateByPrimaryKey(Club record);
}