package com.qs.webside.member.mapper;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.member.model.MemberInvite;

public interface MemberInviteMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberInvite record);

    int insertSelective(MemberInvite record);

    MemberInvite selectByPrimaryKey(Integer id);

    MemberInvite selectByMid(Integer mid);

    int updateByPrimaryKeySelective(MemberInvite record);

    int updateByPrimaryKey(MemberInvite record);

    int queryCountByInviteCode(String inviteCode);

}