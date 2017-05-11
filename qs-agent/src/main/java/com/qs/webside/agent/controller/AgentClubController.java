package com.qs.webside.agent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ctc.wstx.util.DataUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.DateUtil;
import com.qs.common.util.DateUtils;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.model.AppGame;
import com.qs.webside.agent.model.AgentClubMember;
import com.qs.webside.agent.service.IAgentClubGroupService;
import com.qs.webside.agent.service.IAgentClubMemberService;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.service.IMemberFidesService;
import com.qs.webside.member.service.impl.MemberFidesServiceImpl;
import com.qs.webside.util.AgentDataSourceUtil;
import com.qs.webside.util.AgentUtil;

@Controller
@RequestMapping(value = "/agentClub")
public class AgentClubController extends BaseController{

	Logger log = Logger.getLogger(AgentClubController.class);  
	
	@Autowired
	private IAgentClubMemberService agentClubMemberService ;
	
	@Autowired 
	private IAgentClubGroupService agentClubGroupService ;
	
	@Autowired
	private AgentDataSourceUtil agentDataSourceUtil;
	
	@Autowired
	private IMemberFidesService memberFidesService;

	@RequestMapping("infoAgentClubUi.html")
	public String infoAgentClubUi(Model model,HttpServletRequest req){
		PageUtil page = new PageUtil(req);
        model.addAttribute("page", page);
        return "/WEB-INF/view/web/agent/club/agent_club_list";
	}
	
	/**
	 * 根据当前cmid获取当前俱乐部成员
	 * @param gridPager
	 * @return
	 * @author:zyy
	 * @time:2017年5月10日
	 */
	@ResponseBody
	@RequestMapping("infoAgentClubList.html")
	public Object infoAgentClubList(String gridPager){
		
		agentDataSourceUtil.setReadDataSourceType();//设置数据源
		
		Map<String, Object> parameters = null;
		MemberAgents memberAgents = (MemberAgents) SecurityUtils.getSubject().getPrincipal();
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			return null;//如果没有mid传过来则不执行查询。
		}
		//查询俱乐部用户
		parameters.put("cmid", memberAgents.getMid());
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
		List<Map<String,Object>> list=agentClubMemberService.selectByMid(parameters);
		return getReturnPage(pager, page, list);
	}
	
	/**
	 * 给当前代理商添加俱乐部成员
	 * @param mid
	 * @return
	 * @author:zyy
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("addAgentClubInfo.html")
	public Map<String, Object> addAgentClubInfo(int mid){
		
		agentDataSourceUtil.setWriteDataSourceType();//设置数据源
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		AgentClubMember aClubMember=new AgentClubMember();
		if (StringUtils.isBlank(mid+"")) {
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "请输入MID！");
			return result;
		}
		//判断用户存在否
		 MemberFides memberFides =memberFidesService.selectByPrimaryKey(mid);
		 if (memberFides ==null){
			 result.put(CommonContants.SUCCESS, false);
			 result.put(CommonContants.MESSAGE, "当前用户不存在！");
			 return result;
		 }

		//判断用户是否已经存在俱乐部里面
		MemberAgents memberAgents = (MemberAgents) SecurityUtils.getSubject().getPrincipal();
		parameters.put("cmid", memberAgents.getMid());
		parameters.put("mid", mid);
		AgentClubMember agentClubMember =agentClubMemberService.selectByMidInfo(parameters);
		if (agentClubMember !=null ){
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "该用户已经是您成员！");
			return result;
		}
		
		aClubMember.setCmid(memberAgents.getMid());
		aClubMember.setCreateTime(DateUtil.getNowDate());
		aClubMember.setMid(mid);
		agentClubMemberService.insertSelective(aClubMember);
		result.put(CommonContants.SUCCESS, true);
		result.put(CommonContants.MESSAGE, "添加成功！");
		// 映射Pager对象
		// 判断是否包含自定义参数
		return result;
	}
	
	/**
	 * 删除当前代理商指定mid俱乐部成员
	 * @param id
	 * @return
	 * @author:zyy
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("deleteAgentClubInfo.html")
	public Map<String, Object> deleteAgentClubInfo(int id){
		
		agentDataSourceUtil.setWriteDataSourceType();//设置数据源
		
		Map<String, Object> result = new HashMap<String, Object>();
		agentClubMemberService.deleteByPrimaryKey(id);
		result.put(CommonContants.SUCCESS, true);
		result.put(CommonContants.MESSAGE, "删除成功！");
		return result;
	}
	
	/**
	 * 代开房
	 * @param id
	 * @param state
	 * @return
	 * @author:zyy
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("updateAgentClubInfo.html")
	public Map<String, Object> updateAgentClubInfo(int id,int state){
		
		agentDataSourceUtil.setWriteDataSourceType();//设置数据源
		
		Map<String, Object> result = new HashMap<String, Object>();
		AgentClubMember agentClubMember= new AgentClubMember();
		agentClubMember.setId(id);
		agentClubMember.setOpenRoom(state);
		int count =agentClubMemberService.updateByPrimaryKeySelective(agentClubMember);
		if (count !=0 ){
			result.put(CommonContants.SUCCESS, true);
			result.put(CommonContants.MESSAGE, "开房成功！");
		}else {
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "开房失败！");
		}
		return result;
	}
	
}
