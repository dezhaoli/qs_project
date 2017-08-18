package com.qs.acti.game.service.impl;

import com.qs.acti.game.mapper.RobotLogMapper;
import com.qs.acti.game.model.RobotLog;
import com.qs.acti.game.service.IRobotLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/8/14 9:11.
 * Description:机器人日志
 */
@Service
public class RobotLogServiceImpl implements IRobotLogService {

    @Resource
    private RobotLogMapper robotLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return robotLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RobotLog record) {
        return robotLogMapper.insert(record);
    }

    @Override
    public int insertSelective(RobotLog record) {
        return robotLogMapper.insertSelective(record);
    }

    @Override
    public RobotLog selectByPrimaryKey(Integer id) {
        return robotLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RobotLog record) {
        return robotLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RobotLog record) {
        return robotLogMapper.updateByPrimaryKey(record);
    }

}
