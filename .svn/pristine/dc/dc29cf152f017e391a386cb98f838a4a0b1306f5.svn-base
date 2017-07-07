package com.qs.webside.activity.service;

import com.qs.webside.activity.model.ActiIntegral;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/8 19:09.
 * Description:活动中心积分表
 */
public interface IActiIntegralService {

    int deleteByPrimaryKey(Integer id);

    int insert(ActiIntegral record);

    int insertSelective(ActiIntegral record);

    ActiIntegral selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActiIntegral record);

    int updateByPrimaryKey(ActiIntegral record);

    List<Map<String,Object>> queryListByPage(Map<String, Object> parameters);

    /**
     * @Author:zun.wei , @Date:2017/6/8 19:38
     * @Description:根据mid查询积分对象
     * @param mid
     * @return
     */
    ActiIntegral selectByMid(Integer mid);

    /**
     * @Author:zun.wei , @Date:2017/7/7 13:50
     * @Description:分享链接送积分
     * @param mid 用户mid
     * @return
     */
    Object sendIntegralByShare(int mid);

    /**
     * @Author:zun.wei , @Date:2017/7/7 14:21
     * @Description:不存在就插入积分对象，存在就更新。
     * @param record 积分对象
     * @return
     */
    int insertOrUpate(ActiIntegral record);

}
