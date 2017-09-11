package com.qs.webside.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qs.common.constant.CacheConstan;
import com.qs.log.game.service.IMailService;
import com.qs.webside.game.mapper.ClubMemberMapper;
import com.qs.webside.game.model.ClubMember;
import com.qs.webside.game.service.IClubMemberService;

@Service
public class ClubMemberServiceImpl implements IClubMemberService{

	@Resource
	private ClubMemberMapper clubMemberMapper;
	
	
	@Override
	public int insert(ClubMember record) {
		
		return clubMemberMapper.insert(record);
	}

	@Override
	@CacheEvict(value={CacheConstan.NEW_INTO_CLUB_ALL_NAME},key="'getClubInfoList:'+#record.mid")
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

	@Override
	public int deleteByPrimaryClubId(int mid) {
		return clubMemberMapper.deleteByPrimaryClubId(mid);
	}

	@Override
	public List<ClubMember> getByPrimaryClubidList(int clubid) {
		return clubMemberMapper.getByPrimaryClubidList(clubid);
	}

}
