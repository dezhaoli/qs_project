/*
 * 文件名：UserLoginLogServiceImpl.java	 
 * 时     间：上午9:07:51
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sync.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.sync.mapper.SyncUserLoginLogMapper;
import com.qs.pub.sync.service.IUserLoginLogService;
import com.qs.sync.model.SyncUserLoginLog;

/** 
 * @ClassName: UserLoginLogServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月23日 上午9:07:51 
 */
@Service
public class UserLoginLogServiceImpl implements IUserLoginLogService
{
@Resource
private SyncUserLoginLogMapper syncUserLoginLogMapper;
	@Override
	public int insert(SyncUserLoginLog syncUserLoginLog)
	{
		return syncUserLoginLogMapper.insert(syncUserLoginLog);
	}
	
}
