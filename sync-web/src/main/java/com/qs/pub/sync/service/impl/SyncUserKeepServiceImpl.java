/*
 * 文件名：SyncUserKeepServiceImpl.java	 
 * 时     间：上午10:24:00
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sync.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.sync.mapper.SyncUserKeepMapper;
import com.qs.pub.sync.service.ISyncUserKeepService;
import com.qs.sync.model.SyncUserKeep;

/** 
 * @ClassName: SyncUserKeepServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月29日 上午10:24:00 
 */
@Service
public class SyncUserKeepServiceImpl implements ISyncUserKeepService
{
	@Resource
	private SyncUserKeepMapper syncUserKeepMapper;
	
	@Override
	public int insert(SyncUserKeep syncUserKeep)
	{
		return syncUserKeepMapper.insert(syncUserKeep);
	}

	@Override
	public SyncUserKeep selectByUserId(Map<String, Object> map)
	{
		return syncUserKeepMapper.selectByUserId(map);
	}

	@Override
	public int update(SyncUserKeep syncUserKeep)
	{
		return syncUserKeepMapper.update(syncUserKeep);
	}
	
}
