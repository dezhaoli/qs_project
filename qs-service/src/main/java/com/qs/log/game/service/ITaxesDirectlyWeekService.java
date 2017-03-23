package com.qs.log.game.service;

import com.qs.log.game.model.TaxesDirectlyWeek;

import java.util.List;
import java.util.Map;

/**
 * 代理商周统计表
 * Created by zun.wei on 2017/3/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface ITaxesDirectlyWeekService {

    int insert(TaxesDirectlyWeek record);

    int insertSelective(TaxesDirectlyWeek record);

    /**
     * 根据代理商的belongid 和日期获取对象分页列表
     * @param belongIdAndDate belongid  date
     * @return List<TaxesDirectlyWeek>
     */
    List<Map<String,Object>> queryListPageByAgentBelongIdAndSunDayDate(Map<String, Object> belongIdAndDate);


}
