package com.qs.log.game.mapper;

import java.util.List;
import java.util.Map;
import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.log.game.model.TaxesInviteWeekSend;

public interface TaxesInviteWeekSendMapper extends IBaseMapper<TaxesInviteWeekSend,Integer> {
    int insert(TaxesInviteWeekSend record);

    int insertSelective(TaxesInviteWeekSend record);
    /**
     * 
     * 根据时间获取活动返利
     * @param parameters
     * @return
     */
    List<Map<String, Object>> getWeekPayInfoByDate(Map<String, Object> parameters);
    
    /**
     *  查询单条记录（周结算）
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
     * 查询多条数据，批量支付查询(一键查询)
     * @param parameters
     * @return
     */
    List<TaxesInviteWeekSend> getWeekPayListByCondition(Map<String, Object> parameters);
}