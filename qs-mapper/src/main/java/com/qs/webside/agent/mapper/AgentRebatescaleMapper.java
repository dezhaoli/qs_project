package com.qs.webside.agent.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.agent.model.AgentRebatescale;

public interface AgentRebatescaleMapper extends IBaseMapper {
    int insert(AgentRebatescale record);

    int insertSelective(AgentRebatescale record);
    
    /**
     * 通过mid或不带mid获取Rebate列表信息
     * @param parma
     * @return
     * @author:zyy
     * @time:2017年4月17日
     */
    List<AgentRebatescale> getRebateByMidAllList(Map<String,Object> parma);
    
    int deleteById(Integer id);
    
    AgentRebatescale getRebateById(Integer id);
    
    int updateByPrimaryKeySelective(AgentRebatescale param);
}