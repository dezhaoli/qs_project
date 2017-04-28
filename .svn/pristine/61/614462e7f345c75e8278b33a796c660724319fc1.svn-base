package com.qs.pub.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.game.mapper.BizUserGroupMapper;
import com.qs.pub.game.model.BizUserGroup;
import com.qs.pub.game.service.IBizUserGroupService;

@Service
public class BizUserGroupServiceImpl implements IBizUserGroupService {

	@Resource
	private BizUserGroupMapper bizUserGroupMapper ;
	@Override
	public int insert(BizUserGroup record) {
		
		return bizUserGroupMapper.insert(record);
	}

	@Override
	public int insertSelective(BizUserGroup record) {
		
		return bizUserGroupMapper.insertSelective(record);
	}

}
