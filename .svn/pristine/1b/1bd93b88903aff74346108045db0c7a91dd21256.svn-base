package com.qs.log.game.service;

import com.qs.log.game.model.TaxesInviteWeek;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: ITaxesInviteWeekService 
 * @描述: 周结算支付
 * @author moys
 * @date 2017年7月11日 下午7:41:28
 */
public interface ITaxesInviteWeekService {

    int insert(TaxesInviteWeek record);

    int insertSelective(TaxesInviteWeek record);

    /**
     * 
     * @标题: getHistoryAgentsRebateList 
     * @描述: 代理商历史结算
     *
     * @参数信息
     *    @param parameters
     *    @return
     *
     * @返回类型 List<Map<String,Object>>
     * @开发者 moys
     * @可能抛出异常
     */
    List<Map<String, Object>> getHistoryAgentsRebateList(Map<String, Object> parameters);
    
    /**
     * 
     * @标题: getPaoDeKuaiWeekPayInfoByDate 
     * @描述: 跑得快周结算列表(单独方法)
     *
     * @参数信息
     *    @param parameters
     *    @return
     *
     * @返回类型 List<Map<String,Object>>
     * @开发者 moys
     * @可能抛出异常
     */
    List<Map<String, Object>> getPaoDeKuaiWeekPayInfoByDate(Map<String, Object> parameters);
    /**
     * 
     * @标题: getWeekPayinfoByDate 
     * @描述: 根据时间获取周结算列表
     *
     * @参数信息
     *    @param parameters
     *    @return
     *
     * @返回类型 List<Map<String,Object>>
     * @开发者 moys
     * @可能抛出异常
     */
    List<Map<String, Object>> getWeekPayinfoByDate(Map<String, Object> parameters);

    
    /**
     * 查询单条记录（周结算数据）
     * @param record
     * @return
     */
    TaxesInviteWeek  findTaxesDirectlyWeekByCondition(TaxesInviteWeek record);
    
    /***
     * 更新为已经支付
     * @param record
     * @return
     */
    int updateIsawardByCondition(TaxesInviteWeek record);

    /**
     * 
     * @标题: getWeekPayHistoryDetailInfoByDate 
     * @描述: 获取周结算历史详情
     *
     * @参数信息
     *    @param parameters
     *    @return
     *
     * @返回类型 List<Map<String,Object>>
     * @开发者 moys
     * @可能抛出异常
     */
    List<Map<String, Object>> getWeekPayHistoryDetailInfoByDate(Map<String, Object> parameters);
    
    /**
     *  查询多条数据，批量支付查询(一键查询)
     * @param parameters
     * @return
     */
    List<TaxesInviteWeek> getWeekPayListByCondition(Map<String, Object> parameters);
    

}
