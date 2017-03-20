package com.qs.pub.game.service;

import com.qs.pub.game.model.CommonAgentsInfo;

import java.util.List;
import java.util.Map;

/**
 * 代理商信息
 * Created by zun.wei on 2017/3/8.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface ICommonAgentInfoService {

    int deleteByPrimaryKey(String sitemid);

    int insert(CommonAgentsInfo record);

    int insertSelective(CommonAgentsInfo record);

    CommonAgentsInfo selectByPrimaryKey(String sitemid);

    int updateByPrimaryKeySelective(CommonAgentsInfo record);

    int updateByPrimaryKey(CommonAgentsInfo record);

    List<CommonAgentsInfo> queryListByPage(Map<String, Object> parameters);

}
