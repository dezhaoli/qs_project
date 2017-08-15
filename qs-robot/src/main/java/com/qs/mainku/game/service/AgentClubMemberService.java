package com.qs.mainku.game.service;


import com.qs.mainku.game.model.AgentClubMember;

import java.util.List;
import java.util.Map;

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

    /**
     * @param mids amid代理商mid,omid待开房者mid
     * @return
     * @Author:zun.wei , @Date:2017/8/15 13:28
     * @Description:根据代理商mid和待开房者mid获取俱乐部成员
     */
    AgentClubMember getMemberInfoByAmidOmid(Map<String, Object> mids);


}
