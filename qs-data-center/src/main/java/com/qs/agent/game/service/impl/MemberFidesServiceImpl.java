/*
 * 文件名：MemberFidesServiceImpl.java	 
 * 时     间：上午11:35:03
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.agent.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.agent.game.mapper.MemberFidesMapper;
import com.qs.agent.game.model.MemberFides;
import com.qs.agent.game.service.IMemberFidesService;

/** 
 * @ClassName: MemberFidesServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月8日 上午11:35:03 
 */
@Service
public class MemberFidesServiceImpl implements IMemberFidesService
{
	@Resource
	private MemberFidesMapper memberFidesMapper;
	@Override
	public List<MemberFides> queryListByPage(Map<String, Object> parameters)
	{
		return memberFidesMapper.queryListByPage(parameters);
	}
	@Override
	public Long queryAddUserCountTotals(Map<String, Object> parameters)
	{
		return memberFidesMapper.queryAddUserCountTotals(parameters);
	}
	@Override
	public List<MemberFides> userPayrankingList(Map<String, Object> parameters)
	{
		return memberFidesMapper.userPayrankingList(parameters);
	}
	
}
