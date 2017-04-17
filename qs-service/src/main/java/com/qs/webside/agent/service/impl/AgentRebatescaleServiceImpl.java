package com.qs.webside.agent.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.webside.agent.mapper.AgentRebatescaleMapper;
import com.qs.webside.agent.model.AgentRebatescale;
import com.qs.webside.agent.service.IAgentRebatescaleService;

@Service
public class AgentRebatescaleServiceImpl implements IAgentRebatescaleService {

	@Resource
	private AgentRebatescaleMapper agentRebatescaleMapper ;
	@Override
	public int insert(AgentRebatescale record) {
		
		return agentRebatescaleMapper.insert(record);
	}

	@Override
	public int insertSelective(AgentRebatescale record) {
		
		return agentRebatescaleMapper.insertSelective(record);
	}

	@Override
	public List<AgentRebatescale> getRebateByMidAllList(Map<String, Object> parma) {
		
		return agentRebatescaleMapper.getRebateByMidAllList(parma);
	}

	@Override
	public int deleteById(Integer id) {
		return agentRebatescaleMapper.deleteById(id);
	}

	@Override
	public AgentRebatescale getRebateById(Integer id) {
		return agentRebatescaleMapper.getRebateById(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AgentRebatescale param) {
		return agentRebatescaleMapper.updateByPrimaryKeySelective(param);
	}

}
