/*
 * 文件名：IMemberpaymentService.java	 
 * 时     间：下午2:21:10
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.agent.game.service;

import java.util.List;
import java.util.Map;

import com.qs.agent.game.model.Memberpayment;

/** 
 * @ClassName: IMemberpaymentService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月8日 下午2:21:10 
 */
public interface IMemberpaymentService
{

	List<Memberpayment> queryListByPage(Map<String, Object> parameters);
	
}
