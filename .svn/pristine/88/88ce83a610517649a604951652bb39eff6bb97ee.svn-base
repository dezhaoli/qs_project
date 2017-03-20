package com.qs.cfg.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.cfg.sys.model.SystemRoom;
import com.qs.cfg.sys.service.SystemRoomService;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.FileUtils;
import com.qs.common.util.PageUtil;
import com.qs.common.util.XmlUtil;

import jodd.util.StringUtil;



@Controller
@Scope("prototype")
@RequestMapping("/cfg/systemRoom/")
public class SystemRoomController extends BaseController {

	@Autowired
	private SystemRoomService systemRoomService;
	
	@Value("${cfg.dir}")
	private String cfgDir;

	
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {

		try
		{
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/cfg/sys/systemRoom_list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	
	@RequestMapping("list.html")
	@ResponseBody
	public Object list(String gridPager) throws Exception{
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			parameters.put("name", null);
		}
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
	
		List<SystemRoom> list = systemRoomService.queryListByPage(parameters);
		return getReturnPage(pager, page, list);
	}	
	

	@RequestMapping("addUI.html")
	public String addUI(Model model, HttpServletRequest request, String id) {
		try
		{
			
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/cfg/sys/systemRoom_form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request,Integer id) {
		try
		{
			SystemRoom record = systemRoomService.findById(id);
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			model.addAttribute("record", record);
			return Common.BACKGROUND_PATH + "/cfg/sys/systemRoom_form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(SystemRoom record)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			//record.setCreateTime(new Date(System.currentTimeMillis()));
			int result = systemRoomService.insert(record);
			if(result>0){
				map.put(CommonContants.SUCCESS, Boolean.TRUE);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.ADD_SUCCESS);
			}else
			{
				map.put(CommonContants.SUCCESS, Boolean.FALSE);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	

	
	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(SystemRoom record)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			//stockistLove.setUpdateTime(new Date(System.currentTimeMillis()));
			int result = systemRoomService.update(record);
			if(result > 0)
			{
				map.put(CommonContants.SUCCESS, Boolean.TRUE);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.EDIT_SUCCESS);
			}else
			{
				map.put(CommonContants.SUCCESS, Boolean.FALSE);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.EDIT_FAILURE);
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	@RequestMapping("deleteById.html")
	@ResponseBody
	public Object deleteById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
	
			int result = systemRoomService.deleteById(id);
			if(result>0)
			{
				map.put(CommonContants.SUCCESS, true);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.DELETE_SUCCESS);
			}else
			{
				map.put(CommonContants.SUCCESS, false);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.DELETE_SUCCESS);
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	

	
}
