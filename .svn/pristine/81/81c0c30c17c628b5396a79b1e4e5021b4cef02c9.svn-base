package com.qs.webside.agent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.DateUtil;
import com.qs.common.util.PageUtil;
import com.qs.datasource.util.ConstantUtil;
import com.qs.log.game.model.TaxesInvite;
import com.qs.pub.game.model.AppGame;
import com.qs.pub.game.service.IAppGameService;
import com.qs.webside.agent.service.IMemberAgentService;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.util.AgentDataSourceUtil;
import com.qs.webside.util.AgentUtil;

@Controller
@RequestMapping(value = "/vip")
public class AgentVipController extends BaseController{
	Logger log = Logger.getLogger(AgentVipController.class);  
	
	@Autowired
	private AgentDataSourceUtil agentDataSourceUtil;
	
	@Autowired
	private IAppGameService appGameService;
	
	@Autowired
	private IMemberAgentService memberAgentService;
	
	
	@RequestMapping(value = "pokerUi.html")
	public String pokerUi(Model model,HttpServletRequest req){
		PageUtil page = new PageUtil(req);
        model.addAttribute("page", page);
		return "WEB-INF/view/web/agent/vip/agent_poker_list";
	}
	/**
	 * 直属会员牌局数查询
	 * @param gridPager
	 * @return
	 * @author:zyy
	 * @time:2017年8月15日
	 */
	@ResponseBody
	@RequestMapping(value = "pokerList.html")
	public Object pokerList(String gridPager){
		agentDataSourceUtil.setReadDataSourceType();
		int gameType=agentDataSourceUtil.getGameType();
		Map<String, Object> parameters = null;
		MemberAgents memberAgents = (MemberAgents) SecurityUtils.getSubject().getPrincipal();
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			return null;//如果没有mid传过来则不执行查询。
		}
		parameters.put("mid", AgentUtil.getAgentMid());
		parameters.put("code", memberAgents.getCode());
		// 设置分页，page里面包含了分页信息
		AppGame appGame= appGameService.selectByPrimaryKey(gameType);
		parameters.put("dbName",  appGame.getGameCode() + "_log.majiang_game_record");
		Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
		List<Map<String,Object>> list=memberAgentService.getVipPokelist(parameters);
		return getReturnPage(pager, page, list);
	}
	
	@RequestMapping(value = "openRoomSumUi.html")
	public String openRoomSumUi(Model model,HttpServletRequest req){
		PageUtil page = new PageUtil(req);
        model.addAttribute("page", page);
		return "WEB-INF/view/web/agent/vip/agent_openRoomSum_list";
	}
	
	/**
	 * 
	 * 直属会员开房数查询
	 * @param gridPager
	 * @return
	 * @author:zyy
	 * @time:2017年8月15日
	 */
	@ResponseBody
	@RequestMapping(value = "openRoomSumList.html")
	public Object openRoomSumList(String gridPager){
		agentDataSourceUtil.setReadDataSourceType();
		int gameType=agentDataSourceUtil.getGameType();
		Map<String, Object> parameters = null;
		MemberAgents memberAgents = (MemberAgents) SecurityUtils.getSubject().getPrincipal();
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			return null;//如果没有mid传过来则不执行查询。
		}
		parameters.put("mid", AgentUtil.getAgentMid());
		parameters.put("code", memberAgents.getCode());
		// 设置分页，page里面包含了分页信息
		AppGame appGame= appGameService.selectByPrimaryKey(gameType);
		parameters.put("dbName",  appGame.getGameCode() + "_log.majiang_game_record");
		Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
		List<Map<String,Object>> list=memberAgentService.getVipOpenRoomlist(parameters);
		return getReturnPage(pager, page, list);
	}
}
