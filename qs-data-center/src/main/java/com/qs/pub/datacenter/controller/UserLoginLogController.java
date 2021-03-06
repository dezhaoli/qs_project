/*
 * 文件名：UserLoginLogController.java	 
 * 时     间：下午6:48:50
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.constant.Constant;
import com.qs.pub.datacenter.model.UserLoginLog;
import com.qs.pub.datacenter.service.IUserLoginLogService;
import com.qs.pub.sys.model.UserEntity;

/** 
 * @ClassName: UserLoginLogController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月15日 下午6:48:50 
 */
@Controller
@RequestMapping(value = "/userLoginLog/")
public class UserLoginLogController extends BaseController
{
	@Resource
	private IUserLoginLogService userLoginLogService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	@RequestMapping("toUserLoginLogListUi.html")
	public String toUserLoginLogListUi(Model model){
		return "WEB-INF/view/web/user/user_login_log_list";
	}
	@RequestMapping("userLoginLogList.html")
	@ResponseBody
	public Object userLoginLogList(String startTime,String endTime){
		UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
		String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        parameters.put("gameCode", gameCode);
        
		List<UserLoginLog> list = userLoginLogService.queryListAll(parameters);
		List<String> list2 = new ArrayList<String>();
		List<Integer> list3 = new ArrayList<Integer>();
		for(UserLoginLog data : list){
			list2.add(data.getRegion());
			list3.add(data.getTotals());
		}
		String json2  = JSON.toJSONString(list2);
		String json3  = JSON.toJSONString(list3);
		return json2+"@"+json3;
	}
	@RequestMapping("queryListByPage.html")
	@ResponseBody
	public Object queryListByPage(String gridPager,HttpServletResponse response){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			parameters.put("gameCode", gameCode);
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<UserLoginLog> list = userLoginLogService
							.queryListByPage(parameters);
					ExportUtils.exportAll(response, pager, list);
					return null;
				} else
				{
					// 3.2、导出当前页数据
					ExportUtils.export(response, pager);
					return null;
				}
			} else
			{
				// 设置分页，page里面包含了分页信息
				Page<Object> page = PageHelper.startPage(pager.getNowPage(),
						pager.getPageSize());
				List<UserLoginLog> list = userLoginLogService
						.queryListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
        
	}
	@RequestMapping("queryUserLoginCountTotals.html")
	@ResponseBody
	public Object queryUserLoginCountTotals(String stime,String etime){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			Map<String, Object> parameters = new HashMap<String,Object>();
			// 判断是否包含自定义参数
			parameters.put("gameCode", gameCode);
			parameters.put("startTime", stime);
			parameters.put("endTime", etime);
			Long  totals = userLoginLogService.queryUserLoginCountTotals(parameters);
			return totals == null?0:totals;
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
	}
	/**
	 * 
	 * @标题: insert 
	 * @描述:  添加登录日志接口
	 *
	 * @参数信息
	 *    @param gridPager
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("addUserLoginLog.html")
	@ResponseBody
	public Object insert(UserLoginLog userLoginLog){
		int rows = userLoginLogService.insert(userLoginLog);
		return rows;
	}
	
	
	
}
