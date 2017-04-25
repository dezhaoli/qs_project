package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.TaxesInviteWeekMapper;
import com.qs.log.game.model.TaxesInviteWeek;
import com.qs.log.game.service.ITaxesInviteWeekService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * //@Author:zun.wei, @Date:2017/4/7 13:42
 *  周统计表
 * Created by zun.wei on 2017/4/7.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class TaxesInviteWeekServiceImpl implements ITaxesInviteWeekService {

    @Resource
    private TaxesInviteWeekMapper taxesInviteWeekMapper;

    @Override
    public int insert(TaxesInviteWeek record) {
        return taxesInviteWeekMapper.insert(record);
    }

    @Override
    public int insertSelective(TaxesInviteWeek record) {
        return taxesInviteWeekMapper.insertSelective(record);
    }

    @Override
    public List<Map<String, Object>> getHistoryAgentsRebateList(Map<String, Object> parameters) {
        return taxesInviteWeekMapper.getHistoryAgentsRebateList(parameters);
    }
    
    
    
	@Override
	public TaxesInviteWeek findTaxesDirectlyWeekByCondition(TaxesInviteWeek record) {
		return taxesInviteWeekMapper.findTaxesDirectlyWeekByCondition(record);
	}


	@Override
	public int updateIsawardByCondition(TaxesInviteWeek record) {
		int c=taxesInviteWeekMapper.updateIsawardByCondition(record);
		return c;
	}

    @Override
    public List<Map<String, Object>> getWeekPayHistoryDetailInfoByDate(Map<String, Object> parameters) {
        return taxesInviteWeekMapper.getWeekPayHistoryDetailInfoByDate(parameters);
    }

    @Override
    public List<Map<String, Object>> getWeekPayinfoByDate(Map<String, Object> parameters) {
        return taxesInviteWeekMapper.getWeekPayInfoByDate(parameters);
    }

	@Override
	public List<TaxesInviteWeek> getWeekPayListByCondition(Map<String, Object> parameters) {
		return taxesInviteWeekMapper.getWeekPayListByCondition(parameters);
	}



}
