package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.TaxesInviteWeek;

import java.util.List;
import java.util.Map;

public interface TaxesInviteWeekMapper extends IBaseMapper {
    int insert(TaxesInviteWeek record);

    int insertSelective(TaxesInviteWeek record);

    /**
     * //@Author:zun.wei, @Date:2017/4/7 12:30
     * 代理商历史结算
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getHistoryAgentsRebateList(Map<String, Object> parameters);

}