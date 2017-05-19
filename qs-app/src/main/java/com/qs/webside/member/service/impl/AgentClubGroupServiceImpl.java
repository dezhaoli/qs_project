package com.qs.webside.member.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.webside.member.mapper.AgentClubGroupMapper;
import com.qs.webside.member.model.AgentClubGroup;
import com.qs.webside.member.service.AgentClubGroupService;

@Service
public class AgentClubGroupServiceImpl implements AgentClubGroupService {

	@Resource
	private AgentClubGroupMapper agentClubGroupMapper ;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return agentClubGroupMapper.deleteByPrimaryKey( id);
	}

	@Override
	public int insert(AgentClubGroup record) {
		
		return agentClubGroupMapper.insert(record);
	}

	@Override
	public int insertSelective(AgentClubGroup record) {
		
		return agentClubGroupMapper.insertSelective(record);
	}

	@Override
	public AgentClubGroup selectByPrimaryKey(Integer id) {
		
		return agentClubGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AgentClubGroup record) {
		
		return agentClubGroupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AgentClubGroup record) {
		
		return agentClubGroupMapper.updateByPrimaryKey(record);
	}

	@Override
	public AgentClubGroup selectByCmidKeyInfo(Integer cmid) {
		
		return agentClubGroupMapper.selectByCmidKeyInfo(cmid);
	}

}
