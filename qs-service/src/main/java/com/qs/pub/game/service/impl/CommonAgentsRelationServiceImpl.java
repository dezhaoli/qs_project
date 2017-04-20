package com.qs.pub.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.game.mapper.CommonAgentsRelationMapper;
import com.qs.pub.game.model.CommonAgentsRelation;
import com.qs.pub.game.service.ICommonAgentsRelationService;

@Service
public class CommonAgentsRelationServiceImpl implements ICommonAgentsRelationService {

	@Resource
	private CommonAgentsRelationMapper commonAgentsRelation;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return commonAgentsRelation.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CommonAgentsRelation record) {
		
		return commonAgentsRelation.insert(record);
	}

	@Override
	public int insertSelective(CommonAgentsRelation record) {
		
		return commonAgentsRelation.insertSelective(record);
	}

	@Override
	public CommonAgentsRelation selectByPrimaryKey(Integer id) {
		
		return commonAgentsRelation.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CommonAgentsRelation record) {
		
		return commonAgentsRelation.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CommonAgentsRelation record) {
		
		return commonAgentsRelation.updateByPrimaryKey(record);
	}

}
