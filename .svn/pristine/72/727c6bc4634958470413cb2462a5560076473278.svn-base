package com.qs.agent.game.service.impl;

import com.qs.agent.game.mapper.MemberAgentsMapper;
import com.qs.agent.game.model.MemberAgents;
import com.qs.agent.game.service.IMemberAgentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * //@Author:zun.wei, @Date:2017/4/5 14:03
 *  代理商表
 * Created by zun.wei on 2017/4/5.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class MemberAgentServiceImpl implements IMemberAgentService {

    @Resource
    private MemberAgentsMapper memberAgentsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberAgentsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MemberAgents record) {
        return memberAgentsMapper.insert(record);
    }

    @Override
    public int insertSelective(MemberAgents record) {
        return memberAgentsMapper.insertSelective(record);
    }

    @Override
    public MemberAgents selectByPrimaryKey(Integer id) {
        return memberAgentsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MemberAgents record) {
        return memberAgentsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MemberAgents record) {
        return memberAgentsMapper.updateByPrimaryKey(record);
    }

	@Override
	public MemberAgents findByMid(Integer mid) {
		return memberAgentsMapper.findByMid(mid);
	}


}
