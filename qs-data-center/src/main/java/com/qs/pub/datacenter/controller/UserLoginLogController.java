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
import com.qs.pub.datacenter.model.UserLoginLog;
import com.qs.pub.datacenter.service.IUserLoginLogService;

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
	
	
	@RequestMapping("toUserLoginLogListUi.html")
	public String toUserLoginLogListUi(Model model){
		return "/WEB-INF/view/loginfo/user_login_log_list";
	}
	@RequestMapping("userLoginLogList.html")
	@ResponseBody
	public Object userLoginLogList(String startTime,String endTime){
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        
		List<UserLoginLog> list = userLoginLogService.queryListAll(parameters);
		List<String> list2 = new ArrayList<String>();
		List<Integer> list3 = new ArrayList<Integer>();
		for(UserLoginLog data : list){
			list2.add(data.getAppName());
			list3.add(data.getuId());
		}
		String json2  = JSON.toJSONString(list2);
		String json3  = JSON.toJSONString(list3);
		return json2+"@"+json3;
	}
	@RequestMapping("queryListByPage.html")
	@ResponseBody
	public Object queryListByPage(String gridPager){
		Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
		List<UserLoginLog> list = userLoginLogService.queryListByPage(parameters);
		return getReturnPage(pager, page, list);
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
