package com.qs.webside.member.service;

import com.qs.webside.member.model.MemberFides;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/27.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IMemberFidesService {

    int deleteByPrimaryKey(Integer mid);

    int insert(MemberFides record);

    int insertSelective(MemberFides record);

    MemberFides selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(MemberFides record);

    int updateByPrimaryKey(MemberFides record);

    /**
     *
     * @param mid 白名单的mid
     * @return 成员列表
     */
    MemberFides selectByMemberMid(Integer mid);

    /**
     * 查询分页人员列表
     * @param parameters
     * @return
     */
    List<MemberFides> queryListByPage(Map<String, Object> parameters);

    /**
     * 根据mid获取人员信息
     * @param mid
     * @return
     */
    Map<String, Object> queryUserInfoByMid(Integer mid);

    /**
     * 根据mid,sitemid获取用户信息
     * @param midOrSiteMid
     * @return
     */
    Map<String, Object> queryUserInfoByMidAndSiteMid(Map<String, Object> midOrSiteMid);

    /**
     * 根据用户名查询用户列表，目前是用户名精确查询。返回列表是为了以后模糊查询进行拓展
     * @param parameters
     * @return
     */
    List<MemberFides> queryListByUserName(Map<String, Object> parameters);

    /**
     * 根据mid获取用户信息
     * @param parameters
     * @return
     */
    MemberFides queryListByMid(Map<String, Object> parameters);




    /**
     * 根据用户名查询用户列表，目前是用户名精确查询。返回列表是为了以后模糊查询进行拓展
     * @param parameters
     * @return
     */
    List<MemberFides> queryListByNameAndMidAndSiteMid(Map<String, Object> parameters);

    /**
     * 代理商商授权要求超过15人
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年3月28日
     */
    Integer selectAengtCountByInvite(Integer mid);
    
    /**
     * 
     * @标题: queryUserAndGoldListByPage 
     * @描述: 查询用户和金币列表
     *
     * @参数信息
     *    @param parameters
     *    @return
     *
     * @返回类型 List<Map<String,Object>>
     * @开发者 QS
     * @可能抛出异常
     */
    List<Map<String, Object>> queryUserAndGoldListByPage(Map<String, Object> parameters);
    
    /**
     * 根据mid查看当前用户的邀请人的信息
     * @param invite
     * @return List<MemberFides>
     * @author:zyy
     * @time:2017年5月26日
     */
    List<MemberFides> selectAengtInviteInfo(Integer invite);

    /**
     * @Author:zun.wei , @Date:2017/6/26 10:49
     * @Description:查询代理商绑定的用户列表
     * @param parameters
     * @return
     */
    List<Map<String, Object>> selectAgentBindingUserList(Map<String, Object> parameters);
    
    /**
     * 根据mid获取当前代理商等级比值
     * @param mid
     * @return
     * @author:zyy
     * @time:2017年9月11日
     */
    int selectAgentClubGrade(Integer mid);

}
