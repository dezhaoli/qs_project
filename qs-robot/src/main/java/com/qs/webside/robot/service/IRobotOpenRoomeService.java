package com.qs.webside.robot.service;

import com.qs.webside.robot.model.RobotOpenRoom;

/**
 * Created by zun.wei on 2017/8/14 15:31.
 * Description:
 */
public interface IRobotOpenRoomeService {

    int deleteByPrimaryKey(Integer id);

    int insert(RobotOpenRoom record);

    int insertSelective(RobotOpenRoom record);

    RobotOpenRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RobotOpenRoom record);

    int updateByPrimaryKey(RobotOpenRoom record);

}
