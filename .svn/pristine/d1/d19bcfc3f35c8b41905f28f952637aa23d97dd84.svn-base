package com.qs.webside.sys.service.member.service.impl;

import com.qs.webside.member.mapper.MemberFidesMapper;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.sys.service.member.service.IMemberFidesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/27.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MemberFidesServiceImpl implements IMemberFidesService {

    @Resource
    private MemberFidesMapper memberFidesMapper;


    @Override
    public int deleteByPrimaryKey(Integer mid) {
        return memberFidesMapper.deleteByPrimaryKey(mid);
    }

    @Override
    public int insert(MemberFides record) {
        return memberFidesMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberFides record) {
        return memberFidesMapper.insertSelective(record);
    }

    @Override
    public MemberFides selectByPrimaryKey(Integer mid) {
        return memberFidesMapper.selectByPrimaryKey(mid);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberFides record) {
        return memberFidesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberFides record) {
        return memberFidesMapper.updateByPrimaryKey(record);
    }

    @Override
    public MemberFides selectByMemberMid(Integer mid) {
        return memberFidesMapper.selectByMemberMid(mid);
    }

    @Override
    public List<MemberFides> queryListByPage(Map<String, Object> parameters) {
        return memberFidesMapper.queryListByPage(parameters);
    }

    @Override
    public Map<String, Object> queryUserInfoByMid(Integer mid) {
        return memberFidesMapper.queryUserInfoByMid(mid);
    }

    @Override
    public Map<String, Object> queryUserInfoByMidAndSiteMid(Map<String, Object> midOrSiteMid) {
        return memberFidesMapper.queryUserInfoByMidAndSiteMid(midOrSiteMid);
    }

    @Override
    public List<MemberFides> queryListByUserName(Map<String, Object> parameters) {
        return memberFidesMapper.queryListByUserName(parameters);
    }

}
