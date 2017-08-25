package com.qs.webside.robot.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.webside.robot.mapper.AgentRobotConfigMapper;
import com.qs.webside.robot.mapper.RobotRoomConfigMapper;
import com.qs.webside.robot.model.AgentRobotConfig;
import com.qs.webside.robot.model.RobotRoomConfig;
import com.qs.webside.robot.service.IAgentRobotConfigService;
/**
 * Created by zun.wei on 2017/8/25 18:27.
 * Description:代理商私聊机器人业务层
 */
@Service
public class AgentRobotConfigServiceImpl implements IAgentRobotConfigService {
	
	@Autowired
	private AgentRobotConfigMapper agentRobotConfigMapper;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return agentRobotConfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AgentRobotConfig record) {
		
		return agentRobotConfigMapper.insert(record);
	}

	@Override
	public int insertSelective(AgentRobotConfig record) {
		
		return agentRobotConfigMapper.insertSelective(record);
	}

	@Override
	public AgentRobotConfig selectByPrimaryKey(Integer id) {
		
		return agentRobotConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AgentRobotConfig record) {
		
		return agentRobotConfigMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AgentRobotConfig record) {
		
		return agentRobotConfigMapper.updateByPrimaryKey(record);
	}
	
	 /**
	  * 
	  * @author zsf   @date 创建时间：2017年8月25日 下午5:31:43 
	  * @Description: 插入用户和机器人私聊的开房配置，没有就插入有就跟新
	  * @param  
	  * @return
	  */
	@Override
	public int insertOrUpdate(AgentRobotConfig arc) {
		
		return agentRobotConfigMapper.insertOrUpdate(arc);
	}
	
	

	
	

}
