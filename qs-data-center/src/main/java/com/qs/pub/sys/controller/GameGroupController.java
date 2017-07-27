/*
 * 文件名：GameGroupController.java	 
 * 时     间：下午2:29:46
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

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

import com.qs.pub.sys.model.BusinessGroup;
import com.qs.pub.sys.model.GameDict;
import com.qs.pub.sys.model.GameGroup;
import com.qs.pub.sys.model.Group;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.BusinessService;
import com.qs.pub.sys.service.GameGroupService;

import com.qs.webside.util.TreeUtil;

import jodd.util.StringUtil;

/** 
 * @ClassName: GroupController 
 * @描述: (处理游戏分组管理的业务) 
 * @author zsf
 * @date 2017年6月14日 下午2:29:46 
 */
@Controller
@RequestMapping("/game/")
public class GameGroupController extends BaseController
{
	@Autowired
	private GameGroupService gameGroupService;

	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	/**
	 * 处理游戏分组页面的跳转
	 * @param gameType
	 * @param model
	 * @return
	 */
	@RequestMapping("toGameGroupListUi.html")
	public String toMemberagentsListUi(String gameType, Model model)
	{
		model.addAttribute("gameType", gameType);
		return "/WEB-INF/view/role/gameGroup";
	}
	/**
	 * 查找游戏分组数据并在页面上回显
	 * @param gridPager
	 * @param response
	 * @return
	 */
	@RequestMapping("groupList.html")
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
					List<GameGroup> list = gameGroupService
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
				
				List<GameGroup> list = gameGroupService
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
		return Common.BACKGROUND_PATH + "/role/gameGroup_form";
	}
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(GameGroup groupEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			groupEntity.setCreateTime(new Date(System.currentTimeMillis()));
			int result = gameGroupService.insert(groupEntity);
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
	public String editUI(Model model, HttpServletRequest request, Integer id) {
		try
		{
			GameGroup groupEntity = gameGroupService.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("groupEntity", groupEntity);
			return Common.BACKGROUND_PATH + "/role/gameGroup_form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(GameGroup groupEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			groupEntity.setUpdateTime(new Date());
			int result = gameGroupService.update(groupEntity);
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
//	
//	
//	@RequestMapping("withoutAuth/validateRoleName.html")
//	@ResponseBody
//	public Object validateRoleName1(@RequestParam(value="groupName")String groupName){
//		try
//		{
//			GameGroup groupEntity = gameGroupService.findByName(groupName);
//			if(groupEntity == null)
//			{
//				return true;
//			}else
//			{
//				return false;
//			}
//		}catch(Exception e)
//		{
//			throw new AjaxException(e);
//		}
//	}
//	
//	
	/**
	 * 分配游戏权限的页面跳转
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("permissionUI.html")
	public String permissionUI(Model model, HttpServletRequest request, Integer id) {
		try
		{
			GameGroup groupEntity = gameGroupService.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("groupEntity", groupEntity);
			return Common.BACKGROUND_PATH + "/role/game_group_permission";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	
	/**
	 * 处理分配游戏权限的保存
	 * @param Id  分组id
	 * @param resourceIds  选中游戏id
	 * @return
	 */
	@RequestMapping("permission.html")
	@ResponseBody
	public Object permission(String Id, String resourceIds)
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
			boolean result = gameGroupService.addGameGroupMiddle(Id, list);
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
	public Object resourceTree(String Id) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		List<JSTreeEntity> jstreeList = null;
		try{
			parameter.put("isHide", 0);
			//parameter.put("type", 0);
			parameter.put("id", !StringUtil.isBlank(Id)?Integer.valueOf(Id):-1);
			List<GameDict> list = gameGroupService.queryDictList(parameter);
			jstreeList = new TreeUtil().generateDictJSTree(list);
		}
		catch (Exception e) {
			jstreeList = new ArrayList<JSTreeEntity>();
			logger.error(e.getMessage(), e);
		}
		return jstreeList;
	}
	
	@RequestMapping("withoutAuth/validateGroupName.html")
	@ResponseBody
	public Object validateRoleName(@RequestParam(value="groupName")String groupName){
		try
		{
			GameGroup groupEntity = gameGroupService.findByName(groupName);
			if(groupEntity == null)
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


	
}
