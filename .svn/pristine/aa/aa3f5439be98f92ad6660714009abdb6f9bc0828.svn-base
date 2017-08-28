package com.qs.webside.robot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.webside.robot.mapper.RobotRoomCfgDfMapper;
import com.qs.webside.robot.model.RobotRoomCfgDf;
import com.qs.webside.robot.service.IRobotRoomCfgDfService;
/**
 * 处理机器人开房的房间配置
 */
@Service
public class RobotRoomCfgDfServiceImpl implements IRobotRoomCfgDfService {

	@Autowired
	private RobotRoomCfgDfMapper robotRoomCfgDfMapper;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return robotRoomCfgDfMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RobotRoomCfgDf record) {
		
		return robotRoomCfgDfMapper.insert(record);
	}

	@Override
	public int insertSelective(RobotRoomCfgDf record) {
		
		return robotRoomCfgDfMapper.insertSelective(record);
	}

	@Override
	public RobotRoomCfgDf selectByPrimaryKey(Integer id) {
		
		return robotRoomCfgDfMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RobotRoomCfgDf record) {
		
		return robotRoomCfgDfMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RobotRoomCfgDf record) {
		
		return robotRoomCfgDfMapper.updateByPrimaryKeySelective(record);
	}

	
	/**
     * 机器人创建房间配置 ，如果有数据就修改，如果没数据就新增
     * @param 机器人创建房间配置对象
     * @return
     */
	@Override
	public int insertOrUpdate(RobotRoomCfgDf rrc) {
		
		return robotRoomCfgDfMapper.insertOrUpdate(rrc);
	}
	
	/**
	 * 
	 * @param roomType 标题
	 * @return 机器人默认创建房间配置
	 */
	@Override
	public RobotRoomCfgDf queryRobotConfigByType(int roomType) {
		
		return robotRoomCfgDfMapper.queryRobotConfigByType(roomType);
	}

	
	/**
	 * 查询所有机器人房间信息
	 * @return 机器人房间信息的集合
	 */
	@Override
	public List<Map<String, Object>> findRobotRoomCigInfo() {
		
		return robotRoomCfgDfMapper.findRobotRoomCigInfo();
	}

	@Override
	public List<Map<String, Object>> queryRobotConfig() {
		return robotRoomCfgDfMapper.queryRobotConfig();
	}


}
