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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.pub.datacenter.model.UserAddLog;
import com.qs.pub.datacenter.service.IUserAddLogService;

/** 
 * @ClassName: UserLoginLogController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月15日 下午6:48:50 
 */
@Controller
@RequestMapping(value = "/userAddLog/")
public class UserAddLogController extends BaseController
{
	@Resource
	private IUserAddLogService userAddLogService;
	
	
	
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
	@RequestMapping("addUserAddLog.html")
	@ResponseBody
	public Object insert(UserAddLog userAddLog){
		int rows = userAddLogService.insert(userAddLog);
		return rows;
	}
	@RequestMapping("toUserAddLogListUi.html")
	public String toUserAddLogListUi(Model model){
		
		return "/WEB-INF/view/loginfo/user_add_log_list";
	}
	/**
	 * 
	 * @标题: queryUserAddLogList 
	 * @描述:  新增用户日志统计查询
	 *
	 * @参数信息
	 *    @param gridPager
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("userAddLogList.html")
	@ResponseBody
	public Object queryUserAddLogList(String gridPager){
		try{
		Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
		List<UserAddLog> list = userAddLogService.queryListByPage(parameters);
		return getReturnPage(pager, page, list);
		}catch(Exception e){
			throw new SystemException(e);
		}
	}
	/**
	 * 
	 * @标题: queryCount 
	 * @描述:  统计报表数据查询接口
	 *
	 * @参数信息
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("userAddLogQueryCount.html")
	@ResponseBody
	public Object queryCount(String startTime,String endTime){
		try{
			Map<String, Object> parameter = new HashMap<String,Object>();
			parameter.put("startTime", startTime);
			parameter.put("endTime", endTime);
			List<UserAddLog> list = userAddLogService.queryCount(parameter);
			List<Object> listY = new ArrayList<Object>();
			List<Object> listX = new ArrayList<Object>();
			for(UserAddLog bean:list){
				listY.add(bean.getuId());
				listX.add(bean.getAppName());
			}
			String json = JSON.toJSONString(listY)+"@"+JSON.toJSONString(listX);
			return json;
			}catch(Exception e){
				throw new SystemException(e);
			}
	}
}
