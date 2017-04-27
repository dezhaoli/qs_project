package com.qs.pub.game.service.impl;

import com.qs.pub.game.service.ICommonAgentInfoService;
import com.qs.pub.game.mapper.CommonAgentsInfoMapper;
import com.qs.pub.game.model.CommonAgentsInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/8.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class CommonAgentInfoServiceImpl implements ICommonAgentInfoService {

    @Resource
    private CommonAgentsInfoMapper commonAgentsInfoMapper;

    @Override
    public int deleteByPrimaryKey(String sitemid) {
        return commonAgentsInfoMapper.deleteByPrimaryKey(sitemid);
    }

    @Override
    public int insert(CommonAgentsInfo record) {
        return commonAgentsInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(CommonAgentsInfo record) {
        return commonAgentsInfoMapper.insertSelective(record);
    }

    @Override
    public CommonAgentsInfo selectByPrimaryKey(String sitemid) {
        return commonAgentsInfoMapper.selectByPrimaryKey(sitemid);
    }

    @Override
    public int updateByPrimaryKeySelective(CommonAgentsInfo record) {
        return commonAgentsInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommonAgentsInfo record) {
        return commonAgentsInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CommonAgentsInfo> queryListByPage(Map<String, Object> parameters) {
        return commonAgentsInfoMapper.queryListByPage(parameters);
    }

    @Override
    public List<Map<String, Object>> queryFirstAgentByBelongIdPage(Map<String, Object> belongid) {
        return commonAgentsInfoMapper.queryFirstAgentByBelongIdPage(belongid);
    }

}
