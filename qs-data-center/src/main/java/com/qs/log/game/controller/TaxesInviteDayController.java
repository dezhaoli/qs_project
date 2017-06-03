/*
 * 文件名：TaxesInviteDayController.java	 
 * 时     间：下午8:48:28
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.qs.common.exception.SystemException;
import com.qs.constant.Constant;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInviteDay;
import com.qs.log.game.service.ITaxesInviteDayService;

/** 
 * @ClassName: TaxesInviteDayController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月2日 下午8:48:28 
 */
@Controller
@RequestMapping(value="/businessCount/")
public class TaxesInviteDayController extends BaseController
{
	@Resource
	private ITaxesInviteDayService taxesInviteDayService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	
	@RequestMapping("toBusinessCountListUi.html")
	public String toBusinessCountListUi(){
		return "/WEB-INF/view/loginfo/memberbusiness_list";
	}
	
	@RequestMapping("queryBusinessCountList.html")
	@ResponseBody
	public Object queryBusinessCountList(String gridPager,HttpServletResponse response){
		try
		{
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE);
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE);
		    String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
		    DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			parameters.put("gameCode", Constant.getDataCenterBusinessGameCode(gameCode));
			if (gameType != null && !gameType.trim().equals(""))
			{
				if (gameType.equals("gd_majiang_pub"))
				{
					parameters.put("stat", gameType);
				} else
				{
					parameters.put("stat", gameType + "_stat");
				}
			}
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<TaxesInviteDay> list = taxesInviteDayService.queryBusinessListByPage(parameters);
							
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
				List<TaxesInviteDay> list = taxesInviteDayService.queryBusinessListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	
	@RequestMapping("toAgentCountListUi.html")
	public String toAgentCountListUi(String id,Model model){
		model.addAttribute("id",id);
		return "/WEB-INF/view/loginfo/agent_list";
	}
	
	@RequestMapping("queryAgentCountList.html")
	@ResponseBody
	public Object queryAgentCountList(String gridPager,String id,HttpServletResponse response){
		try
		{
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE);
		    String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
		    DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<TaxesInviteDay> list = taxesInviteDayService.queryAgentListByPage(parameters);
							
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
				List<TaxesInviteDay> list = taxesInviteDayService.queryAgentListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
}
