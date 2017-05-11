package com.qs.webside.agent.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.agent.model.AgentClubMember;

public interface AgentClubMemberMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentClubMember record);

    int insertSelective(AgentClubMember record);

    AgentClubMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentClubMember record);

    int updateByPrimaryKey(AgentClubMember record);
    
    /**
     * 根据cmid查询当前俱乐部的成员
     * @param cmid
     * @return
     * @author:zyy
     * @time:2017年5月10日
     */
    List<Map<String,Object>> selectByMid(Map<String,Object> parma);
    
    /**
     * 根据cmid mid 查看单成员用户信息
     * @param parma
     * @return
     * @author:zyy
     * @time:2017年5月11日
     */
    AgentClubMember selectByMidInfo(Map<String,Object> parma);
}