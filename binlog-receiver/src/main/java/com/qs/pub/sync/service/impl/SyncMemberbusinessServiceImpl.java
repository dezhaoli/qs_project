/*
 * 文件名：ISysnMemberbusinessService.java	 
 * 时     间：下午7:24:12
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sync.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.sync.mapper.SyncMemberbusinessMapper;
import com.qs.pub.sync.service.ISysnMemberbusinessService;
import com.qs.sync.model.SyncMemberbusiness;

/** 
 * @ClassName: ISysnMemberbusinessService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年9月14日 下午7:24:12 
 */
@Service
public class SyncMemberbusinessServiceImpl implements ISysnMemberbusinessService
{

	@Resource
	private SyncMemberbusinessMapper syncMemberbusinessMapper;
	@Override
	public void insert(SyncMemberbusiness myMessage)
	{
		syncMemberbusinessMapper.insert(myMessage);
	}
	
}
