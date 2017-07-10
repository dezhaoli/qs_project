package com.qs.webside.activity.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.activity.model.ActiIntegralCfg;

import java.util.List;
import java.util.Map;

/**
 * 活动中心积分配置表
 */
public interface ActiIntegralCfgMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActiIntegralCfg record);

    int insertSelective(ActiIntegralCfg record);

    ActiIntegralCfg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiIntegralCfg record);

    int updateByPrimaryKey(ActiIntegralCfg record);

    /**
     * @Author:zun.wei , @Date:2017/7/10 15:21
     * @Description:根据活动类型和时间获取配置对象列表
     * @param actiType
     * @return
     */
    List<Map<String,Object>> queryListByActiTypeLimitByDate(int actiType);

}