package com.qs.pub.pay.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.pub.pay.model.PayLog;

public interface PayLogMapper extends IBaseMapper<Integer,PayLog> {
    int deleteByPrimaryKey(Integer id);

    int insert(PayLog record);

    int insertSelective(PayLog record);

    PayLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayLog record);

    int updateByPrimaryKey(PayLog record);
    
    List<PayLog> selectPayLogAll(Map<String ,Object> payLog);
    /**
     * 判断支付日志是否存在
     * @param record
     * @return
     */
    PayLog findPayLogByCondition(PayLog record);
    
    
}