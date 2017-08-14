package com.qs.mainku.game.service.impl;

import com.qs.mainku.game.mapper.AgentMidsMapper;
import com.qs.mainku.game.model.AgentMids;
import com.qs.mainku.game.service.IAgentMidService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/8/14 11:11.
 * Description:待开房
 */
@Service
public class AgentMidServiceImpl implements IAgentMidService{

    @Resource
    private AgentMidsMapper agentMidsMapper;


    @Override
    public int insert(AgentMids record) {
        return agentMidsMapper.insert(record);
    }

    @Override
    public int insertSelective(AgentMids record) {
        return agentMidsMapper.insertSelective(record);
    }

    @Override
    public AgentMids getAgentGrantByMid(Integer mid) {
        return agentMidsMapper.getAgentGrantByMid(mid);
    }

}
