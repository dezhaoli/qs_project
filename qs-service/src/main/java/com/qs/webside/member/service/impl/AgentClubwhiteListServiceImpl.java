package com.qs.webside.member.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.webside.agent.mapper.AgentClubwhiteListMapper;
import com.qs.webside.agent.model.AgentClubwhiteList;
import com.qs.webside.member.mapper.MemberFidesMapper;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.model.MemberWhiteList;
import com.qs.webside.member.service.IAgentClubwhiteListService;

@Service
public class AgentClubwhiteListServiceImpl implements IAgentClubwhiteListService{

	@Resource
	private AgentClubwhiteListMapper agentClubwhiteListMapper;
	
	@Resource
    private MemberFidesMapper memberFidesMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return agentClubwhiteListMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AgentClubwhiteList record) {
		
		return agentClubwhiteListMapper.insert(record);
	}

	@Override
	public int insertSelective(AgentClubwhiteList record) {
		
		return agentClubwhiteListMapper.insertSelective(record);
	}

	@Override
	public AgentClubwhiteList selectByPrimaryKey(Integer id) {
		
		return agentClubwhiteListMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AgentClubwhiteList record) {
		
		return agentClubwhiteListMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AgentClubwhiteList record) {
		
		return agentClubwhiteListMapper.updateByPrimaryKey(record);
	}


	@Override
	public List<AgentClubwhiteList> queryListByPage(Map<String, Object> parameters) {
		return agentClubwhiteListMapper.queryListByPage(parameters);
	}

	@Override
	public int updateTakeEffectById(Integer id) {
		return agentClubwhiteListMapper.updateTakeEffectById(id);
	}

	@Override
	public int addSelective(AgentClubwhiteList record) {
		 MemberFides memberFides = memberFidesMapper.selectByMemberMid(record.getMid());
		 AgentClubwhiteList awl = this.selectByMid(record.getMid());
	        int result = 0;
	        if (null != memberFides && memberFides.getMid() > 0 && awl == null) {
	        	AgentClubwhiteList memberWhiteList = new AgentClubwhiteList();
	            memberWhiteList.setIcon(memberFides.getIcon());
	            memberWhiteList.setName(memberFides.getName());
	            memberWhiteList.setMid(memberFides.getMid());
	            return agentClubwhiteListMapper.insertSelective(memberWhiteList);
	        }
	        if (awl != null) {
	            return -2;
	        }
	        return result;
	}

	@Override
    public AgentClubwhiteList selectByMid(Integer mid) {
        return agentClubwhiteListMapper.selectByMid(mid);
    }
}
