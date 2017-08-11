package com.qs.webside.activity.service.impl;

import com.qs.webside.activity.mapper.ActiSendIntegralMapper;
import com.qs.webside.activity.model.ActiSendIntegral;
import com.qs.webside.activity.service.IActiSendIntegralService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/7/7 11:44.
 * Description:发送积分
 */
@Service
public class ActiSendIntegralServiceImpl implements IActiSendIntegralService {


    @Resource
    private ActiSendIntegralMapper actiSendIntegralMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiSendIntegralMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiSendIntegral record) {
        return actiSendIntegralMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiSendIntegral record) {
        return actiSendIntegralMapper.insertSelective(record);
    }

    @Override
    public ActiSendIntegral selectByPrimaryKey(Integer id) {
        return actiSendIntegralMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiSendIntegral record) {
        return actiSendIntegralMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiSendIntegral record) {
        return actiSendIntegralMapper.updateByPrimaryKey(record);
    }

}
