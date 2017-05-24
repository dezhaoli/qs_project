package com.qs.webside.member.service;

import com.qs.webside.member.model.Memberagents;

/**
 * Created by zun.wei on 2017/5/24 13:53.
 * Description:代理商表
 */
public interface IMemberAgentService {

    int deleteByPrimaryKey(Integer id);

    int insert(Memberagents record);

    int insertSelective(Memberagents record);

    Memberagents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Memberagents record);

    int updateByPrimaryKey(Memberagents record);
    /**
     * 通过mid查询代理商
     * @param mid
     * @return
     */
    Memberagents findMemberagentsByMid(Integer mid);

}
