/*
 * 文件名：TaxesInviteWeekDownController.java	 
 * 时     间：上午9:36:53
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.controller;

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
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInviteWeekDown;
import com.qs.log.game.service.IWeekDownService;

/** 
 * @ClassName: TaxesInviteWeekDownController 
 * @描述: 代理商业绩下滑统计控制器 
 * @author wangzhen
 * @date 2017年5月17日 上午9:36:53 
 */
@Controller
@RequestMapping(value="/weekdown/")
public class TaxesWeekDownController extends BaseController
{
	@Resource
	private IWeekDownService weekDownService;
	
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
	@RequestMapping("toTaxesInviteWeekDownListUi.html")
	public String toTaxesInviteWeekDownListUi(String gameType,Model model){
		model.addAttribute("gameType",gameType);
		return "/WEB-INF/view/loginfo/taxes_week_down_list";
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
	@RequestMapping("TaxesInviteWeekDownList.html")
	@ResponseBody
	public Object queryListByPage(String gameType,String gridPager){
	try {
		String logDataSourceType=gameType+"LogDataSource";
		String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		
		Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
       // parameters.put("dbTable", gameType);
		List<TaxesInviteWeekDown> list = weekDownService.queryListByPage(parameters);
		return getReturnPage(pager, page, list);
		}catch(Exception e){
			throw new SystemException(e);
		}
		
	}
	@RequestMapping("TaxesInviteWeekDownListCount.html")
	@ResponseBody
	public Object TaxesInviteWeekDownListCount(String mid,String date,String gameType){
		String logDataSourceType=gameType+"LogDataSource";
		String mainDataSourceType=gameType+"AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("mid", mid);
        parameters.put("date", date);
        
		List<TaxesInviteWeekDown> list = weekDownService.queryListAll(parameters);
		
		String json  = JSON.toJSONString(list);
		return json;
	}
}
