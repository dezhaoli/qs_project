package com.qs.webside.robot.mapper;

import java.util.List;
import java.util.Map;

import com.qs.common.base.basemapper.IBaseMapper;
import com.qs.webside.robot.model.RobotRoomConfig;

public interface RobotRoomConfigMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RobotRoomConfig record);

    int insertSelective(RobotRoomConfig record);

    RobotRoomConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RobotRoomConfig record);

    int updateByPrimaryKey(RobotRoomConfig record);
    
    /**
     * 机器人创建房间配置 ，如果有数据就修改，如果没数据就新增
     * @param 机器人创建房间配置对象
     * @return
     */
	int insertOrUpdate(RobotRoomConfig rrc);
	
	
	/**
	 * 根据用户游戏id查询机器人配置房间的信息
	 * @param mid 代理商游戏id
	 * @return 
	 */
	List<Map<String,Object>> getRobotRoomCfg(int mid);

    /**
     * @Author:zun.wei , @Date:2017/8/18 18:11
     * @Description:根据mid和房间类型获取机器人房间配置
     * @param parameters
     * @return
     */
    RobotRoomConfig getRobotRoomCfgByMidAndType(Map<String, Object> parameters);
    /**
     * 根据游戏id和机器人名字查询房间配置
     * @param map
     * @return
     */
    RobotRoomConfig queryRobotCfgByMidAndRobotName(Map<String, Object> map);

}