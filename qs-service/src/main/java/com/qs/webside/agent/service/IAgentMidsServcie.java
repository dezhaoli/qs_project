package com.qs.webside.agent.service;

import com.qs.webside.member.model.AgentMids;

/**
 * Created by zun.wei on 2017/3/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IAgentMidsServcie {

    int insert(AgentMids record);

    int insertSelective(AgentMids record);

    /**
     * 根据Mid删除记录
     * @param mid
     * @return
     */
    int deleteByMid(Integer mid);

    /**
     * 根据mid查询信息
     * @param mid
     * @return
     */
    AgentMids selectByMid(Integer mid);

    /**
     * @Author:zun.wei , @Date:2017/6/12 13:22
     * @Description:根据代理商mid删除所有待开房记录。
     * @param amid
     * @return
     */
    int deleteByAgentMid(Integer amid);

}
