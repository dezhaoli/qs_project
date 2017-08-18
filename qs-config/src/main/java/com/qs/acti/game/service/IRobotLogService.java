package com.qs.acti.game.service;


import com.qs.acti.game.model.RobotLog;

/**
 * Created by zun.wei on 2017/8/14 9:09.
 * Description:机器人日志
 */
public interface IRobotLogService {

    int deleteByPrimaryKey(Integer id);

    int insert(RobotLog record);

    int insertSelective(RobotLog record);

    RobotLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RobotLog record);

    int updateByPrimaryKey(RobotLog record);

}
