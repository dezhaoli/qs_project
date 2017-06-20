/*
 * 文件名：BusinessServiceImpl.java	 
 * 时     间：下午3:15:18
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.sys.mapper.BusinessMapper;
import com.qs.pub.sys.model.Business;
import com.qs.pub.sys.service.BusinessService;

/** 
 * @ClassName: BusinessServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月16日 下午3:15:18 
 */
@Service
public class BusinessServiceImpl implements BusinessService
{
	@Resource
	private BusinessMapper businessMapper;

	@Override
	public List<Business> queryListByPage(Map<String, Object> parameters)
	{
		return businessMapper.queryListByPage(parameters);
	}

	@Override
	public int insert(Business businessEntity)
	{
		return businessMapper.insert(businessEntity);
	}

	@Override
	public int update(Business businessEntity)
	{
		return businessMapper.updateByPrimaryKey(businessEntity);
	}

	@Override
	public Business findById(int id)
	{
		return businessMapper.findById(id);
	}

	@Override
	public List<Business> selectByGroupId(Integer groupId)
	{
		return businessMapper.selectByGroupId(groupId);
	}

	@Override
	public List findByuId(Long id)
	{
		return businessMapper.findByuId(id);
	}

	@Override
	public List findBusinessByGroupId(int id)
	{
		return businessMapper.findBusinessByGroupId(id);
	}

	@Override
	public Integer selectBusiness(Long id)
	{
		return businessMapper.selectBusiness(id);
	}

	@Override
	public Integer ifLeader(Long id)
	{
		return businessMapper.ifLeader(id);
	}
}
