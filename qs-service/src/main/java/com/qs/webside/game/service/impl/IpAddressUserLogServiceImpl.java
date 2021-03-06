package com.qs.webside.game.service.impl;

import com.qs.webside.game.mapper.IpaddressUseLogMapper;
import com.qs.webside.game.model.IpaddressUseLog;
import com.qs.webside.game.service.IIpAddressUserLogService;
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
public class IpAddressUserLogServiceImpl implements IIpAddressUserLogService {

    @Resource
    private IpaddressUseLogMapper ipaddressUseLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ipaddressUseLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(IpaddressUseLog record) {
        return ipaddressUseLogMapper.insert(record);
    }

    @Override
    public int insertSelective(IpaddressUseLog record) {
        return ipaddressUseLogMapper.insertSelective(record);
    }

    @Override
    public IpaddressUseLog selectByPrimaryKey(Integer id) {
        return ipaddressUseLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(IpaddressUseLog record) {
        return ipaddressUseLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(IpaddressUseLog record) {
        return ipaddressUseLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<IpaddressUseLog> queryListByPage(Map<String, Object> parameter) {
        return ipaddressUseLogMapper.queryListByPage(parameter);
    }

    @Override
    public List<Map<String, Object>> queryIpLogByType(Map<String, Object> parameters) {
        return ipaddressUseLogMapper.queryIpLogByType(parameters);
    }

}
