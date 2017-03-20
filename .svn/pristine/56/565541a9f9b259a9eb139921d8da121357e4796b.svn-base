package com.qs.webside.sys.service.game.service.impl;

import com.qs.webside.game.mapper.IpaddressMapper;
import com.qs.webside.game.model.Ipaddress;
import com.qs.webside.sys.service.game.service.IIpAddressService;
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
public class IpAddressServiceImpl implements IIpAddressService {

    @Resource
    private IpaddressMapper ipaddressMapper;

    @Override
    public List<Ipaddress> queryListByPage(Map<String, Object> parameters) {
        return ipaddressMapper.queryListByPage(parameters);
    }

    @Override
    public Ipaddress findById(Integer id) {
        return ipaddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Ipaddress ipaddress) {
        return ipaddressMapper.insertSelective(ipaddress);
    }

    @Override
    public int deleteById(Integer id) {
        return ipaddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Ipaddress ipaddress) {
        return ipaddressMapper.updateByPrimaryKeySelective(ipaddress);
    }

}
