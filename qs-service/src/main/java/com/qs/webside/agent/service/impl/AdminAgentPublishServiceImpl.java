package com.qs.webside.agent.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.ctc.wstx.util.DataUtil;
import com.qs.webside.agent.mapper.AdminAgentPublishMapper;
import com.qs.webside.agent.model.AdminAgentPublish;
import com.qs.webside.agent.service.IAdminAgentPublishService;

@Service
public class AdminAgentPublishServiceImpl implements IAdminAgentPublishService {

	@Resource
	private AdminAgentPublishMapper adminAgentPublishMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return adminAgentPublishMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AdminAgentPublish record) {
		
		return adminAgentPublishMapper.insert(record);
	}

	@Override
	public int insertSelective(AdminAgentPublish record) {
		
		return adminAgentPublishMapper.insertSelective(record);
	}

	@Override
	public AdminAgentPublish selectByPrimaryKey(Integer id) {
		
		return adminAgentPublishMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminAgentPublish record) {
		
		return adminAgentPublishMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminAgentPublish record) {
		
		return adminAgentPublishMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<AdminAgentPublish> getPublishByAllList(Map<String,Object> param) {
		return adminAgentPublishMapper.getPublishByAllList(param);
	}

	@Override
	public AdminAgentPublish getPublishInfo() {
		return adminAgentPublishMapper.getPublishInfo();
	}

}
