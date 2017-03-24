package com.qs.webside.member.service.impl;

import com.qs.webside.member.mapper.MembersMapper;
import com.qs.webside.member.model.Members;
import com.qs.webside.member.service.IMembersServcie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  平台ID与游戏ID对应表
 *
 * Created by zun.wei on 2017/3/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MembersServcieImpl implements IMembersServcie{

    @Resource
    private MembersMapper membersMapper;

    @Override
    public int deleteByPrimaryKey(Integer mid) {
        return membersMapper.deleteByPrimaryKey(mid);
    }

    @Override
    public int insert(Members record) {
        return membersMapper.insert(record);
    }

    @Override
    public int insertSelective(Members record) {
        return membersMapper.insertSelective(record);
    }

    @Override
    public Members selectByPrimaryKey(Integer mid) {
        return membersMapper.selectByPrimaryKey(mid);
    }

    @Override
    public int updateByPrimaryKeySelective(Members record) {
        return membersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Members record) {
        return membersMapper.updateByPrimaryKey(record);
    }

}
