package com.qs.webside.activity.service.impl;

import com.qs.webside.activity.mapper.ActiRedPacketCfgMapper;
import com.qs.webside.activity.model.ActiRedPacketCfg;
import com.qs.webside.activity.service.IActiRedPacketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/9/19 13:41.
 * Description:活动中心红包接口
 */
@Service
public class ActiRedPacketServiceImpl implements IActiRedPacketService {

    @Resource
    private ActiRedPacketCfgMapper actiRedPacketCfgMapper;


    @Override
    public List<ActiRedPacketCfg> queryListByPage(Map<String, Object> parameters) {
        return actiRedPacketCfgMapper.queryListByPage(parameters);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actiRedPacketCfgMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ActiRedPacketCfg record) {
        return actiRedPacketCfgMapper.insert(record);
    }

    @Override
    public int insertSelective(ActiRedPacketCfg record) {
        return actiRedPacketCfgMapper.insertSelective(record);
    }

    @Override
    public ActiRedPacketCfg selectByPrimaryKey(Integer id) {
        return actiRedPacketCfgMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ActiRedPacketCfg record) {
        return actiRedPacketCfgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActiRedPacketCfg record) {
        return actiRedPacketCfgMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ActiRedPacketCfg> queryListByActiType(Integer actiType) {
        return actiRedPacketCfgMapper.queryListByActiType(actiType);
    }

    @Override
    public int updateStockByReduceAndId(Map<String, Object> parameters) {
        return actiRedPacketCfgMapper.updateStockByReduceAndId(parameters);
    }

}
