package com.qs.mainku.game.service.impl;

import com.qs.mainku.game.mapper.AgentClubMemberMapper;
import com.qs.mainku.game.model.AgentClubMember;
import com.qs.mainku.game.service.AgentClubMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

	@Override
	public AgentClubMember getMemberInfoByAmidOmid(Map<String, Object> mids) {
		return agentClubMemberMapper.getMemberInfoByAmidOmid(mids);
	}

}
