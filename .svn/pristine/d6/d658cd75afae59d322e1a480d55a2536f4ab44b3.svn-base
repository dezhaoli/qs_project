package com.qs.log.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.game.mapper.ClubMapper;
import com.qs.log.game.model.Club;
import com.qs.log.game.service.IClubService;

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
	public int updateByPrimaryKeySelective(Club record) {
		
		return clubMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Club record) {
		
		return clubMapper.updateByPrimaryKey(record);
	}

}
