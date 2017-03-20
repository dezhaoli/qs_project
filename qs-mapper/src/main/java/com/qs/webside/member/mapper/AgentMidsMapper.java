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

}