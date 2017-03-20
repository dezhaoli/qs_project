package com.qs.webside.sys.service.agent.service;

import com.qs.webside.member.model.MemberAgents;

import java.util.List;
import java.util.Map;

/**
 * 代理商用户业务层
 *
 * Created by zun.wei on 2017/3/8.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IMemberAgentService {

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAgents record);

    int insertSelective(MemberAgents record);

    MemberAgents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberAgents record);

    int updateByPrimaryKey(MemberAgents record);

    List<MemberAgents> queryListByPage(Map<String, Object> parameters);

    /**
     * 编辑返现比例
     * @param id
     * @param mid
     * @param scale
     * @param remark
     * @return
     */
    int editScale(Integer id, Integer mid, Byte scale, String remark);

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    int resetPwd(Integer id);

}
