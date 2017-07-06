package com.qs.webside.agent.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

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
	private static final Logger log = Logger.getLogger(DateBaseUapdateAgentJob.class);  
	
	private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource
	private IAgentDateBaseJobService agentDateBaseJobServiceM;
	
	@Resource
	private IAgentDateBaseJobLogService agentDateBaseJobLogService;
	
	@Value("${game.gameCode}")
	private String dbName;

    
    /**
     * 更新代理商表commagent 
     * 更新时间：2:00
     * @author:zyy
     * @time:2017年4月13日
     */
    public void updateCommonAgentDataBase(){
    	
    	Map<String,Object> param=new HashMap<String,Object>();
    	log.debug("into updateCommonAgentDataBase star =============:"+sdf.format(new Date()));
    	if (!StringUtils.isBlank(dbName)){
    		param.put("dbName", dbName);
    		agentDateBaseJobServiceM.updateCommonAgentDataBase(param);
    	}
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

	public void statisticalDailyTable(){
		Map<String, Object> param = new HashMap<String, Object>();
		log.debug("into statisticalDailyTable star =============:"+sdf.format(new Date()));
		agentDateBaseJobLogService.statisticalDailyTable(param);
	}

	public void updateCreateRoomCardCount(){
		Map<String, Object> param = new HashMap<String, Object>();
		log.debug("into updateCreateRoomCardCount star =============:"+sdf.format(new Date()));
		param.put("memberagents", dbName + ".memberagents");
		agentDateBaseJobLogService.createRoomCardCount(param);
	}
	
	/**
	 * 清空表数据 表：
	 *  room_record
	 *	player_record
	 *	majiang_game_record_sub
	 *	majiang_game_record
	 *	gold_log
	 *	ipaddress_use_log
	 * 执行时间每周一4:00执行，保留数据40数据
	 * @param param
	 * @author:zyy
	 * @time:2017年7月3日
	 */
	public void delTable40(){
		Map<String, Object> param = new HashMap<String, Object>();
		log.debug("into delTable40 star =============:"+sdf.format(new Date()));
		agentDateBaseJobLogService.delTable40(param);
	}
	

}
