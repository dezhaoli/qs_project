package com.qs.webside.activity.service;

import com.qs.webside.activity.model.ActiCenter;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/31 15:53.
 * Description:活动中心表
 */
public interface IActiCenterService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiCenter record);

    int insertSelective(ActiCenter record);

    ActiCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiCenter record);

    int updateByPrimaryKey(ActiCenter record);

    List<ActiCenter> queryListByPage(Map<String, Object> parameter);

    /**
     * @Author:zun.wei , @Date:2017/6/1 14:19
     * @Description:拉取启用状态下的活动中心对象
     * @return
     */
    List<Map<String,Object>> queryListActivityByStatus();

}
