package com.qs.mainku.game.service.impl;

import com.qs.mainku.game.mapper.AgentClubGroupMapper;
import com.qs.mainku.game.model.AgentClubGroup;
import com.qs.mainku.game.service.AgentClubGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
