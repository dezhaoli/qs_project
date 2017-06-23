/*
 * 文件名：BusinessService.java	 
 * 时     间：下午3:15:00
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.service;

import java.util.List;
import java.util.Map;

import com.qs.pub.sys.model.Business;

/** 
 * @ClassName: BusinessService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月16日 下午3:15:00 
 */
public interface BusinessService
{

	List<Business> queryListByPage(Map<String, Object> parameters);

	int insert(Business businessEntity);

	int update(Business businessEntity);

	Business findById(int id);


	List<Business> selectByGroupId(Map<String, Object> map);

	List findByuId(Map<String, Object> map);

	List findBusinessByGroupId(int id);

	Integer ifLeader(Map<String, Object> map);

	List selectBusiness(Map<String, Object> parameters);

	
}
