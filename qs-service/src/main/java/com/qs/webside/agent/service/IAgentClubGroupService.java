package com.qs.webside.agent.service;

import com.qs.webside.agent.model.AgentClubGroup;

public interface IAgentClubGroupService {
	int deleteByPrimaryKey(Integer id);

    int insert(AgentClubGroup record);

    int insertSelective(AgentClubGroup record);

    AgentClubGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentClubGroup record);

    int updateByPrimaryKey(AgentClubGroup record);
    
    /**
     * 根据代理商俱乐部房间查看当前俱乐部信息
     * @param cmid
     * @return
     * @author:zyy
     * @time:2017年5月18日
     */
    AgentClubGroup selectByCmidKeyInfo(Integer cmid);

    /**
     * @Author:zun.wei , @Date:2017/5/24 14:52
     * @Description:根据mid删除俱乐部
     * @param mid
     * @return
     */
    int deleteByMid(Integer mid);
}
