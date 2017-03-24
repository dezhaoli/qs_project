package com.qs.webside.agent.service;

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
    /**
     * 根据sitemid获取用户信息 -
     * @param sitemid
     * @return
     */
    MemberAgents getMemberAgentsInfoBySitemid(String sitemid);

    Map<String, Integer> getTaxesInviteMapper( Map<String ,Object > param);
    
    /**
     * 根据parent_id取用户信息
     * @param parent_id
     * @return
     */
    List<MemberAgents> getMemberAgentsInfoByParentId(String parent_id);  


    MemberAgents selectByMid(Integer mid);

    /**
     * 根据mid查询对应的代理商信息，和商务信息。
     * @param mid
     * @return
     */
    Map<String, Object> getAgentInfoAndBizInfoByMid(Integer mid);

    /**
     * 根据代理商所属id查询以及代理名下的所有代理商条数。
     * @param belongid
     * @return Integer total = map.get("total");
     */
    Map<String, Object> queryFirstAgentCountByBelongId(Integer belongid);

    /**
     * 获取商务专员绑定的一级代理商充值列表
     * @param belongid
     * @return
     */
    List<Map<String, Object>> queryFirstAgentByBelongIdPage(Map<String,Object> belongid);

    /**
     * 根据mid获取代理商信息
     * @param mid
     * @return
     */
    Map<String, Object> getAgentBusinessInfoByMid(Integer mid);

    /**
     * 根据mid获取代理商真实名字
     * @param mid
     * @return
     */
    String getAgentRealNameByMid(Integer mid);

    /**
     *  获取商务id下面的一级代理商所有子代理商总条数
     * @param firstMidBelongId
     * @return
     */
    Integer getChildrenAgentsCount(Map<String, Object> firstMidBelongId);

    /**
     * 根据商务ID和直属代理商ID获取当前直属代理商所有子级代理商
     * @param firstMidBelongId
     * @return
     */
    List<Map<String, Object>> getChildrenAgentsList(Map<String, Object> firstMidBelongId);


}
