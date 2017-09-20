package com.qs.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.sync.model.DouniuGameRecordSub;
import com.qs.warehouse.mapper.DouniuGameRecordSubMapper;
import com.qs.warehouse.service.IDouniuGameRecordSubService;




@Service
public class DouniuGameRecordSubServiceImpl implements IDouniuGameRecordSubService {

	@Autowired
	private DouniuGameRecordSubMapper douniuGameRecordSubMapper;
	
	@Override
	public int insertSelective(DouniuGameRecordSub record) {
		
		return douniuGameRecordSubMapper.insertSelective(record);
	}



}
