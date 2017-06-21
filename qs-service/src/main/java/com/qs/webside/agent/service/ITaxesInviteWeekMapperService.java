package com.qs.webside.agent.service;

import java.util.List;
import java.util.Map;

import com.qs.log.agent.model.TaxesInviteWeek;

public interface ITaxesInviteWeekMapperService {
	
	int insert(TaxesInviteWeek record);

    int insertSelective(TaxesInviteWeek record);
    
    Map<String, Object> selectByIdTexesInviteWeek(Map<String,Object> param);

    /**
     *  根据日期获取代理商结算列表
     * @param parameters
     * @return
     */
    List<TaxesInviteWeek> findInfoRebatetotalByAgentMidDate(Map<String, Object> parameters);

    /**
     *  根据日期获取代理商结算列表以及代理商正式名字
     * @param parameters
     * @param weekPayTotal
     *@param weekSettleTotal @return
     */
    List<Map<String, Object>> findMidPaytotalRebatetotalIsawardInfoAgentRealname(Map<String, Object> parameters, Double weekPayTotal, Float weekSettleTotal);

    /**
     * 获取代理商结算详情
     *
     * @return
     */
    Map<String, Object> getPayDetail(Map<String, Object> parameters);

    /**
     *  获取代理商结算审核
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getWeekPayCheckInfoByDate(Map<String, Object> parameters);

    /**
     * 审核周结算
     * @param parameters
     * @return
     */
    int updateWeekPayCheckInfoByDate(Map<String, Object> parameters);

    /**
     * //@Author:zun.wei, @Date:2017/4/13 13:44
     *  单独审核mid
     * @param parameters
     * @return
     */
    int updateWeekPayCheckInfoByMidDate(Map<String, Object> parameters);

    /**
     * //@Author:zun.wei, @Date:2017/4/13 14:46
     * 获取本周返利总额
     * @param date
     * @return
     */
    int getSumRebateTotalByDate(String date);

    /**
     * //@Author:zun.wei, @Date:2017/4/19 15:06
     * 获取代理商历史结算审核
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getHistoryAgentsRebateList(Map<String, Object> parameters);

    /**
     * //@Author:zun.wei, @Date:2017/4/19 15:21
     *  获取周结算历史审核详情
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getWeekPayHistoryDetailInfoByDate(Map<String, Object> parameters);

}
