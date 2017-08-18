package com.qs.acti.game.mapper;

import com.qs.acti.game.model.RobotLog;
import com.qs.common.base.basemapper.IBaseMapper;

public interface RobotLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RobotLog record);

    int insertSelective(RobotLog record);

    RobotLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RobotLog record);

    int updateByPrimaryKey(RobotLog record);
}