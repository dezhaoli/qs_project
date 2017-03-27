package com.qs.log.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qs.log.game.mapper.TaxesDirectlyDayMapper;
import com.qs.log.game.model.TaxesDirectlyDay;
import com.qs.log.game.service.ITaxesDirectlyDayService;

@Service
public class TaxesDirectlyDayService implements ITaxesDirectlyDayService {

	@Resource 
	private TaxesDirectlyDayMapper  taxesDirectlyDayMapper ;
	@Override
	public int insert(TaxesDirectlyDay record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TaxesDirectlyDay record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TaxesDirectlyDay getVipWeekDataStatQuery(Map<String, Object> param) {
		return taxesDirectlyDayMapper.getVipWeekDataStatQuery(param);
	}

	@Override
	public List<TaxesDirectlyDay> getVipWeekDataStatDetailQuery(Map<String, Object> param) {
		return taxesDirectlyDayMapper.getVipWeekDataStatDetailQuery(param);
	}

	
}
