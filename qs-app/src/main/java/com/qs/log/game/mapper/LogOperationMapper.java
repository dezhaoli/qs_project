package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.LogOperation;

public interface LogOperationMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogOperation record);

    int insertSelective(LogOperation record);

    LogOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogOperation record);

    int updateByPrimaryKeyWithBLOBs(LogOperation record);

    int updateByPrimaryKey(LogOperation record);
}