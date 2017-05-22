/*
 * 文件名：TaxesInviteWeekDownServiceImpl.java	 
 * 时     间：下午1:59:39
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.game.mapper.TaxesInviteWeekDownMapper;
import com.qs.log.game.model.TaxesInviteWeekDown;
import com.qs.log.game.service.IWeekDownService;


/** 
 * @ClassName: TaxesInviteWeekDownServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月20日 下午1:59:39 
 */
@Service
public class WeekDownServiceImpl
		implements IWeekDownService
{
	@Resource
	private TaxesInviteWeekDownMapper taxesInviteWeekDownMapper;

	@Override
	public List<TaxesInviteWeekDown> queryListByPage(
			Map<String, Object> parameters)
	{
		return taxesInviteWeekDownMapper.queryListByPage(parameters);
		
	}

	@Override
	public List<TaxesInviteWeekDown> selectByMid(Map<String, Object> parameters)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaxesInviteWeekDown> queryListAll(
			Map<String, Object> parameters)
	{
		// TODO Auto-generated method stub
		return taxesInviteWeekDownMapper.queryListAll(parameters);
	}
	
}
