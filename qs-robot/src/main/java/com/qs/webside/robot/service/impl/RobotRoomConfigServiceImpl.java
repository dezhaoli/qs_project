package com.qs.webside.robot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qs.webside.robot.mapper.RobotRoomConfigMapper;
import com.qs.webside.robot.model.RobotRoomConfig;
/**
 * 处理机器人开房的房间配置
 */
import com.qs.webside.robot.service.IRobotRoomConfigService;
@Service
public class RobotRoomConfigServiceImpl implements IRobotRoomConfigService {

	@Autowired
	private RobotRoomConfigMapper robotRoomConfigMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return robotRoomConfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RobotRoomConfig record) {
		
		return robotRoomConfigMapper.insert(record);
	}

	@Override
	public int insertSelective(RobotRoomConfig record) {
		
		return robotRoomConfigMapper.insertSelective(record);
	}

	@Override
	public RobotRoomConfig selectByPrimaryKey(Integer id) {
		
		return robotRoomConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RobotRoomConfig record) {
		
		return robotRoomConfigMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RobotRoomConfig record) {
		
		return robotRoomConfigMapper.updateByPrimaryKeySelective(record);
	}

	
	/**
     * 机器人创建房间配置 ，如果有数据就修改，如果没数据就新增
     * @param 机器人创建房间配置对象
     * @return
     */
	@Override
	public int insertOrUpdate(RobotRoomConfig rrc) {
		
		return robotRoomConfigMapper.insertOrUpdate(rrc);
	}
	
	
	/**
	 * 根据用户游戏id查询机器人配置房间的信息
	 * @param mid
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getRobotRoomCfg(int mid) {
		return robotRoomConfigMapper.getRobotRoomCfg(mid);
	}

	@Override
	public RobotRoomConfig getRobotRoomCfgByMidAndType(Map<String, Object> parameters) {
		return robotRoomConfigMapper.getRobotRoomCfgByMidAndType(parameters);
	}

	 /**
     * @Author:zsf , @Date:2017/8/25 9:23
     * @Description:根据mid 和robotName查询出房间配置
     * @param mid 游戏id
     * @param robotName 机器人名称
     * @return map 房间的配置
     */
	@Override
	public RobotRoomConfig queryRobotCfgByMidAndRobotName(int mid, String robotName) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("mid", mid);
		map.put("robotName", robotName);
		return robotRoomConfigMapper.queryRobotCfgByMidAndRobotName(map);
	}
	/**
	 * 
	 * @author zsf   @date 创建时间：2017年8月28日 上午9:33:00 
	 * @Description: 根据用户id和房间类型查询所有的子集配置
	 * @param  游戏id  机器人名字
	 * @return  所有的子集配置
	 */
	public List<Map<String, Object>> querySubsetByMidAndRoomType(int mid,int roomType){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("mid", mid);
		map.put("roomType", roomType);
		return robotRoomConfigMapper.querySubsetByMidAndRoomType(map);
	}

}
