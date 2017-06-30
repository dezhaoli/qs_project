/*
 * 文件名：IUserKeepService.java	 
 * 时     间：上午10:39:02
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.datacenter.model.UserKeep;

/** 
 * @ClassName: IUserKeepService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月30日 上午10:39:02 
 */
public interface IUserKeepService
{

	List<UserKeep> queryUserKeepList(Map<String, Object> parameters);
	
}
