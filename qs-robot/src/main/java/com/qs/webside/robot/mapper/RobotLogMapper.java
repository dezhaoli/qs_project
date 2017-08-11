package com.qs.webside.robot.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.robot.model.RobotLog;

public interface RobotLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RobotLog record);

    int insertSelective(RobotLog record);

    RobotLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RobotLog record);

    int updateByPrimaryKey(RobotLog record);
}