package com.qs.webside.member.service;

import java.util.List;
import java.util.Map;

import com.qs.webside.member.model.AgentClubMember;

public interface AgentClubMemberService {

	int deleteByPrimaryKey(Integer id);

    int insert(AgentClubMember record);

    int insertSelective(AgentClubMember record);

    AgentClubMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentClubMember record);

    int updateByPrimaryKey(AgentClubMember record);
    
    /**
     * 根据mid 获取当前用户的所有俱乐部组
     * @param mid
     * @return  List<Map<String,Object>>
     * @author:zyy
     * @time:2017年5月11日
     */
    List<Map<String,Object>> getClubGroupInfo(Integer mid);
}
