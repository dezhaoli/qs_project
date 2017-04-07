package com.qs.log.game.service;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.qs.log.game.model.TaxesDirectlyWeek;

import java.util.List;
import java.util.Map;

/**
 * //@Author:zun.wei, @Date:2017/4/5 14:08
 * 周统计表
 * Created by zun.wei on 2017/4/5.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface ITaxesDirectlyWeekService {

    int insert(TaxesDirectlyWeek record);

    int insertSelective(TaxesDirectlyWeek record);

    /**
     * //@Author:zun.wei, @Date:2017/4/5 15:48
     * 根据时间获取周结算发放
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getWeekPayinfoByDate(Map<String, Object> parameters);

    
    /**
     *  查询周结算记录
     * @param record
     * @return
     */
    TaxesDirectlyWeek  findTaxesDirectlyWeekByCondition(TaxesDirectlyWeek record);
    
    /***
     * 更新为已经支付
     * @param record
     * @return
     */
    int updateIsawardByCondition(TaxesDirectlyWeek record);

    /**
     * //@Author:zun.wei, @Date:2017/4/7 15:53
     *  获取周结算历史详情
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getWeekPayHistoryDetailInfoByDate(Map<String, Object> parameters);

}
