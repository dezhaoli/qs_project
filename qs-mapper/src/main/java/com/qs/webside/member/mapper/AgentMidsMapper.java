package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.AgentMids;

public interface AgentMidsMapper extends IBaseMapper {
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