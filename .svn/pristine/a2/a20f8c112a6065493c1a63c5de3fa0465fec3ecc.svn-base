package com.qs.webside.robot.service;

import java.util.List;
import java.util.Map;

import com.qs.webside.robot.model.RobotRoomCfgDf;


/**
 * 机器人开房玩法配置业务接口
 * @author zhengshengfei
 *
 */
public interface IRobotRoomCfgDfService {
	  	int deleteByPrimaryKey(Integer id);

	    int insert(RobotRoomCfgDf record);

	    int insertSelective(RobotRoomCfgDf record);

	    RobotRoomCfgDf selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(RobotRoomCfgDf record);

	    int updateByPrimaryKey(RobotRoomCfgDf record);
	    /**
	     * 机器人创建房间配置 ，如果有数据就修改，如果没数据就新增
	     * @param 机器人创建房间配置对象
	     * @return
	     */
		int insertOrUpdate(RobotRoomCfgDf rrc);
		
		/**
		 * 
		 * @param roomType 标题
		 * @return 机器人默认创建房间配置
		 */
		RobotRoomCfgDf queryRobotConfigByType(int roomType);
		
		List<Map<String, Object>> findRobotRoomCigInfo();
}
