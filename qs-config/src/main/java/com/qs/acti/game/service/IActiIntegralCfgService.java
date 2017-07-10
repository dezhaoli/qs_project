package com.qs.acti.game.service;


import com.qs.acti.game.model.ActiIntegralCfg;

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

}
