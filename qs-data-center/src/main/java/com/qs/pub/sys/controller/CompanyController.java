/*
 * 文件名：CompanyController.java	 
 * 时     间：上午10:13:09
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.common.model.JSTreeEntity;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.pub.sys.model.Company;
import com.qs.pub.sys.model.GroupCompany;
import com.qs.pub.sys.service.CompanyService;
import com.qs.webside.util.TreeUtil;

import jodd.util.StringUtil;

/** 
 * @ClassName: CompanyController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月15日 上午10:13:09 
 */
@Controller
@RequestMapping("/sysCompany/")
public class CompanyController extends BaseController
{
	@Resource
	private CompanyService companyService;
	
	@RequestMapping("toCompanyListUi.html")
	public String toMemberagentsListUi(Model model)
	{
		return "/WEB-INF/view/role/company";
	}
	@RequestMapping("companyList.html")
	@ResponseBody
	public Object queryGroupList(String gridPager,
			HttpServletResponse response){
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
					List<Company> list = companyService
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
				
				List<Company> list = companyService
						.queryListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	
	@RequestMapping("addUI.html")
	public String addUI() {
		return Common.BACKGROUND_PATH + "/role/company_form";
	}
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(Company companyEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			companyEntity.setCreateTime(new Date(System.currentTimeMillis()));
			int result = companyService.insert(companyEntity);
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
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try
		{
			Company companyEntity = companyService.findById(id != null ?Integer.valueOf(id):-1);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("companyEntity", companyEntity);
			return Common.BACKGROUND_PATH + "/role/company_form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(Company companyEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			companyEntity.setUpdateTime(new Date());
			int result = companyService.update(companyEntity);
			if(result > 0)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "编辑成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "编辑失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	@RequestMapping("withoutAuth/validateRoleName.html")
	@ResponseBody
	public boolean validateRoleName(@RequestParam(value="companyName")String companyName){
		try
		{
			Company companyEntity = companyService.findByName(companyName);
			if(companyEntity == null)
			{
				return true;
			}else
			{
				return false;
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	
	@RequestMapping("permissionUI.html")
	public String permissionUI(Model model, HttpServletRequest request, Integer id) {
		try
		{
			Company companyEntity = companyService.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("companyEntity", companyEntity);
			return Common.BACKGROUND_PATH + "/role/company_permission";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("permission.html")
	@ResponseBody
	public Object permission(String companyId, String resourceIds)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			List<Integer> list = new ArrayList<Integer>();
			if(StringUtil.isNotBlank(resourceIds))
			{
				for (String id : resourceIds.split(",")) {
					list.add(Integer.valueOf(id));
				}
			}
			boolean result = companyService.addRolePermBatch(companyId != null ?Integer.valueOf(companyId):-1, list);
			if(result)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "授权成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "授权失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	@RequestMapping("resourceTree.html")
	@ResponseBody
	public Object resourceTree(String companyId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		List<JSTreeEntity> jstreeList = null;
		try{
			parameter.put("isHide", 0);
			//parameter.put("type", 0);
			parameter.put("companyId", !StringUtil.isBlank(companyId) ? Integer.valueOf(companyId):-1);
			List<GroupCompany> list = companyService.queryBusinessList(parameter);
			jstreeList = new TreeUtil().generateGroupCompanyJSTree(list);
		}
		catch (Exception e) {
			jstreeList = new ArrayList<JSTreeEntity>();
			logger.error(e.getMessage(), e);
		}
		return jstreeList;
	}
}
