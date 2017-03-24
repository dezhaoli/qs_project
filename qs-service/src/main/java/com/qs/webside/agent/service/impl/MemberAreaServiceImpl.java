package com.qs.webside.agent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.qs.webside.agent.service.IMemberAreaService;
import org.springframework.stereotype.Service;

import com.qs.pub.game.mapper.AreaMapper;
import com.qs.pub.game.model.Area;

@Service
public class MemberAreaServiceImpl implements IMemberAreaService {

	@Resource
	private AreaMapper areaMapper;
	
	
	@Override
	public int deleteByPrimaryKey(Integer aid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Area record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Area record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 根据aid差 AREA
	 */
	@Override
	public Area selectByPrimaryKey(Integer aid) {
		return areaMapper.selectByPrimaryKey(aid);
	}

	@Override
	public int updateByPrimaryKeySelective(Area record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Area record) {
		return 0;
	}

	@Override
	public List<Area> selectByAreaPrimaryKey(Area record) {
		
		return areaMapper.selectByAreaPrimaryKey(record);
	}

}
