/*
 * 文件名：MemberagentsController.java	 
 * 时     间：上午9:36:53
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.user.controller;

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
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.user.model.Memberbusiness;
import com.qs.log.user.model.UserLoginLog;
import com.qs.log.user.service.IMemberagentsService;

/** 
 * @ClassName: MemberagentsController 
 * @描述: 代理商清理功能控制器 
 * @author wangzhen
 * @date 2017年5月17日 上午9:36:53 
 */
@Controller
@RequestMapping(value="/memberagents/")
public class MemberagentsController extends BaseController
{
	@Resource
	private IMemberagentsService memberagentsService;
	
	/**
	 * 
	 * @标题: toMemberagentsListUi 
	 * @描述: 跳转到商务数据列表页面 
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toMemberagentsListUi.html")
	public String toMemberagentsListUi(String gameType,Model model){
		model.addAttribute("gameType",gameType);
		return "/WEB-INF/view/loginfo/memberagents_list";
	}
	
	/**
	 * 
	 * @标题: queryListByPage 
	 * @描述:  商务数据查询接口
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param gridPager
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("memberagentsList.html")
	@ResponseBody
	public Object queryListByPage(String gameType,String gridPager){
	try {
		//String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	//DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		
		Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        parameters.put("dbTable", gameType);
		List<Memberbusiness> list = memberagentsService.queryListByPage(parameters);
		return getReturnPage(pager, page, list);
		}catch(Exception e){
			throw new SystemException(e);
		}
		
	}
	/**
	 * 
	 * @标题: toMemberagentsDetailsUi 
	 * @描述:  跳转到 不合格代理商佣金汇总页面
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param mid
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toMemberagentsDetailsUi.html")
	public String toMemberagentsDetailsUi(String gameType,String id,Model model){
		model.addAttribute("gameType",gameType);
		model.addAttribute("id",id);
		return "/WEB-INF/view/loginfo/memberagents_details_list";
	}
	/**
	 * 
	 * @标题: queryListByPageDetails 
	 * @描述:  不合格代理商佣金汇总查询接口
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param mid
	 *    @param gridPager
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("memberagentsDetails.html")
	@ResponseBody
	public Object queryListByPageDetails(String gameType,String id,String gridPager){
	try {
		//String logDataSourceType=gameType+"LogDataSource";
       	String mainDataSourceType=gameType+"AgentDataSource";
    	//DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		
		Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        parameters.put("dbTable", gameType);
        parameters.put("id",id);
		List<Memberbusiness> list = memberagentsService.selectByMid(parameters);
		return getReturnPage(pager, page, list);
		}catch(Exception e){
			throw new SystemException(e);
		}
		
	}
}
