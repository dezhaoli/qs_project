package com.qs.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.sync.model.DouniuGameRecord;
import com.qs.warehouse.mapper.DouniuGameRecordMapper;
import com.qs.warehouse.service.IDouniuGameRecordService;

@Service
public class DouniuGameRecordServiceImpl implements IDouniuGameRecordService {

	@Autowired
	private DouniuGameRecordMapper douniuGameRecordMapper;
	@Override
	public int insertSelective(DouniuGameRecord record) {
		
		return douniuGameRecordMapper.insertSelective(record);
	}

}
