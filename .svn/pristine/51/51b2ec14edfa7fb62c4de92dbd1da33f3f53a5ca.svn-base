/*
 * 文件名：DataCenterLogController.java	 
 * 时     间：下午6:48:50
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.LogType;
import com.qs.common.exception.SystemException;
import com.qs.pub.datacenter.model.AccountLog;
import com.qs.pub.datacenter.model.UserAddLog;
import com.qs.pub.datacenter.model.UserLoginLog;
import com.qs.pub.datacenter.service.IAccountLogService;
import com.qs.pub.datacenter.service.IUserAddLogService;
import com.qs.pub.datacenter.service.IUserLoginLogService;

/** 
 * @ClassName: DataCenterLogController 
 * @描述: 埋点日志控制器
 * @author wangzhen
 * @date 2017年5月15日 下午6:48:50 
 */
@Controller
@RequestMapping(value = "/dataCenter/")
public class DataCenterLogController extends BaseController
{
	/**
	 * 账单日志业务层
	 */
	@Resource
	private IAccountLogService accountLogService;
	/**
	 * 新增用户日志业务层
	 */
	@Resource
	private IUserAddLogService userAddLogService;
	/**
	 * 用户登录业务层
	 */
	@Resource
	private IUserLoginLogService userLoginLogService;
	
	
	/**
	 * 
	 * @标题: dataCenterLogDispatch 
	 * @描述:  日志接口调度控制器
	 *
	 * @参数信息
	 *    @param logType 日志类型  1账单日志   2新增用户   3用户登录
	 *    @param params  数据
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping(value="dataCenterLogDispatch.html", method=RequestMethod.POST)
	@ResponseBody
	public Object dataCenterLogDispatch(@RequestBody String params){
		try{
			int rows = 0;
			JSONObject obj = JSON.parseObject(params);
			String logType = obj.getString("logType");
			if(logType != null && !logType.trim().equals("")){
				if(logType.equals(LogType.accountLog)){
					rows = accountLogService.insert(JSON.parseObject(params,AccountLog.class));
				}else if(logType.equals(LogType.userAddLog)){
					rows = userAddLogService.insert(JSON.parseObject(params,UserAddLog.class));
				}else if(logType.equals(LogType.userLoginLog)){
					rows = userLoginLogService.insert(JSON.parseObject(params,UserLoginLog.class));
				}
			}
			return rows;
		}catch(Exception e){
			throw new SystemException(e);
		}
	}
	
	
	
}
