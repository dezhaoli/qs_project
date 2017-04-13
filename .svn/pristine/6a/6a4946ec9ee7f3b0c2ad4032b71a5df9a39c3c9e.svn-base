package com.qs.webside.agent.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.webside.agent.mapper.AgentDateBaseJobMappper;
import com.qs.webside.agent.service.IAgentDateBaseJobService;

@Service
public class AgentDateBaseJobServiceImpl implements IAgentDateBaseJobService {

	@Resource
	private AgentDateBaseJobMappper agentDateBaseJobMappper ;
	
	@Override
	public void updateCommonAgentDataBase(Map<String, Object> param) {
		agentDateBaseJobMappper.updateCommonAgentDataBase(param);
	}

}
