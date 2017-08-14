package com.qs.webside.robot.service.impl;

import com.qs.webside.robot.mapper.RobotFriendsMapper;
import com.qs.webside.robot.model.RobotFriends;
import com.qs.webside.robot.service.IRobotFriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/8/14 9:10.
 * Description:机器人好友
 */
@Service
public class RobotFriendServiceImpl implements IRobotFriendService {

    @Resource
    private RobotFriendsMapper robotFriendsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return robotFriendsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RobotFriends record) {
        return robotFriendsMapper.insert(record);
    }

    @Override
    public int insertSelective(RobotFriends record) {
        return robotFriendsMapper.insertSelective(record);
    }

    @Override
    public RobotFriends selectByPrimaryKey(Integer id) {
        return robotFriendsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RobotFriends record) {
        return robotFriendsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RobotFriends record) {
        return robotFriendsMapper.updateByPrimaryKey(record);
    }

}
