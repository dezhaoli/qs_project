package com.qs.acti.game.service.impl;

import com.qs.acti.game.mapper.ActiRedPacketCfgMapper;
import com.qs.acti.game.model.ActiRedPacketCfg;
import com.qs.acti.game.service.IActiRedPacketService;
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
    public Integer queryStoreByActiType(Integer actiType) {
        return actiRedPacketCfgMapper.queryStoreByActiType(actiType);
    }

}
