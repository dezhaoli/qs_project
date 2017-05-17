package com.qs.webside.agent.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.agent.model.AgentClubwhiteList;

public interface AgentClubwhiteListMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentClubwhiteList record);

    int insertSelective(AgentClubwhiteList record);

    AgentClubwhiteList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentClubwhiteList record);

    int updateByPrimaryKey(AgentClubwhiteList record);
    
    /**
     * 更新名单类型
     * @param id
     * @return
     * @author:zyy
     * @time:2017年5月17日
     */
    int updateTakeEffectById(Integer id);
    
    /**
     * 根据mid 查对象
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年5月17日
     */
    AgentClubwhiteList selectByMid(Integer mid);
}