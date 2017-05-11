package com.qs.webside.agent.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.agent.model.AgentClubGroup;

public interface AgentClubGroupMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentClubGroup record);

    int insertSelective(AgentClubGroup record);

    AgentClubGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentClubGroup record);

    int updateByPrimaryKey(AgentClubGroup record);
}