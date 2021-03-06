package com.qs.webside.util;

import java.util.concurrent.TimeUnit;

import org.apache.shiro.SecurityUtils;

import com.qs.webside.member.model.MemberAgents;

public class AgentUtil {
	/**
	 * 获取当前用户的mid
	 * @return
	 */
	public static Integer getAgentMid(){
		Integer mid=0;
		MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
		if (memberAgents != null) {
			mid = memberAgents.getMid();
		}
		return mid;
	}
	
	/**
	 * 获取sitemid
	 * @return
	 * @author:zyy
	 * @time:2017年4月21日
	 */
	public static String getAgentSitemid(){
		String stemid="";
		MemberAgents memberAgents = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
		if (memberAgents != null) {
			stemid = memberAgents.getSitemid();
		}
		return stemid;
	}
	
}
