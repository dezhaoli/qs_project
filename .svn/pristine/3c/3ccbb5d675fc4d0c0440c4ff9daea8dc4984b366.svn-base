package com.qs.mainku.game.service;

import com.qs.mainku.game.model.MemberFides;
import com.qs.mainku.game.model.Members;

import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/27 11:04.
 * Description:基本人员表
 */
public interface IMemberFidesService {

    int deleteByPrimaryKey(Integer mid);

    int insert(MemberFides record);

    int insertSelective(MemberFides record);

    MemberFides selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(MemberFides record);

    int updateByPrimaryKey(MemberFides record);

    List<MemberFides> queryListByPage(Map<String, Object> parameters);

    /**
     * 根据mid查询用户实体
     * @param mid 白名单的mid
     * @return 成员列表
     */
    MemberFides selectByMemberMid(Integer mid);

    /**
     * 根据mid获取用户的信息，金币，积分等
     * @param mid
     * @return
     */
    Map<String, Object> queryUserInfoByMid(Integer mid);

    /**
     * 根据mid或者sitemid查询用户信息
     * @param midOrSiteMid mid,sitemid 作为键
     * @return
     */
    Map<String, Object> queryUserInfoByMidAndSiteMid(Map<String, Object> midOrSiteMid);

    /**
     * 根据用户名查询用户列表，目前是用户名精确查询。返回列表是为了以后模糊查询进行拓展
     * @param parameters
     * @return
     */
    List<MemberFides> queryListByNameAndMidAndSiteMid(Map<String, Object> parameters);

    /**
     * 根据mid获取用户信息
     * @param parameters
     * @return
     */
    MemberFides queryListByMid(Map<String, Object> parameters);

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
     * @标题: queryOrderCountByPage
     * @描述: 查询用户和金币列表
     *
     * @参数信息
     *    @param parameters
     *    @return
     *
     * @返回类型 List<Map<String,Object>>
     * @开发者 moyousheng
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
     * 查询用户平台关联表
     * @param sitemid
     * @return
     */
    public Members findMembersBySitemid(String sitemid);

}
