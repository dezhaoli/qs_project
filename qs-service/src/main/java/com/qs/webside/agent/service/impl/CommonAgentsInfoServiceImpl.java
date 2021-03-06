package com.qs.webside.agent.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.game.mapper.CommonAgentsInfoMapper;
import com.qs.pub.game.model.CommonAgentsInfo;
import com.qs.webside.agent.service.ICommonAgentsInfoService;

@Service
public class CommonAgentsInfoServiceImpl implements ICommonAgentsInfoService{
	 @Resource
	private CommonAgentsInfoMapper commonAgentsInfoMapper;
	@Override
	public int deleteByPrimaryKey(String sitemid) {
		return commonAgentsInfoMapper.deleteByPrimaryKey(sitemid);
	}

	@Override
	public int insert(CommonAgentsInfo record) {
		return commonAgentsInfoMapper.insert(record);
	}

	/**
	 * 
	 */
	@Override
	public int insertSelective(CommonAgentsInfo record) {
		return commonAgentsInfoMapper.insertSelective(record);
	}

	@Override
	public CommonAgentsInfo selectByPrimaryKey(String sitemid) {
		return commonAgentsInfoMapper.selectByPrimaryKey(sitemid);
	}

	@Override
	public int updateByPrimaryKeySelective(CommonAgentsInfo record) {
		return commonAgentsInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CommonAgentsInfo record) {
		return commonAgentsInfoMapper.updateByPrimaryKey(record);
	}

}
