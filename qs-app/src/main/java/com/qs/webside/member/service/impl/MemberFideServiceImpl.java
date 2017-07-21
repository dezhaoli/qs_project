package com.qs.webside.member.service.impl;

import com.qs.webside.member.mapper.MemberfidesMapper;
import com.qs.webside.member.model.Memberfides;
import com.qs.webside.member.service.IMemberFideService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/7/21 10:28.
 * Description:
 */
@Service
public class MemberFideServiceImpl implements IMemberFideService {

    @Resource
    private MemberfidesMapper memberfidesMapper;

    @Override
    public int deleteByPrimaryKey(Integer mid) {
        return memberfidesMapper.deleteByPrimaryKey(mid);
    }

    @Override
    public int insert(Memberfides record) {
        return memberfidesMapper.insert(record);
    }

    @Override
    public int insertSelective(Memberfides record) {
        return memberfidesMapper.insertSelective(record);
    }

    @Override
    public Memberfides selectByPrimaryKey(Integer mid) {
        return memberfidesMapper.selectByPrimaryKey(mid);
    }

    @Override
    public int updateByPrimaryKeySelective(Memberfides record) {
        return memberfidesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Memberfides record) {
        return memberfidesMapper.updateByPrimaryKey(record);
    }

}
