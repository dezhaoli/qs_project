package com.qs.webside.member.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.webside.member.mapper.AgentClubMemberMapper;
import com.qs.webside.member.model.AgentClubMember;
import com.qs.webside.member.service.AgentClubMemberService;

@Service
public class AgentClubMemberServiceImpl implements AgentClubMemberService {

	@Resource
	private AgentClubMemberMapper agentClubMemberMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return agentClubMemberMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AgentClubMember record) {
		
		return agentClubMemberMapper.insert(record);
	}

	@Override
	public int insertSelective(AgentClubMember record) {
		
		return agentClubMemberMapper.insertSelective(record);
	}

	@Override
	public AgentClubMember selectByPrimaryKey(Integer id) {
		
		return agentClubMemberMapper.selectByPrimaryKey(id);
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
	public List<Map<String, Object>> getClubGroupInfo(Integer mid) {
		return agentClubMemberMapper.getClubGroupInfo(mid);
	}
}
