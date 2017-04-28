package com.qs.webside.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.service.IAppGameService;
import com.qs.pub.game.service.IBusinessService;
import com.qs.webside.agent.service.IMemberAgentService;
import com.qs.pub.game.service.IBizGroupAccessService;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.service.IMemberFidesService;
import com.qs.webside.util.BusinessDataSourceUtil;

/**
 * 金牌代理商
 * @author zyy
 *
 */
@Controller
@RequestMapping(value = "/business")
public class AgentEmpowerGoldController extends BaseController{
	
	Logger log = Logger.getLogger(AgentEmpowerGoldController.class);
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private IMemberFidesService memberFidesService;
	
	
	@Autowired
	private IMemberAgentService memberAgentService;
	
	@Autowired
	private BusinessDataSourceUtil businessDataSourceUtil;
	
	@Resource
	private IAppGameService appGameService;
	
	@Autowired
	private IBizGroupAccessService bizGroupAccessService;
	
	
	@RequestMapping("addGoldAgentUi.html")
	public String agentEmpowerGoldUi(HttpServletRequest request,Model model){
		PageUtil page = super.getPage(request);
		model.addAttribute("page", page);
		return "/WEB-INF/view/web/business/agent_agentEmpowerGold_list";
	}

	@ResponseBody
	@RequestMapping("addGoldAgentList.html")
	public Object agentEmpowerGoldList(String gridPager){

		Map<String,Object> parameters = null;
		// 映射Pager对象 
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
		   String gameCode = appGameService.getGameCode(businessDataSourceUtil.getGameType());
		parameters.put("dbName", gameCode+".memberagents");
		List<Map<String, Object>> agentList =businessService.getGoldAgentList(parameters);// businessService.queryFirstAgentByBelongIdPage(parameters);
		return getReturnPage(pager, page, agentList);
	}
	
	@ResponseBody
	@RequestMapping("addGoldUserUi.html")
	public Map<String,Object> addGoldUserUi(int mid,Model model){
		
		businessDataSourceUtil.setReadAllDataSourceType();
		Map<String,Object> result=new HashMap<>();
		MemberFides memberFides=memberFidesService.selectByPrimaryKey(mid);
		MemberAgents memberAgents=memberAgentService.selectByMid(mid);
		MemberBusiness memberBusiness = (MemberBusiness)SecurityUtils.getSubject().getPrincipal();
		String acids=bizGroupAccessService.selectCountAcids(memberBusiness.getId());
		if (StringUtils.isBlank(acids)){
			result.put(CommonContants.ERROR, false);
			result.put(CommonContants.MESSAGE, "该用户没用权限！");
			return result;
		}
		if (memberFides ==null) {
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "当前用户不存在！");
			log.debug("addGoldUserSubmit user info =====::null");
			return result;
		}
		if (memberAgents !=null ){
			result.put("agentType", 1); //改用户是代理商
		}else {
			result.put("agentType", 0);//改用户不是代理商
		}
		result.put("memberFides", memberFides);
		result.put(CommonContants.SUCCESS, true);
		return result;
	}
	
	/**
	 * 金牌代理商授权
	 * @param mid
	 * @param model
	 * @return
	 * @author:zyy
	 * @time:2017年4月28日
	 */
	@ResponseBody
	@RequestMapping("addGoldUserSubmit.html")
	public Map<String,Object> addGoldUserSubmit(int mid,Model model){
		businessDataSourceUtil.setWriteAllDataSourceType();
		
		Map<String,Object> result=new HashMap<>();
		MemberFides memberFides=memberFidesService.selectByPrimaryKey(mid);
		MemberBusiness memberBusiness = (MemberBusiness)SecurityUtils.getSubject().getPrincipal();
		String acids=bizGroupAccessService.selectCountAcids(memberBusiness.getId());
		if (StringUtils.isBlank(acids)){
			result.put(CommonContants.ERROR, false);
			result.put(CommonContants.MESSAGE, "该用户没用权限！");
			return result;
		}
		if (memberFides ==null) {
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "当前用户不存在！");
			log.debug("addGoldUserSubmit user info =====::null");
			return result;
		}
		//更新代理商表省级金牌代理商
		MemberAgents memberAgents=new MemberAgents();
		memberAgents.setMid(mid);
		memberAgents.setGlevel(Byte.valueOf("1"));
		memberAgentService.updateByMid(memberAgents);
		result.put(CommonContants.SUCCESS, true);
		result.put(CommonContants.MESSAGE, "授权成功！");
		return result;
	}
}
