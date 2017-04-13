package com.qs.webside.game.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.webside.game.model.GameShield;
import com.qs.webside.game.service.GameShieIdService;

/**
 * 游戏盾入口
 * @author xin.tu
 *
 */
@Controller
@RequestMapping(value = "/game/shield/")
public class GameShieldController extends BaseController{
	
	@Autowired
	private GameShieIdService gameShieIdService;
	
	@RequestMapping("gameShieldUi.html")
	public String gameShieldUi(Model model,HttpServletRequest request){
		PageUtil page = new PageUtil();
        if (request.getParameterMap().containsKey("page")) {
            page.setPageSize(Integer.valueOf(request.getParameter("rows")));
            page.setPageNum(Integer.valueOf(request.getParameter("page")));
            page.setOrderByColumn(request.getParameter("sidx"));
            page.setOrderByType(request.getParameter("sord"));
        }
        model.addAttribute("page", page);
		return Common.BACKGROUND_PATH + "/web/shieid/shidid_list";
	}
	
	
	@RequestMapping("list.html")
	@ResponseBody
	public Object listData(String gridPager){
		 Map<String, Object> parameters = null;
		Pager pager = JSON.parseObject(gridPager, Pager.class);
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("name", null);
        }
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<GameShield> list = gameShieIdService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
	}
	
	
	@RequestMapping("addUI")
	public String addUI(Model model, HttpServletRequest request, String id){
		PageUtil page = new PageUtil(request);
		model.addAttribute("page", page);
		return Common.BACKGROUND_PATH + "/web/shieid/shidid_form";
	}
	
	
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(GameShield record){
        Map<String, Object> map = new HashMap<String, Object>();
        int result = gameShieIdService.add(record);
        executeRequestResult(result,map);
		return map;
	}
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, Integer id){
		PageUtil page = new PageUtil(request);
		model.addAttribute("page", page);
        model.addAttribute("record", gameShieIdService.findById(id));
        return Common.BACKGROUND_PATH + "/web/shieid/shidid_form";

	}
	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(GameShield record){
		 Map<String, Object> map = new HashMap<String, Object>();
	        int result = gameShieIdService.update(record);
	        executeRequestResult(result,map);
		return map;
	}
	
	@RequestMapping("deleteById.html")
	@ResponseBody
	public Object deleteById(Integer id){
		 Map<String, Object> map = new HashMap<String, Object>();
	        int result = gameShieIdService.delete(id);
	        executeRequestResult(result,map);
		return map;
	}
}
