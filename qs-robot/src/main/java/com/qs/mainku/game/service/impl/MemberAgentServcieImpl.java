package com.qs.mainku.game.service.impl;

import com.qs.mainku.game.mapper.MemberagentsMapper;
import com.qs.mainku.game.model.Memberagents;
import com.qs.mainku.game.service.IMemberAgentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zun.wei on 2017/8/14 10:54.
 * Description:
 */
@Service
public class MemberAgentServcieImpl implements IMemberAgentService {

    @Resource
    private MemberagentsMapper memberagentsMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberagentsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Memberagents record) {
        return memberagentsMapper.insert(record);
    }

    @Override
    public int insertSelective(Memberagents record) {
        return memberagentsMapper.insertSelective(record);
    }

    @Override
    public Memberagents selectByPrimaryKey(Integer id) {
        return memberagentsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Memberagents record) {
        return memberagentsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Memberagents record) {
        return memberagentsMapper.updateByPrimaryKey(record);
    }

    @Override
    public Memberagents findMemberagentsByMid(Integer mid) {
        return memberagentsMapper.findMemberagentsByMid(mid);
    }

    @Override
    public int checkAgentIsExist(Integer mid) {
        return memberagentsMapper.checkAgentIsExist(mid);
    }

}
