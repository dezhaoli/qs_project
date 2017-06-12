package com.qs.webside.activity.service;


import com.qs.webside.activity.model.ActiAwardRecord;

import java.util.List;
import java.util.Map;

/**
 * @Author:zun.wei , @Date:2017/6/7 15:15
 * @Description:活动中心奖品兑换记录表
 */
public interface IActiAwardRecordService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardRecord record);

    int insertSelective(ActiAwardRecord record);

    ActiAwardRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardRecord record);

    int updateByPrimaryKey(ActiAwardRecord record);

    List<ActiAwardRecord> queryListByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/6/8 17:31
     * @Description:根据活动类型查询，该活动类型对应的奖品兑换记录总个数
     * @param parameters
     * @return
     */
    int checkAwardRecordSumByActiType(Map<String,Object> parameters);
    
    
    /**
     * 根据mid 获取当前用户已兑换奖励列表
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年6月12日
     */
    List<Map<String,Object>> selectByMidKey(Integer mid);
    
    
    /**
     * 根据mid id 获取当前商品兑换次数
     * @param record
     * @return
     * @author:zyy
     * @time:2017年6月12日
     */
    int countAwardNumber(ActiAwardRecord record);

}