package com.qs.pub.game.service;

import com.qs.pub.game.model.CommonAgents;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/3/8.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface ICommonAgentService {

    int deleteByPrimaryKey(String sitemid);

    int insert(CommonAgents record);

    int insertSelective(CommonAgents record);

    CommonAgents selectByPrimaryKey(String sitemid);

    int updateByPrimaryKeySelective(CommonAgents record);

    int updateByPrimaryKey(CommonAgents record);

    List<CommonAgents> queryListByPage(Map<String, Object> parameters);

    //List<Map<String,Object>> queryOneBeanMapByPage(Map<String, Object> parameter);
    /**
     * 根据参数查看该手机是否绑定账号
     * @param phone
     * @return
     * @author:zyy
     * @time:2017年4月5日
     */
    CommonAgents selectByPhoneInfo(String phone);

    /**
     * @Author:zun.wei , @Date:2017/7/5 10:30
     * @Description:如果手机号码存在则更新
     * @param record
     * @return
     */
    int updateSelectiveByIfphoneIsExsit(CommonAgents record);

}
