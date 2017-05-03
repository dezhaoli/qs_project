/*
 * 文件名：LoginIpCountRecordService.java	 
 * 时     间：下午4:44:08
 * 作     者：qs       
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.qs.log.game.mapper.LoginIpCountMapper;
import com.qs.log.game.model.DayLoginUserIp;
import com.qs.log.game.service.ILoginIpCountRecordService;

/**
 * @ClassName: LoginIpCountRecordService
 * @描述:
 * @author wangzhen
 * @date 2017年5月2日 下午4:44:08
 */
@Service
public class LoginIpCountRecordServiceImpl implements ILoginIpCountRecordService
{
	@Resource
	private LoginIpCountMapper loginIpCountMapper;
	/**
	 * 分页查询登陆ip统计数据
	 */
	@Override
	public List<DayLoginUserIp> queryListByPage(Map<String, Object> parameters)
	{
		return loginIpCountMapper.queryListByPage(parameters);
	}
	
}
