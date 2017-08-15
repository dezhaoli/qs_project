package com.qs.mainku.game.service;


import com.qs.mainku.game.model.AgentClubGroup;

public interface AgentClubGroupService {

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
}
