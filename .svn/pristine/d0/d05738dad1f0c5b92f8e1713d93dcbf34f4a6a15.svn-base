/*
 * 文件名：AccountLogServiceImpl.java	 
 * 时     间：下午5:51:43
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.user.mapper.AccountLogMapper;
import com.qs.log.user.model.AccountLog;
import com.qs.log.user.service.IAccountLogService;

/** 
 * @ClassName: AccountLogServiceImpl 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月17日 下午5:51:43 
 */
@Service
public class AccountLogServiceImpl implements IAccountLogService
{
	@Resource
	private AccountLogMapper accountLogMapper;
	@Override
	public int insert(AccountLog record)
	{
		return accountLogMapper.insert(record);
	}
	
}
