package com.qs.webside.agent.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.qs.webside.agent.controller.AgentRoomController;
import com.qs.webside.agent.service.IAgentDateBaseJobService;
import com.qs.log.agent.service.IAgentDateBaseJobLogService;


/**
 * 代理商数据任务计划入口
 * 启动时间：2:00
 * @author zyy
 *
 */
public class DateBaseUapdateAgentJob {

	 /**
     * 日志对象
     */
	private static final Logger log = Logger.getLogger(AgentRoomController.class);  
	
	private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource
	private IAgentDateBaseJobService agentDateBaseJobServiceM;
	
	@Resource
	private IAgentDateBaseJobLogService agentDateBaseJobLogService;
	
    
    /**
     * 更新代理商表commagent 
     * 更新时间：2:00
     * @author:zyy
     * @time:2017年4月13日
     */
    public void updateCommonAgentDataBase(){
    	
    	Map<String,Object> param=new HashMap<String,Object>();
    	log.debug("into updateCommonAgentDataBase star =============:"+sdf.format(new Date()));
    	agentDateBaseJobServiceM.updateCommonAgentDataBase(param);
    }

    /**
     * 代理商log库，对taxes_invite_week 做周统计数据
     * 更新时间：2:30
     * @author:zyy
     * @time:2017年4月13日
     */
    public void updateTaxesInviteWeekDataBase(){
    	Map<String,Object> param=new HashMap<String,Object>();
    	log.debug("into updateTaxesInviteWeekDataBase star =============:"+sdf.format(new Date()));
    	agentDateBaseJobLogService.updateTaxesInviteWeekDataBase(param);
    }
}