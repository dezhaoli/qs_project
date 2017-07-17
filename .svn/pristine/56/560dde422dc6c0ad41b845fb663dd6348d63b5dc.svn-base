/*
 * 文件名：IMemberagentsService.java	 
 * 时     间：上午9:38:52
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.agent.game.service;

import java.util.List;
import java.util.Map;

import com.qs.agent.game.model.Memberagents;
import com.qs.agent.game.model.Memberbusiness;



/** 
 * @ClassName: IMemberagentsService 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月17日 上午9:38:52 
 */
public interface IMemberagentsService
{
	List<Memberbusiness> queryListByPage(Map<String,Object> parameter);

	List<Memberagents> selectByMid(Map<String, Object> parameters);

	List<Memberbusiness> queryMemberbusinessAddListByPage(
			Map<String, Object> parameters);


	List<Memberagents> queryMemberagentsAddListDetailsByPage(
			Map<String, Object> parameters);

	List<Memberagents> queryMemberpaymentListByPage(
			Map<String, Object> parameters);

	List<Memberagents> queryMemberpaymentListDetailsByPage(
			Map<String, Object> parameters);

	List<Memberbusiness> queryUserAddListByPage(Map<String, Object> parameters);

	List<Memberbusiness> queryUserAddListDetailsByPage(
			Map<String, Object> parameters);

	List<Memberagents> queryAgentLevel(Map<String, Object> parameters);

	List<Memberagents> queryListCountByBusinessId(
			Map<String, Object> parameters);

	List<Memberagents> queryBusinessCountOfAdd(Map<String, Object> parameters);
}
