package com.qs.webside.game.service.impl;

import com.qs.common.constant.CacheConstan;
import com.qs.pub.sys.model.UserEntity;
import com.qs.webside.game.mapper.IpaddressMapper;
import com.qs.webside.game.model.Ipaddress;
import com.qs.webside.game.model.IpaddressLog;
import com.qs.webside.game.service.IIpAddressLogService;
import com.qs.webside.game.service.IIpAddressService;

import org.apache.shiro.SecurityUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private IIpAddressLogService ipAddressLogService;

    @Override
    //@Cacheable(value={CacheConstan.IP_ADDRESS},key="#root.methodName")
    public List<Ipaddress> queryListByPage(Map<String, Object> parameters) {
        return ipaddressMapper.queryListByPage(parameters);
    }

    @Override
    public Ipaddress findById(Integer id) {
        return ipaddressMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value={CacheConstan.IP_ADDRESS_CACHE_STORE_NAME},allEntries=true)
    public int add(Ipaddress ipaddress) {
        IpaddressLog ipaddressLog = new IpaddressLog();
        ipaddressLog.setIpId(ipaddress.getId());
        ipaddressLog.setIpstring(ipaddress.getIpstring());
        ipaddressLog.setName(ipaddress.getName());
        ipaddressLog.setUpdatetime(new Date());
        ipaddressLog.setType(ipaddress.getType());
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        ipaddressLog.setModifierId(userEntity.getId().intValue());
        int insertLog = ipAddressLogService.insertSelective(ipaddressLog);
        if (insertLog == 0) return insertLog;
        return ipaddressMapper.insertSelective(ipaddress);
    }

    @Override
    @CacheEvict(value={CacheConstan.IP_ADDRESS_CACHE_STORE_NAME},allEntries=true)
    public int deleteById(Integer id) {
        return ipaddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value={CacheConstan.IP_ADDRESS_CACHE_STORE_NAME},allEntries=true)
    public int update(Ipaddress ipaddress) {
        //@Author:zun.wei, @Date:2017/4/14 10:25 插入修改日志。
        IpaddressLog ipaddressLog = new IpaddressLog();
        ipaddressLog.setIpId(ipaddress.getId());
        ipaddressLog.setIpstring(ipaddress.getIpstring());
        ipaddressLog.setName(ipaddress.getName());
        ipaddressLog.setUpdatetime(new Date());
        ipaddressLog.setType(ipaddress.getType());
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        ipaddressLog.setModifierId(userEntity.getId().intValue());
        int insertLog = ipAddressLogService.insertSelective(ipaddressLog);
        if (insertLog == 0) return insertLog;
        return ipaddressMapper.updateByPrimaryKeySelective(ipaddress);
    }

}
