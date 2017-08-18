package com.qs.acti.game.service.impl;

import com.qs.acti.game.mapper.RobotMapper;
import com.qs.acti.game.model.Robot;
import com.qs.acti.game.service.IRobotService;
import com.qs.common.util.RandomUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/8/18 9:42.
 * Description:
 */
@Service
public class RobotServiceImpl implements IRobotService {

    @Resource
    private RobotMapper robotMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return robotMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Robot record) {
        return robotMapper.insert(record);
    }

    @Override
    public int insertSelective(Robot record) {
        return robotMapper.insertSelective(record);
    }

    @Override
    public Robot selectByPrimaryKey(Integer id) {
        return robotMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Robot record) {
        return robotMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Robot record) {
        return robotMapper.updateByPrimaryKey(record);
    }

    @Override
    public int checkAuthCodeOrMidExist(Map<String, Object> parameters) {
        return robotMapper.checkAuthCodeOrMidExist(parameters);
    }

    @Override
    public int updateActivationTo1(Robot record) {
        return robotMapper.updateActivationTo1(record);
    }

    @Override
    public int queryCountByAuthCode(int authCode) {
        return robotMapper.queryCountByAuthCode(authCode);
    }

    @Override
    public Robot selectByMid(Integer mid) {
        return robotMapper.selectByMid(mid);
    }

    @Override
    public List<Robot> queryListByPage(Map<String, Object> parameters) {
        return robotMapper.queryListByPage(parameters);
    }

    @Override
    public int getOneRandomAuthCode() {
        while (true) {
            int ramdomCode = RandomUtil.generateAuthCode();
            int code = robotMapper.queryCountByAuthCode(ramdomCode);
            if (code == 0) return ramdomCode;
        }
    }
}
