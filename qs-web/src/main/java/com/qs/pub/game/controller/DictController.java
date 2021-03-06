package com.qs.pub.game.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.model.Dict;
import com.qs.pub.game.service.IDictService;
import com.qs.pub.sys.model.UserEntity;
import com.qs.webside.shiro.ShiroAuthenticationManager;


@Controller
@Scope("prototype")
@RequestMapping("/dict/")
public class DictController extends BaseController {

	@Autowired
	private IDictService dictService;
	
		@InitBinder    
	     public void initBinder(WebDataBinder binder) {    
	         binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true)); 
	     }
	
	
	@RequestMapping("dictList.html")
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
			return Common.BACKGROUND_PATH + "/web/dict/dict_list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	@RequestMapping("toAdd.html")
	public String toAdd(Model model, HttpServletRequest request) {
		
			return Common.BACKGROUND_PATH + "/web/dict/dict_add";
		
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
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "id DESC");
	
		List<Dict> list = dictService.queryListByPage(parameters);
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;
	}
	

	@RequestMapping("viewUI.html")
	public String viewUI(Model model, HttpServletRequest request, Integer id) {
		try
		{
			
			//Stockist curStockist =(Stockist)ShiroAuthenticationManager.getBaseUserEntity();
			Dict dict = dictService.findById(id);
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			model.addAttribute("record", dict);
			return Common.BACKGROUND_PATH + "/web/dict/dict_look";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request,Integer id) {
		try
		{
			Dict dict = dictService.findById(id);
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			model.addAttribute("record", dict);
			return Common.BACKGROUND_PATH + "/web/dict/dict_edit";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	

	@RequestMapping("findDictList.html")
	@ResponseBody
	public Object findDictList(String parentId)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			List<Dict> provinceList=dictService.findDictList(parentId);
			
			if(null!=provinceList){
				map.put(CommonContants.SUCCESS, Boolean.TRUE);
				map.put(CommonContants.DATA, provinceList);
				map.put(CommonContants.MESSAGE, CommonContants.ADD_SUCCESS);
			}else
			{
				map.put(CommonContants.SUCCESS, Boolean.FALSE);
				map.put(CommonContants.DATA, provinceList);
				map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	@RequestMapping("selectByName.html")
	@ResponseBody
	public Object selectByName(String name)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		List<Dict> provinceList;
		try
		{
			provinceList=dictService.selectByName();
			
			if(null!=provinceList){
				map.put(CommonContants.SUCCESS, Boolean.TRUE);
				map.put(CommonContants.DATA, provinceList);
				map.put(CommonContants.MESSAGE, CommonContants.ADD_SUCCESS);
			}else
			{
				map.put(CommonContants.SUCCESS, Boolean.FALSE);
				map.put(CommonContants.DATA, provinceList);
				map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return provinceList;
	}
	
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(Dict dict)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String remarks = dict.getRemarks().trim();
		try
		{
			UserEntity userEntity = ShiroAuthenticationManager.getUserEntity();
			dict.setCreateDate(new Date(System.currentTimeMillis()));
			dict.setCreateBy(userEntity.getAccountName());
			dict.setRemarks(remarks==null?"":remarks.trim());
			int result = dictService.insert(dict);
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
	public Object update(Dict dict)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			UserEntity userEntity = ShiroAuthenticationManager.getUserEntity();
			dict.setUpdateBy(userEntity.getAccountName());
			dict.setUpdateDate(new Date(System.currentTimeMillis()));
			int result = dictService.update(dict);
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
	@RequestMapping("updateStatus.html")
	@ResponseBody
	public Integer updateStatus(Dict dict)
	{
		 
		return dictService.updateStatus(dict);
	}
	
	
	@RequestMapping("deleteById.html")
	@ResponseBody
	public Object deleteById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
	
			int result = dictService.deleteById(id);
			if(result>0)
			{
				map.put(CommonContants.SUCCESS, true);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.ADD_SUCCESS);
			}else
			{
				map.put(CommonContants.SUCCESS, false);
				map.put(CommonContants.DATA, null);
				map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	

	
}
