package com.qs.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.warehouse.mapper.MajiangGameRecordSubMapper;
import com.qs.warehouse.model.MajiangGameRecordSub;
import com.qs.warehouse.service.IMajiangGameRecordSubService;



@Service
public class MajiangGameRecordSubServiceImpl implements IMajiangGameRecordSubService{

	@Autowired
	private MajiangGameRecordSubMapper majiangGameRecordSubMapper;
	
	@Override
	public int insertSelective(MajiangGameRecordSub record) {
		
		return majiangGameRecordSubMapper.insertSelective(record);
	}

	
}
