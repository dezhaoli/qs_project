package com.qs.pub.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.game.mapper.BizGroupAccessMapper;
import com.qs.pub.game.model.BizGroupAccess;
import com.qs.pub.game.service.IBizGroupAccessService;
@Service
public class BizGroupAccessServiceImpl implements IBizGroupAccessService {

	@Resource
	private BizGroupAccessMapper bizGroupAccessMapper;
	@Override
	public int insert(BizGroupAccess record) {
		
		return bizGroupAccessMapper.insert(record);
	}

	@Override
	public int insertSelective(BizGroupAccess record) {
		
		return bizGroupAccessMapper.insertSelective(record);
	}

	@Override
	public String selectCountAcids(int id) {
		return bizGroupAccessMapper.selectCountAcids(id);
	}

}
