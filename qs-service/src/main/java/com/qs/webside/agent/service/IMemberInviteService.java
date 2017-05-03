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

    /**
     * //@Author:zun.wei, @Date:2017/4/1 9:51
     * 根据mid获取代理商信息和邀请码信息。
     * @param mid mid
     * @return a.id, a.mid, a.invitecode, a.inviteurl, a.status,b.realname
     */
    Map<String, Object> getAgentAndInviteInfo(Integer mid);

    /**
     * //@Author:zun.wei, @Date:2017/4/1 11:03
     * 根据mid,inviteCode更新邀请代理商邀请码。
     * @param memberInvite  mid,inviteCode,inviteUrl
     * @return 更新的条数
     */
    int updateInviteCodeUrlByMidCode(MemberInvite memberInvite);

    /**
     * @Author:zun.wei , @Date:2017/5/3 15:31
     * @Description: 根据mid删除代理商邀请表信息
     * @param mid
     * @return
     */
    int deleteByMid(Integer mid);

}
