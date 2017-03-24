package com.qs.webside.agent.service;

import com.qs.webside.member.model.MemberInvite;

import java.util.Map;

/**
 *  代理商邀请表
 * Created by zun.wei on 2017/3/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IMemberInviteService {

    int deleteByPrimaryKey(Integer id);

    int insert(MemberInvite record);

    int insertSelective(MemberInvite record);

    MemberInvite selectByPrimaryKey(Integer id);

    MemberInvite selectByMid(Integer mid);

    int updateByPrimaryKeySelective(MemberInvite record);

    int updateByPrimaryKey(MemberInvite record);

    /**
     *  根据邀请码查询某个邀请码是否已经被使用
     * @param inviteCode 邀请码
     * @return 返回0表示没有使用，大于0表示已经使用了。
     */
    int queryCountByInviteCode(String inviteCode);

    /**
     * 获取邀请码
     * @param parameters
     * @return
     */
    String getInviteCode(Map<String, Object> parameters);

}
