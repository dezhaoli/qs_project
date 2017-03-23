package com.qs.webside.sys.service.agent.service;

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

}
