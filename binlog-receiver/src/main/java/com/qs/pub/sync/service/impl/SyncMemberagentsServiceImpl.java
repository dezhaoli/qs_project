/*
 * 文件名：ISyncMemberagentsService.java	 
 * 时     间：下午7:24:43
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sync.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.pub.sync.mapper.SyncMemberagentsMapper;
import com.qs.pub.sync.service.ISyncMemberagentsService;
import com.qs.sync.model.SyncMemberagents;

/** 
 * @ClassName: ISyncMemberagentsService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年9月14日 下午7:24:43 
 */
@Service
public class SyncMemberagentsServiceImpl implements ISyncMemberagentsService
{
	@Resource
	private SyncMemberagentsMapper syncMemberagentsMapper;

	@Override
	public void insert(SyncMemberagents myMessage)
	{
		syncMemberagentsMapper.insert(myMessage);
	}
	
}
