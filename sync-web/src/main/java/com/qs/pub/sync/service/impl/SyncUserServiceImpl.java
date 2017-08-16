/*
 * 文件名：CreateRoomServiceImpl.java	 
 * 时     间：下午5:39:34
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sync.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.sync.mapper.SyncUserMapper;
import com.qs.pub.sync.model.SyncUser;
import com.qs.pub.sync.service.ISyncUserService;


/** 
 * @ClassName: CreateRoomServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月23日 下午5:39:34 
 */
@Service
public class SyncUserServiceImpl implements ISyncUserService
{
	@Resource
	private SyncUserMapper syncUserMapper;

	@Override
	public int insert(SyncUser syncUser) {
		
		return syncUserMapper.insert(syncUser);
	}
	
	
}
