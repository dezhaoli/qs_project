package com.qs.log.game.service;

import java.util.List;
import java.util.Map;

import com.qs.log.game.model.TaxesDirectlyDay;

public interface ITaxesDirectlyDayService {

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
