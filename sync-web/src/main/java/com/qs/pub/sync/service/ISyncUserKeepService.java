/*
 * 文件名：ISyncUserKeepService.java	 
 * 时     间：上午10:21:53
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sync.service;

import java.util.Map;

import com.qs.sync.model.SyncUserKeep;

/** 
 * @ClassName: ISyncUserKeepService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月29日 上午10:21:53 
 */
public interface ISyncUserKeepService
{

	public int insert(SyncUserKeep syncUserKeep);

	public SyncUserKeep selectByUserId(Map<String, Object> map);

	public int update(SyncUserKeep syncUserKeep);
	
}
