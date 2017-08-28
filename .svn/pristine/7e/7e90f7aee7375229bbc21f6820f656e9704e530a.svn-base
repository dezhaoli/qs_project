package com.qs.webside.robot.service;

import java.util.List;
import java.util.Map;

import com.qs.webside.robot.model.RobotRoomConfig;
/**
 * 机器人开房玩法配置业务接口
 * @author zhengshengfei
 *
 */
public interface IRobotRoomConfigService {
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
     * @Author:zsf , @Date:2017/8/25 9:23
     * @Description:根据mid 和robotName查询出房间配置
     * @param mid 游戏id
     * @param robotName 机器人名称
     * @return  房间的配置
     */
	RobotRoomConfig queryRobotCfgByMidAndRobotName(int mid,String robotName);
	
	/**
	 * 
	 * @author zsf   @date 创建时间：2017年8月28日 上午9:33:00 
	 * @Description: 根据用户id和房间类型查询所有的子集配置
	 * @param  游戏id  机器人名字
	 * @return  所有的子集配置
	 */
	List<Map<String, Object>> querySubsetByMidAndRoomType(int mid,int roomType);
		
}
