package com.qs.log.agent.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.agent.model.AgentUpdateLog;

public interface AgentUpdateLogMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentUpdateLog record);

    int insertSelective(AgentUpdateLog record);

    AgentUpdateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentUpdateLog record);

    int updateByPrimaryKey(AgentUpdateLog record);
}