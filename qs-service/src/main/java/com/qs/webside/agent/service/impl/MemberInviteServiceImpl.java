package com.qs.webside.agent.service.impl;

import com.qs.webside.member.mapper.MemberInviteMapper;
import com.qs.webside.member.model.MemberInvite;
import com.qs.webside.agent.service.IMemberInviteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 * 代理商邀请信息表
 * Created by zun.wei on 2017/3/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MemberInviteServiceImpl implements IMemberInviteService {


    @Resource
    private MemberInviteMapper memberInviteMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberInviteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MemberInvite record) {
        return memberInviteMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberInvite record) {
        return memberInviteMapper.insertSelective(record);
    }

    @Override
    public MemberInvite selectByPrimaryKey(Integer id) {
        return memberInviteMapper.selectByPrimaryKey(id);
    }

    @Override
    public MemberInvite selectByMid(Integer mid) {
        return memberInviteMapper.selectByMid(mid);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberInvite record) {
        return memberInviteMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberInvite record) {
        return memberInviteMapper.updateByPrimaryKey(record);
    }

    @Override
    public int queryCountByInviteCode(String inviteCode) {
        return memberInviteMapper.queryCountByInviteCode(inviteCode);
    }

    @Override
    public String getInviteCode(Map<String, Object> parameters) {
        return memberInviteMapper.getInviteCode(parameters);
    }

    @Override
    public Map<String, Object> getAgentAndInviteInfo(Integer mid) {
        return memberInviteMapper.getAgentAndInviteInfo(mid);
    }

    @Override
    public int updateInviteCodeUrlByMidCode(MemberInvite memberInvite) {
        return memberInviteMapper.updateInviteCodeUrlByMidCode(memberInvite);
    }

    @Override
    public int deleteByMid(Integer mid) {
        return memberInviteMapper.deleteByMid(mid);
    }

}
