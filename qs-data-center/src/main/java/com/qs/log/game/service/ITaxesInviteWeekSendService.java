package com.qs.log.game.service;

import com.qs.log.game.model.TaxesInviteWeekSend;

import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: ITaxesInviteWeekSendService 
 * @描述: 活动返利
 * @author moyousheng
 * @date 2017年4月25日 下午4:15:54
 */
public interface ITaxesInviteWeekSendService {

    int insert(TaxesInviteWeekSend record);

    int insertSelective(TaxesInviteWeekSend record);
    
    /**
     * //@Author:zun.wei, @Date:2017/4/5 15:48
     * 根据时间获取周结算发放
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getWeekPayinfoByDate(Map<String, Object> parameters);

    
    /**
     * 查询单条记录（周结算）
     * @param record
     * @return
     */
    TaxesInviteWeekSend  findTaxesDirectlyWeekByCondition(TaxesInviteWeekSend record);
    
    /***
     * 更新为已经支付
     * @param record
     * @return
     */
    int updateIsawardByCondition(TaxesInviteWeekSend record);

    
    /**
     *  查询多条数据，批量支付查询(一键查询)
     * @param parameters
     * @return
     */
    List<TaxesInviteWeekSend> getWeekPayListByCondition(Map<String, Object> parameters);
    

}
