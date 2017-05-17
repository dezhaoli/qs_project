/*
 * 文件名：UserLoginLogServiceImpl.java	 
 * 时     间：下午6:42:28
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.user.mapper.UserLoginLogMapper;
import com.qs.log.user.model.UserLoginLog;
import com.qs.log.user.service.IUserLoginLogService;

/** 
 * @ClassName: UserLoginLogServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月15日 下午6:42:28 
 */
@Service
public class UserLoginLogServiceImpl implements IUserLoginLogService
{
	
	@Resource
	private UserLoginLogMapper userLoginLogMapper;
	@Override
	public List<UserLoginLog> queryListAll(Map<String,Object> parameter)
	{
		return userLoginLogMapper.queryListAll(parameter);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserLoginLog> queryListByPage(Map<String,Object> parameter)
	{
		return userLoginLogMapper.queryListByPage(parameter);
	}
	@Override
	public int insert(UserLoginLog record)
	{
		return userLoginLogMapper.insert(record);
	}
	
}
