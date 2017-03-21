package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.MemberFides;

import java.util.List;
import java.util.Map;

public interface MemberFidesMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(MemberFides record);

    int insertSelective(MemberFides record);

    MemberFides selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(MemberFides record);

    int updateByPrimaryKey(MemberFides record);

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

}