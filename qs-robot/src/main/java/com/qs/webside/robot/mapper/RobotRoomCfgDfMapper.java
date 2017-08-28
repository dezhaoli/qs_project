package com.qs.webside.robot.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.robot.model.RobotRoomCfgDf;
import com.qs.webside.robot.model.RobotRoomConfig;

public interface RobotRoomCfgDfMapper extends IBaseMapper {
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

	
	/**
	 * 查询所有机器人房间信息
	 * @return 机器人房间信息的集合
	 */
	List<Map<String, Object>> findRobotRoomCigInfo();

	List<Map<String, Object>> queryRobotConfig();

}