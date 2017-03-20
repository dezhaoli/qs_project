package com.qs.common.base.baseservice.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.common.base.baseservice.IBaseService;






public  class AbstractServiceImpl<R, PK extends Serializable> implements
		IBaseService<R, PK> {

	
	private IBaseMapper<R, PK> baseMapper;
	
	public void setBaseMapper(IBaseMapper<R, PK> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public int insert(R record) {
		return baseMapper.insert(record);
	}

	@Override
	public int insertSelective(R record) {
		return baseMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(R record) {
		return baseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(R record) {
		return baseMapper.updateByPrimaryKey(record);
	}
	@Override
	public int deleteByPrimaryKey(PK id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public R selectByPrimaryKey(PK id) {
		return baseMapper.selectByPrimaryKey(id);
	}
	

	@Override
	public List<R> queryListAll(Map<String, Object> parameter) {
		return baseMapper.queryListAll(parameter);
	}

	@Override
	public List<R> queryListByPage(Map<String, Object> parameter) {
		return baseMapper.queryListByPage(parameter);
	}

	@Override
	public int count(Map<String, Object> parameter) {
		return baseMapper.count(parameter);
	}
	
}
