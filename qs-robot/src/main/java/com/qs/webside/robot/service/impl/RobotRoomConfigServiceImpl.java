package com.qs.webside.robot.service.impl;

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

}
