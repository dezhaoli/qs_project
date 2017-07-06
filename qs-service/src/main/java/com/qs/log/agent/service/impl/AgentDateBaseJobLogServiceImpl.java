package com.qs.log.agent.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.agent.mapper.AgentDateBaseJobLogMappper;
import com.qs.log.agent.service.IAgentDateBaseJobLogService;

@Service
public class AgentDateBaseJobLogServiceImpl implements IAgentDateBaseJobLogService {

	@Resource
	private AgentDateBaseJobLogMappper agentDateBaseJobLogMappper ;
	
	@Override
	public void updateTaxesInviteWeekDataBase(Map<String, Object> param) {
		agentDateBaseJobLogMappper.updateTaxesInviteWeekDataBase(param);;
	}

	@Override
	public void statisticalDailyTable(Map<String, Object> param) {
		agentDateBaseJobLogMappper.statisticalDailyTable(param);
	}

	@Override
	public void delTable40(Map<String, Object> param) {
		agentDateBaseJobLogMappper.delTable40(param);
	}

	@Override
	public void createRoomCardCount(Map<String,Object> param) {
		dropProcedureRoomCartCount();//删除存储过程
		agentDateBaseJobLogMappper.createRoomCardCount();//创建表如果不存在
		createRoomCardCountFuntion(param);//创建存储过程
		executeRoomCardCount();//执行存储过程
	}

	@Override
	public void dropProcedureRoomCartCount() {
		agentDateBaseJobLogMappper.dropProcedureRoomCartCount();
	}

	@Override
	public void createRoomCardCountFuntion(Map<String, Object> param) {
		agentDateBaseJobLogMappper.createRoomCardCountFuntion(param);
	}

	@Override
	public void executeRoomCardCount() {
		agentDateBaseJobLogMappper.executeRoomCardCount();
	}

}
