package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.MemberInvite;

import java.util.Map;

public interface MemberInviteMapper extends IBaseMapper {
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