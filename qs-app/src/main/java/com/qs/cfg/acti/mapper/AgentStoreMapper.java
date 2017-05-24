package com.qs.cfg.acti.mapper;

import com.qs.cfg.acti.model.AgentStore;
import com.qs.common.base.basemapper.IBaseMapper;

public interface AgentStoreMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentStore record);

    int insertSelective(AgentStore record);

    AgentStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentStore record);

    int updateByPrimaryKey(AgentStore record);

    AgentStore getGoldByPayMoney(Integer money);

}