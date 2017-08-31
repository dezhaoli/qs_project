package com.qs.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.drc.clusterclient.message.ClusterMessage;
import com.qs.warehouse.mapper.RoomRecordMapper;
import com.qs.warehouse.model.RoomRecord;
import com.qs.warehouse.service.IRoomRecordService;




@Service
public class RoomRecordServiceImpl implements IRoomRecordService {
	
	@Autowired
	private RoomRecordMapper roomRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return roomRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RoomRecord record) {
		
		return roomRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(RoomRecord record) {
		
		return roomRecordMapper.insertSelective(record);
	}

	@Override
	public RoomRecord selectByPrimaryKey(Long id) {
		
		return roomRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RoomRecord record) {
		
		return roomRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RoomRecord record) {
		
		return roomRecordMapper.updateByPrimaryKey(record);
	}

	
}
