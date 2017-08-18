package com.qs.acti.game.service.impl;

import com.qs.acti.game.model.RobotFriends;
import com.qs.acti.game.mapper.RobotFriendsMapper;
import com.qs.acti.game.service.IRobotFriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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

    @Override
    public RobotFriends queryRobotFriendByCodeAndMid(Map<String, Object> parameters) {
        return robotFriendsMapper.queryRobotFriendByCodeAndMid(parameters);
    }

    @Override
    public int checkAgentHasAuth(Map<String, Object> parameters) {
        return robotFriendsMapper.checkAgentHasAuth(parameters);
    }

}
