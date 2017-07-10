package com.qs.webside.activity.service;

import com.qs.webside.activity.model.ActiIntegralCfg;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/7/10 14:04.
 * Description:活动中心积分配置表
 */
public interface IActiIntegralCfgService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiIntegralCfg record);

    int insertSelective(ActiIntegralCfg record);

    ActiIntegralCfg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiIntegralCfg record);

    int updateByPrimaryKey(ActiIntegralCfg record);

    List<ActiIntegralCfg> queryListByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/7/10 15:21
     * @Description:根据活动类型和时间获取配置对象列表
     * @param actiType
     * @return
     */
    List<Map<String,Object>> queryListByActiTypeLimitByDate(int actiType);

    /**
     * @Author:zun.wei , @Date:2017/7/10 15:52
     * @Description:根据活动类型和时间获取配置对象列表（无缓存,清缓存）
     * @param actiType
     * @return
     */
    List<Map<String,Object>> queryListByActiTypeLimitByDate2(int actiType);

}
