package com.qs.webside.game.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.qs.common.constant.CacheConstan;
import com.qs.webside.game.mapper.ClubMapper;
import com.qs.webside.game.model.Club;
import com.qs.webside.game.service.IClubService;

@Service
public class ClubServiceImpl implements IClubService {

	
	@Resource
	private ClubMapper clubMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer clubid) {
		
		return clubMapper.deleteByPrimaryKey(clubid);
	}

	@Override
	public int insert(Club record) {
		
		return clubMapper.insert(record);
	}

	@Override
	public int insertSelective(Club record) {
		return clubMapper.insertSelective(record);
	}

	@Override
	public Club selectByPrimaryKey(Integer clubid) {
		return clubMapper.selectByPrimaryKey(clubid);
	}

	@Override
    @CacheEvict(value={CacheConstan.NEW_INTO_CLUB_ALL_NAME},allEntries=true)
	public int updateByPrimaryKeySelective(Club record) {
		
		return clubMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Club record) {
		
		return clubMapper.updateByPrimaryKey(record);
	}

}
