package com.qs.webside.activity.service.impl;

import com.qs.webside.activity.mapper.ActiAwardAddressMapper;
import com.qs.webside.activity.model.ActiAwardAddress;
import com.qs.webside.activity.service.IActiAwardAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/9 15:46.
 * Description:活动中心奖品发放地址
 */
@Service
public class ActiAwaAddServiceImpl implements IActiAwardAddressService {

    @Resource
    private ActiAwardAddressMapper actiAwardAddressMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiAwardAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiAwardAddress record) {
        return actiAwardAddressMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiAwardAddress record) {
        return actiAwardAddressMapper.insertSelective(record);
    }

    @Override
    public ActiAwardAddress selectByPrimaryKey(Integer id) {
        return actiAwardAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiAwardAddress record) {
        return actiAwardAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiAwardAddress record) {
        return actiAwardAddressMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiAwardAddress> queryListByPage(Map<String, Object> parameters) {
        return actiAwardAddressMapper.queryListByPage(parameters);
    }

    @Override
    public ActiAwardAddress selectByMid(Integer mid) {
        return actiAwardAddressMapper.selectByMid(mid);
    }

}
