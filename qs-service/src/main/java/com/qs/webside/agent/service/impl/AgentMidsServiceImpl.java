package com.qs.webside.agent.service.impl;

import com.qs.webside.member.mapper.AgentMidsMapper;
import com.qs.webside.member.model.AgentMids;
import com.qs.webside.agent.service.IAgentMidsServcie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/3/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class AgentMidsServiceImpl implements IAgentMidsServcie {

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
    public int deleteByMid(Integer mid) {
        return agentMidsMapper.deleteByMid(mid);
    }

	@Override
	public AgentMids selectByMid(Integer mid) {
		return agentMidsMapper.selectByMid(mid);
	}

    @Override
    public int deleteByAgentMid(Integer amid) {
        return agentMidsMapper.deleteByAgentMid(amid);
    }

}
