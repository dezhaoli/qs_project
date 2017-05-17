/*
 * 文件名：MemberagentsServiceImpl.java	 
 * 时     间：上午9:40:14
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.user.mapper.MemberagentsMapper;
import com.qs.log.user.mapper.MemberbusinessMapper;
import com.qs.log.user.model.Memberbusiness;
import com.qs.log.user.model.UserLoginLog;
import com.qs.log.user.service.IMemberagentsService;

/** 
 * @ClassName: MemberagentsServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月17日 上午9:40:14 
 */
@Service
public class MemberagentsServiceImpl implements IMemberagentsService
{
	@Resource
	private MemberagentsMapper memberagentsMapper;
	@Resource
	private MemberbusinessMapper memberbusinessMapper;

	@Override
	public List<Memberbusiness> queryListByPage(Map<String, Object> parameter)
	{
		return memberbusinessMapper.queryListByPage(parameter);
	}

	@Override
	public List<Memberbusiness> selectByMid(Map<String, Object> parameters)
	{
		
		return memberagentsMapper.selectByMid(parameters);
	}
	
}
