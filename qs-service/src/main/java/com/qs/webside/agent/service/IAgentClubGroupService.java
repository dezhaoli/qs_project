package com.qs.webside.agent.service;

import com.qs.webside.agent.model.AgentClubGroup;

public interface IAgentClubGroupService {
	int deleteByPrimaryKey(Integer id);

    int insert(AgentClubGroup record);

    int insertSelective(AgentClubGroup record);

    AgentClubGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentClubGroup record);

    int updateByPrimaryKey(AgentClubGroup record);

}
