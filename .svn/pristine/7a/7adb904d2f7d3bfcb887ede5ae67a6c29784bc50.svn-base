package com.qs.webside.activity.service.impl;

import com.qs.webside.activity.mapper.ActiIntegralMapper;
import com.qs.webside.activity.model.ActiIntegral;
import com.qs.webside.activity.service.IActiIntegralService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/9 14:38.
 * Description:活动中心积分表
 */
@Service
public class ActiIntegralServiceImpl implements IActiIntegralService {

    @Resource
    private ActiIntegralMapper actiIntegralMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiIntegralMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiIntegral record) {
        return actiIntegralMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiIntegral record) {
        return actiIntegralMapper.insertSelective(record);
    }

    @Override
    public ActiIntegral selectByPrimaryKey(Integer id) {
        return actiIntegralMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiIntegral record) {
        return actiIntegralMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiIntegral record) {
        return actiIntegralMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String,Object>> queryListByPage(Map<String, Object> parameters) {
        return actiIntegralMapper.queryListByPage(parameters);
    }

    @Override
    public ActiIntegral selectByMid(Integer mid) {
        return actiIntegralMapper.selectByMid(mid);
    }

}
