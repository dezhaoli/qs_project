package com.qs.mainku.game.service.impl;

import com.qs.mainku.game.mapper.MembersMapper;
import com.qs.mainku.game.model.Members;
import com.qs.mainku.game.service.IMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/8/14 16:28.
 * Description:
 */
@Service
public class MemberServiceImpl implements IMemberService{

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

    @Override
    public Members findMembersBySitemid(String sitemid) {
        return membersMapper.findMembersBySitemid(sitemid);
    }

}
