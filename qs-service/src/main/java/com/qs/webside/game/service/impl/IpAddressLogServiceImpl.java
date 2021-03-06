package com.qs.webside.game.service.impl;

import com.qs.webside.game.mapper.IpaddressLogMapper;
import com.qs.webside.game.model.IpaddressLog;
import com.qs.webside.game.service.IIpAddressLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class IpAddressLogServiceImpl implements IIpAddressLogService {

    @Resource
    private IpaddressLogMapper ipaddressLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ipaddressLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(IpaddressLog record) {
        return ipaddressLogMapper.insert(record);
    }

    @Override
    public int insertSelective(IpaddressLog record) {
        return ipaddressLogMapper.insertSelective(record);
    }

    @Override
    public IpaddressLog selectByPrimaryKey(Integer id) {
        return ipaddressLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(IpaddressLog record) {
        return ipaddressLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(IpaddressLog record) {
        return ipaddressLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<IpaddressLog> queryListByPage(Map<String, Object> parameter) {
        return ipaddressLogMapper.queryListByPage(parameter);
    }

}
