package com.qs.log.agent.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.agent.mapper.AgentDateBaseJobLogMappper;
import com.qs.log.agent.service.IAgentDateBaseJobLogService;

@Service
public class AgentDateBaseJobLogServiceImpl implements IAgentDateBaseJobLogService {

	@Resource
	private AgentDateBaseJobLogMappper agentDateBaseJobLogMappper ;
	
	@Override
	public void updateTaxesInviteWeekDataBase(Map<String, Object> param) {
		agentDateBaseJobLogMappper.updateTaxesInviteWeekDataBase(param);;
	}

}
