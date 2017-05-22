/*
 * 文件名：UserLoginLogController.java	 
 * 时     间：下午6:48:50
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.pub.datacenter.model.AccountLog;
import com.qs.pub.datacenter.service.IAccountLogService;

/** 
 * @ClassName: UserLoginLogController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月15日 下午6:48:50 
 */
@Controller
@RequestMapping(value = "/accountLog/")
public class AccountLogController extends BaseController
{
	@Resource
	private IAccountLogService accountLogService;
	
	
	
	/**
	 * 
	 * @标题: insert 
	 * @描述:  添加用户日志接口
	 *
	 * @参数信息
	 *    @param gridPager
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("addAccountLog.html")
	@ResponseBody
	public Object insert(AccountLog accountLog){
		int rows = accountLogService.insert(accountLog);
		return rows;
	}
	
	
	
}
