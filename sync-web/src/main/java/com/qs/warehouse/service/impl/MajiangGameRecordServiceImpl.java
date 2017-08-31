package com.qs.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.warehouse.mapper.MajiangGameRecordMapper;
import com.qs.warehouse.model.MajiangGameRecord;
import com.qs.warehouse.service.IMajiangGameRecordService;

@Service
public class MajiangGameRecordServiceImpl implements IMajiangGameRecordService{

	@Autowired
	private MajiangGameRecordMapper majiangGameRecordMapper;
	
	@Override
	public int insertSelective(MajiangGameRecord record) {
		
		return majiangGameRecordMapper.insertSelective(record);
	}

	
}
