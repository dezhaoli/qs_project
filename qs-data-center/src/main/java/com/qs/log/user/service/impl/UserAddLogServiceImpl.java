/*
 * 文件名：UserLoginLogServiceImpl.java	 
 * 时     间：下午6:42:28
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.user.service.impl;

import org.springframework.stereotype.Service;

import com.qs.log.user.mapper.UserAddLogMapper;
import com.qs.log.user.model.UserAddLog;
import com.qs.log.user.service.IUserAddLogService;

/** 
 * @ClassName: UserLoginLogServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月15日 下午6:42:28 
 */
@Service
public class UserAddLogServiceImpl implements IUserAddLogService
{
	private UserAddLogMapper userAddLogMapper;
	
	@Override
	public int insert(UserAddLog record)
	{
		return userAddLogMapper.insert(record);
	}
	
}
