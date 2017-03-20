package com.qs.cfg.acti.controller;

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
import com.qs.cfg.acti.model.Store;
import com.qs.cfg.acti.service.StoreService;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.DateUtil;
import com.qs.common.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("/cfg/store/")
public class StoreController extends BaseController {

	@Autowired
	private StoreService storeService;
	
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
			return Common.BACKGROUND_PATH + "/cfg/acti/store_list";
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
	
		List<Store> list = storeService.queryListByPage(parameters);
		return getReturnPage(pager, page, list);
	}	
	

	@RequestMapping("addUI.html")
	public String addUI(Model model, HttpServletRequest request, String id) {
		try
		{
			
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/cfg/acti/store_form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request,Integer id) {
		try
		{
			Store record = storeService.findById(id);
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			model.addAttribute("record", record);
			return Common.BACKGROUND_PATH + "/cfg/acti/store_form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(Store record)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			//record.setCreateTime(new Date(System.currentTimeMillis()));
			record.setStarttime(DateUtil.convertToInt(record.getStarttimeStr()));
			record.setEndtime(DateUtil.convertToInt(record.getEndtimeStr()));
			int result = storeService.insert(record);
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
	public Object update(Store record)
	{
		
		record.setStarttime(DateUtil.convertToInt(record.getStarttimeStr()));
		record.setEndtime(DateUtil.convertToInt(record.getEndtimeStr()));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			//stockistLove.setUpdateTime(new Date(System.currentTimeMillis()));
			int result = storeService.update(record);
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
	
			int result = storeService.deleteById(id);
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
