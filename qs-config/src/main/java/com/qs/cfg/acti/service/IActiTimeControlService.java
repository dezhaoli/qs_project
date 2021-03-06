package com.qs.cfg.acti.service;

import com.qs.cfg.acti.model.ActiTimeControl;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/25 16:44.
 * Description:活动预告时间控制表
 */
public interface IActiTimeControlService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiTimeControl record);

    int insertSelective(ActiTimeControl record);

    ActiTimeControl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiTimeControl record);

    int updateByPrimaryKey(ActiTimeControl record);

    List<ActiTimeControl> queryListByPage(Map<String, Object> parameter);

    /**
     * @Author:zun.wei , @Date:2017/5/25 16:43
     * @Description:查询最新的一条活动数据
     * @return
     */
    ActiTimeControl getActTimeControlLimit01(Integer type);

}
