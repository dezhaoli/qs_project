/*
 * 文件名：BusinessController.java	 
 * 时     间：下午3:12:40
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.baidu.disconf.client.support.utils.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.pub.sys.model.Business;
import com.qs.pub.sys.model.Company;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.BusinessService;

/** 
 * @ClassName: BusinessController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月16日 下午3:12:40 
 */
@Controller
@RequestMapping("/sysBusiness/")
public class BusinessController extends BaseController
{
	@Resource
	private BusinessService businessService;
	@RequestMapping("toBusinessListUi.html")
	public String toBusinessListUi(){
		
		return "/WEB-INF/view/role/business";
	}
	@RequestMapping("businessList.html")
	@ResponseBody
	public Object  businessList(String gridPager,HttpServletResponse response){
		try
		{
			
			
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			
			//判断用户是否代理商
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Business> list = businessService
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
				
				List<Business> list = businessService
						.queryListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	
	
	@RequestMapping("businessAllList.html")
	@ResponseBody
	public Object  businessAllList(){
		try
		{
			Map<String, Object> parameters = null;
			List<Business> list = businessService.queryListByPage(parameters);
			
			return list;
			
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	
	
	@RequestMapping("toAdd.html")
	public String toAdd(){
		return "/WEB-INF/view/role/business_from";
	}
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(Business businessEntity){
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			businessEntity.setCreateUser(userEntity.getAccountName());
			int result = businessService.insert(businessEntity);
			if(result > 0)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	@RequestMapping("toEdit.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try
		{
			Business businessEntity = businessService.findById(id != null ?Integer.valueOf(id):-1);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("businessEntity", businessEntity);
			return Common.BACKGROUND_PATH + "/role/business_from";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("edit.html")
	@ResponseBody
	public Object edit(Business businessEntity){
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			businessEntity.setUpdateUser(userEntity.getAccountName());
			int result = businessService.update(businessEntity);
			if(result > 0)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	@RequestMapping("selectByGroupId.html")
	@ResponseBody
	public Object selectByGroupId(String groupId){
		List<Business> list = businessService.selectByGroupId(StringUtils.isEmpty(groupId)?-1:Integer.valueOf(groupId));
		return list;
	}
}
