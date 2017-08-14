package com.qs.mainku.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.mainku.game.model.AgentMids;

public interface AgentMidsMapper extends IBaseMapper {
    int insert(AgentMids record);

    int insertSelective(AgentMids record);
    /**
     * 查看是否授权待开房
     * @param mid
     * @return
     */
    AgentMids getAgentGrantByMid(Integer mid);
    
}