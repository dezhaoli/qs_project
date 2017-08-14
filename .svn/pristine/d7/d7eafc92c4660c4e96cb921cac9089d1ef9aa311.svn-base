package com.qs.webside.robot.service.impl;

import com.qs.webside.robot.mapper.RobotOpenRoomMapper;
import com.qs.webside.robot.model.RobotOpenRoom;
import com.qs.webside.robot.service.IRobotOpenRoomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/8/14 15:31.
 * Description:申请待开房记录表
 */
@Service
public class RobotOpenRoomServiceImpl implements IRobotOpenRoomeService {

    @Resource
    private RobotOpenRoomMapper robotOpenRoomMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return robotOpenRoomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RobotOpenRoom record) {
        return robotOpenRoomMapper.insert(record);
    }

    @Override
    public int insertSelective(RobotOpenRoom record) {
        return robotOpenRoomMapper.insertSelective(record);
    }

    @Override
    public RobotOpenRoom selectByPrimaryKey(Integer id) {
        return robotOpenRoomMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RobotOpenRoom record) {
        return robotOpenRoomMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RobotOpenRoom record) {
        return robotOpenRoomMapper.updateByPrimaryKey(record);
    }

}
