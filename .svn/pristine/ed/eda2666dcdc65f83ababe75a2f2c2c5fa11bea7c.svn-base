/*
 * 文件名：UserKeepServiceImpl.java	 
 * 时     间：上午10:46:58
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.datacenter.mapper.UserKeepMapper;
import com.qs.pub.datacenter.model.UserKeep;
import com.qs.pub.datacenter.service.IUserKeepService;

/** 
 * @ClassName: UserKeepServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月30日 上午10:46:58 
 */
@Service
public class UserKeepServiceImpl implements IUserKeepService
{
	@Resource
	private UserKeepMapper userKeepMapper;
	@Override
	public List<UserKeep> queryUserKeepList(Map<String, Object> parameters)
	{
		return userKeepMapper.queryUserKeepList(parameters);
	}
	
}
