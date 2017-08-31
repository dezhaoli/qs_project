package com.qs.log.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.game.mapper.ClubMemberMapper;
import com.qs.log.game.model.ClubMember;
import com.qs.log.game.service.IClubMemberService;

@Service
public class ClubMemberServiceImpl implements IClubMemberService{

	@Resource
	private ClubMemberMapper clubMemberMapper;
	@Override
	public int insert(ClubMember record) {
		
		return clubMemberMapper.insert(record);
	}

	@Override
	public int insertSelective(ClubMember record) {
		
		return clubMemberMapper.insertSelective(record);
	}

	@Override
	public List<Map<String, Object>> getClubMemberInfo(Map<String, Object> param) {
		return clubMemberMapper.getClubMemberInfo(param);
	}

	@Override
	public ClubMember selectClubMember(ClubMember clubMember) {
		return clubMemberMapper.selectClubMember(clubMember);
	}

	@Override
	public int selectCountClubMember(int mid) {
		return clubMemberMapper.selectCountClubMember(mid);
	}

	@Override
	public int deleteByPrimaryKey(ClubMember clubMember) {
		return clubMemberMapper.deleteByPrimaryKey(clubMember);
	}

	@Override
	public List<ClubMember> getByPrimaryKeyList(int mid) {
		return clubMemberMapper.getByPrimaryKeyList(mid);
	}

}
