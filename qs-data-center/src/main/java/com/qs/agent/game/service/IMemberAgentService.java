package com.qs.agent.game.service;

import com.qs.agent.game.model.MemberAgents;

/**
 *  //@Author:zun.wei, @Date:2017/4/5 13:59
 *  代理商表
 * Created by zun.wei on 2017/4/5.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IMemberAgentService {

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAgents record);

    int insertSelective(MemberAgents record);

     MemberAgents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberAgents record);

    int updateByPrimaryKey(MemberAgents record);
    
    MemberAgents findByMid(Integer mid);

}
