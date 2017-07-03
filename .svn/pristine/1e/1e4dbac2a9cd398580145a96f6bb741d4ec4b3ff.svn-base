/*
 * 文件名：TaxesInviteController.java	 
 * 时     间：下午5:49:23
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.constant.Constant;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInvite;
import com.qs.log.game.service.ITaxesInviteService;
import com.qs.pub.sys.model.UserEntity;

/** 
 * @ClassName: TaxesInviteController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月30日 下午5:49:23 
 */
@Controller
@RequestMapping("/taxesInvite/")
public class TaxesInviteController extends BaseController
{	
	@Resource
	private ITaxesInviteService taxesInviteService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate; 
	
	@RequestMapping("toTaxesInviteUi.html")
	private String toTaxesInviteUi(){
		
		return "/WEB-INF/view/loginfo/taxesInvite_list";
	}
	
	@RequestMapping("taxesInvite.html")
	@ResponseBody
	private Object taxesInvite(String gridPager,HttpServletResponse response){
		try
		{
			ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
	    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			parameters.put("dbtable", dataSourceName);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<TaxesInvite> list = taxesInviteService
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
				
				List<TaxesInvite> list = taxesInviteService.queryListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping("toArppuUi.html")
	private String toArppuUi(){
		
		return "/WEB-INF/view/loginfo/arppu_list";
	}
	
	
	@RequestMapping("arppuList.html")
	@ResponseBody
	private Object arppuList(String gridPager,HttpServletResponse response){
		try
		{
			ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
	    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			parameters.put("dbtable", dataSourceName);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<TaxesInvite> list = taxesInviteService
							.queryListByPageOfArppu(parameters);
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
				
				List<TaxesInvite> list = taxesInviteService.queryListByPageOfArppu(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
