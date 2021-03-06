/*
 * 文件名：DataCenterLogController.java	 
 * 时     间：下午6:48:50
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.constant.LogType;
import com.qs.common.exception.SystemException;
import com.qs.common.util.ContextUtil;
import com.qs.pub.datacenter.model.AccountLog;
import com.qs.pub.datacenter.model.UserAddLog;
import com.qs.pub.datacenter.model.UserLoginLog;
import com.qs.pub.datacenter.service.IAccountLogService;
import com.qs.pub.datacenter.service.ICreateRoomService;
import com.qs.pub.datacenter.service.IPlayingService;
import com.qs.pub.datacenter.service.IUserAddLogService;
import com.qs.pub.datacenter.service.IUserLoginLogService;
import com.qs.sync.model.SyncCreateRoom;
import com.qs.sync.model.SyncPlaying;
import com.qs.sync.sender.SendDataFacade;

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
	private static final String key = "Fk$M$EbTAZqdK!BV";
	Logger logger = Logger.getLogger(DataCenterLogController.class);
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
	 * 在线用户业务层
	 */
	@Resource
	private IPlayingService playingService;
	/**
	 * 创建房间业务层
	 */
	@Resource
	private ICreateRoomService createRoomService;
	
	
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
	public Object dataCenterLogDispatch(@RequestBody String params,
			HttpServletRequest request)
	{
		logger.debug("dataCenterLogDispatch.html==::"+params);
		try
		{
			int rows = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			
			SendDataFacade sendDataFacade = ContextUtil
					.getBeanByType(SendDataFacade.class);
			if (null != sendDataFacade)
			{
				JSONObject obj = JSON.parseObject(params);
				String logType = obj.getString("logType");
				if (logType != null && !logType.trim().equals(""))
				{
					if (logType.equals(LogType.ACCOUNT_LOG))
					{
						rows = accountLogService.insert(
								JSON.parseObject(params, AccountLog.class));
					} else if (logType.equals(LogType.USER_ADD_LOG))
					{
						rows = userAddLogService.insert(
								JSON.parseObject(params, UserAddLog.class));
					} else if (logType.equals(LogType.USER_LOGIN_LOG))
					{
						rows = userLoginLogService.insert(
								JSON.parseObject(params, UserLoginLog.class));
					} else if (logType.equals(LogType.PLAYING))
					{
						SyncPlaying pl = JSON.parseObject(params,
								SyncPlaying.class);
						pl.setFromSysCode("sync-data");
						sendDataFacade.sendByJms(pl);
						rows = 1;
					} else if (logType.equals(LogType.CREATE_ROOM))
					{
						SyncCreateRoom sc = JSON.parseObject(params,
								SyncCreateRoom.class);
						sc.setFromSysCode("sync-data");
						sendDataFacade.sendByJms(sc);
						rows = 1;
					}
				}
				if (rows > 0)
				{
					map.put(CommonContants.SUCCESS, Boolean.TRUE);
					map.put(CommonContants.MESSAGE, CommonContants.ADD_SUCCESS);
					return map;
				} else
				{
					map.put(CommonContants.SUCCESS, Boolean.FALSE);
					map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
					return map;
				}
			} else
			{
				map.put(CommonContants.SUCCESS, Boolean.FALSE);
				map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
				return map;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			//throw new SystemException(e);
		}
		return null;
	}
		
		
		
		/*try
		{
			int rows = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			String sign = request.getHeader("sign");
			String signCode = request.getHeader("signCode");
			
			String curSign = MD5.encrypt(params + signCode + key, false);
			//数字签名
			if (curSign.equalsIgnoreCase(sign))
			{
				JSONObject obj = JSON.parseObject(params);
				String logType = obj.getString("logType");
				if (logType != null && !logType.trim().equals(""))
				{
					if (logType.equals(LogType.accountLog))
					{
						rows = accountLogService
								.insert(JSON.parseObject(params, AccountLog.class));
					} else if (logType.equals(LogType.userAddLog))
					{
						rows = userAddLogService
								.insert(JSON.parseObject(params, UserAddLog.class));
					} else if (logType.equals(LogType.userLoginLog))
					{
						rows = userLoginLogService.insert(
								JSON.parseObject(params, UserLoginLog.class));
					} else if (logType.equals(LogType.playing))
					{
						rows = playingService
								.insert(JSON.parseObject(params, Playing.class));
					} else if (logType.equals(LogType.createRoom))
					{
						rows = createRoomService
								.insert(JSON.parseObject(params, CreateRoom.class));
					}
				}
				if (rows > 0)
				{
					map.put(CommonContants.SUCCESS, Boolean.TRUE);
					map.put(CommonContants.MESSAGE, CommonContants.ADD_SUCCESS);
					return map;
				} else
				{
					map.put(CommonContants.SUCCESS, Boolean.FALSE);
					map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
					return map;
				}
			}else{
				map.put(CommonContants.SUCCESS, Boolean.FALSE);
				map.put(CommonContants.MESSAGE, "数字签名失败!");
				return map;
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
	}*/
	
	
	
}
