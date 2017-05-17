package com.qs.webside.agent.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qs.webside.agent.mapper.AgentClubMemberMapper;
import com.qs.webside.agent.model.AgentClubMember;
import com.qs.webside.agent.service.IAgentClubMemberService;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.service.IMemberFidesService;

@Service
public class AgentClubMemberServiceImpl implements IAgentClubMemberService{
	
	Logger log = Logger.getLogger(AgentClubMemberServiceImpl.class);  
	@Resource
	private AgentClubMemberMapper agentClubMemberMapper ;
	
	@Resource
	private IMemberFidesService memberFidesService;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return agentClubMemberMapper.deleteByPrimaryKey( id);
	}

	@Override
	public int insert(AgentClubMember record) {
		
		return agentClubMemberMapper.insert( record);
	}

	@Override
	public int insertSelective(AgentClubMember record) {
		
		return agentClubMemberMapper.insertSelective( record);
	}

	@Override
	public AgentClubMember selectByPrimaryKey(Integer id) {
		
		return agentClubMemberMapper.selectByPrimaryKey( id);
	}

	@Override
	public int updateByPrimaryKeySelective(AgentClubMember record) {
		
		return agentClubMemberMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AgentClubMember record) {
		
		return agentClubMemberMapper.updateByPrimaryKey(record);
	}

	@Override
	public  List<Map<String,Object>>  selectByMid(Map<String,Object> parma) {
		List<Map<String,Object>>  list=agentClubMemberMapper.selectByMid(parma);
		if (list !=null){
			
			for (Map<String, Object> map : list) {
				MemberFides	memberFides=memberFidesService.selectByPrimaryKey(Integer.valueOf(map.get("mid").toString()));
                if (memberFides !=null){
                	map.put("name", memberFides.getName());
                }
			}
		}
				return list;
	}

	@Override
	public AgentClubMember selectByMidInfo(Map<String, Object> parma) {
		return agentClubMemberMapper.selectByMidInfo(parma);
	}


}