package com.qs.pub.game.service.impl;

import com.qs.pub.game.mapper.MemberInviteMapper;
import com.qs.pub.game.model.MemberInvite;
import com.qs.pub.game.service.IMemberInviteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class MemberInviteServiceImpl implements IMemberInviteService {

	@Resource
	private MemberInviteMapper memberInviteMapper; 
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return memberInviteMapper.deleteByPrimaryKey( id);
	}

	@Override
	public int insert(MemberInvite record) {
		
		return memberInviteMapper.insert(record);
	}

	@Override
	public int insertSelective(MemberInvite record) {
		
		return memberInviteMapper.insertSelective(record);
	}

	@Override
	public MemberInvite selectByPrimaryKey(Integer id) {
		
		return memberInviteMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MemberInvite record) {
		
		return memberInviteMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MemberInvite record) {
		
		return memberInviteMapper.updateByPrimaryKey(record);
	}


	@Override
	public MemberInvite selectBySiteId(String sitemid) {
		return memberInviteMapper.selectBySiteId(sitemid);
	}

	@Override
	public int queryCountByInviteCode(String inviteCode) {
		return memberInviteMapper.queryCountByInviteCode(inviteCode);
	}

	@Override
	public String getInviteCode(String sitemid) {
		return memberInviteMapper.getInviteCode(sitemid);
	}

	@Override
	public Map<String, Object> getAgentAndInviteInfo(Map<String, Object> parameters) {
		return memberInviteMapper.getAgentAndInviteInfo(parameters);
	}

	@Override
	public int updateInviteCodeUrlByMidCode(MemberInvite memberInvite) {
		return memberInviteMapper.updateInviteCodeUrlByMidCode(memberInvite);
	}

	@Override
	public int deleteBySiteId(String sitemid) {
		return memberInviteMapper.deleteBySiteId(sitemid);
	}

}
