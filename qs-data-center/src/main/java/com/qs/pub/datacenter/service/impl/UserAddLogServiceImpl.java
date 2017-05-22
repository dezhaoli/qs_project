/*
 * 文件名：UserLoginLogServiceImpl.java	 
 * 时     间：下午6:42:28
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.datacenter.mapper.UserAddLogMapper;
import com.qs.pub.datacenter.model.UserAddLog;
import com.qs.pub.datacenter.service.IUserAddLogService;


/** 
 * @ClassName: UserLoginLogServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月15日 下午6:42:28 
 */
@Service
public class UserAddLogServiceImpl implements IUserAddLogService
{
	@Resource
	private UserAddLogMapper userAddLogMapper;
	
	@Override
	public int insert(UserAddLog record)
	{
		return userAddLogMapper.insert(record);
	}

	@Override
	public List<UserAddLog> queryListByPage(Map<String, Object> parameter)
	{
		
		return userAddLogMapper.queryListByPage(parameter);
	}

	@Override
	public List<UserAddLog> queryCount(Map<String, Object> parameter)
	{
		return userAddLogMapper.queryCount(parameter);
	}
	
}
