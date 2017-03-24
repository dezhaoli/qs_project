package com.qs.log.agent.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.agent.model.TaxesInviteWeek;

public interface TaxesInviteWeekMapper extends IBaseMapper {
    int insert(TaxesInviteWeek record);

    int insertSelective(TaxesInviteWeek record);
    
    TaxesInviteWeek selectByIdTexesInviteWeek(Map<String,Object> param);
}