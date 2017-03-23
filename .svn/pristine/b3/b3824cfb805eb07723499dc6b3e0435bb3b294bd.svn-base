package com.qs.log.game.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.TaxesDirectlyWeek;

import java.util.List;
import java.util.Map;

public interface TaxesDirectlyWeekMapper extends IBaseMapper {

    int insert(TaxesDirectlyWeek record);

    int insertSelective(TaxesDirectlyWeek record);

    /**
     * 根据代理商的belongid 和日期获取对象分页列表
     * @param belongIdAndDate belongid  date
     * @return List<TaxesDirectlyWeek>
     */
    List<Map<String,Object>> queryListPageByAgentBelongIdAndSunDayDate(Map<String, Object> belongIdAndDate);


}