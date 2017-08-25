package com.qs.webside.robot.mapper;

import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.robot.model.AgentRobotConfig;

public interface AgentRobotConfigMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentRobotConfig record);

    int insertSelective(AgentRobotConfig record);

    AgentRobotConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentRobotConfig record);

    int updateByPrimaryKey(AgentRobotConfig record);
    
    /**
	  * 
	  * @author zsf   @date 创建时间：2017年8月25日 下午5:31:43 
	  * @Description: 插入用户和机器人私聊的开房配置，没有就插入有就跟新
	  * @param  
	  * @return
	  */
	int insertOrUpdate(AgentRobotConfig arc);
    
}