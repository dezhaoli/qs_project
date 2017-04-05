package com.qs.agent.game.mapper;

import com.qs.agent.game.model.MemberAgents;
import com.qs.common.base.basemapper.IBaseMapper;

public interface MemberAgentsMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberAgents record);

    int insertSelective(MemberAgents record);

    MemberAgents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberAgents record);

    int updateByPrimaryKey(MemberAgents record);
    
    /**
     * 通过openid查询代理商信息
     * @param openid
     * @return
     */
    MemberAgents findByMid(Integer mid);


}