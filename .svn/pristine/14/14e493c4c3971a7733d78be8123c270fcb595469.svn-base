package com.qs.webside.agent.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.sys.service.agent.service.IMemberAgentService;

@Controller
@RequestMapping(value = "/agentroom")
public class AgentRoomController {

	@Autowired
	private IMemberAgentService memberAgentService;
	/**
	 * 代理商须知
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "instructions.html")
	public String agentinstructions(Model model,HttpServletRequest req){
		
		return "WEB-INF/view/web/agent/query/agent_instructions_list";
	}
	
	/**
	 * 团队充值统计
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "teamrechargecount.html")
	public String teamrechargecount(Model model,HttpServletRequest req,HttpSession session,Date startTime,Date endTime){
		
		  Map<String ,Object > resutl=new HashMap<String,Object>();
		Map<String,Object> parma=new HashMap<String,Object>();
		MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
		parma.put("mid",memberAgents.getParentId() );
		if(startTime !=null) {
			parma.put("sdate",startTime );
		}else {
			parma.put("sdate",new Date() );
		}
		if(endTime !=null) {
			parma.put("edate",endTime );
		}else {
			parma.put("edate", new Date());
		}
		resutl=memberAgentService.getTaxesInviteMapper(parma);
		model.addAttribute("resultMap", resutl);
		return "WEB-INF/view/web/agent/query/team_recharge_count_form";
	}
	
	
}
