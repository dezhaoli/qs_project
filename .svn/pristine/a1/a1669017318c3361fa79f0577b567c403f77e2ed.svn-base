package com.qs.log.game.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.TaxesDirectlyDay;

public interface TaxesDirectlyDayMapper extends IBaseMapper {
    int insert(TaxesDirectlyDay record);

    int insertSelective(TaxesDirectlyDay record);
    /**
     * 直属会员周信息统计
     * @param param
     * @return
     */
    TaxesDirectlyDay getVipWeekDataStatQuery(Map<String,Object> param);
    
    /**
     * 直属会员周信息统计详情
     * @param param
     * @return
     */
    List<TaxesDirectlyDay> getVipWeekDataStatDetailQuery(Map<String,Object> param);
}