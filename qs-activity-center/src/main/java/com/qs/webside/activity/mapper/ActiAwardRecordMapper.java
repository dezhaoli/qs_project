package com.qs.webside.activity.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiAwardRecord;

public interface ActiAwardRecordMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiAwardRecord record);

    int insertSelective(ActiAwardRecord record);

    ActiAwardRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiAwardRecord record);

    int updateByPrimaryKey(ActiAwardRecord record);

    /**
     * @Author:zun.wei , @Date:2017/6/8 17:31
     * @Description:根据活动类型查询，该活动类型对应的奖品兑换记录总个数
     * @param parameters
     * @return
     */
    Integer checkAwardRecordSumByActiType(Map<String,Object> parameters);
    
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

    
    /**
     * 根据param 获取用户某互动获得奖品列表
     * @param parameters
     * @return
     * @author:zyy
     * @time:2017年9月14日
     */
    List<Map<String,Object>> selectByParamList(Map<String,Object> parameters);
    
    /**
     * 获取最新前30 条获得奖品用户
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年9月14日
     */
    
    List<Map<String,Object>>  getActivityCenterAcquireGoodsThirty(Map<String,Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/9/20 15:37
     * @Description:查询某个活动类型的兑换记录数。
     * @param parameters
     * @return
     */
    int queryRecordCount(Map<String, Object> parameters);

}