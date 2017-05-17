package com.qs.webside.member.service;

import java.util.List;
import java.util.Map;

import com.qs.webside.agent.model.AgentClubwhiteList;
import com.qs.webside.member.model.MemberWhiteList;

public interface IAgentClubwhiteListService {

	int deleteByPrimaryKey(Integer id);

    int insert(AgentClubwhiteList record);

    int insertSelective(AgentClubwhiteList record);

    AgentClubwhiteList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentClubwhiteList record);

    int updateByPrimaryKey(AgentClubwhiteList record);
    
    List<AgentClubwhiteList> queryListByPage(Map<String, Object> parameters);

    /**
     * 更新名单类型
     * @param id
     * @return
     * @author:zyy
     * @time:2017年5月17日
     */
    int updateTakeEffectById(Integer id);
    /**
     * 添加白名单
     * @param record
     * @return
     * @author:zyy
     * @time:2017年5月17日
     */
    int addSelective(AgentClubwhiteList record);
    
    /**
     * 根据mid 查对象
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年5月17日
     */
    AgentClubwhiteList selectByMid(Integer mid);
}
