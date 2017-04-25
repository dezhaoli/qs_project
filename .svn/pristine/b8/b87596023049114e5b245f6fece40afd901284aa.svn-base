package com.qs.log.game.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.qs.log.game.mapper.TaxesInviteWeekSendMapper;
import com.qs.log.game.model.TaxesInviteWeekSend;
import com.qs.log.game.service.ITaxesInviteWeekSendService;

/**
 * 
 * @ClassName: TaxesInviteWeekServiceSendImpl 
 * @描述: 活动返利
 * @author moyousheng
 * @date 2017年4月25日 下午4:31:10
 */
@Service
public class TaxesInviteWeekServiceSendImpl implements ITaxesInviteWeekSendService {

    @Resource
    private TaxesInviteWeekSendMapper taxesInviteWeekSendMapper;

    @Override
    public int insert(TaxesInviteWeekSend record) {
        return taxesInviteWeekSendMapper.insert(record);
    }

    @Override
    public int insertSelective(TaxesInviteWeekSend record) {
        return taxesInviteWeekSendMapper.insertSelective(record);
    }

    
    
	@Override
	public TaxesInviteWeekSend findTaxesDirectlyWeekByCondition(TaxesInviteWeekSend record) {
		return taxesInviteWeekSendMapper.findTaxesDirectlyWeekByCondition(record);
	}


	@Override
	public int updateIsawardByCondition(TaxesInviteWeekSend record) {
		int c=taxesInviteWeekSendMapper.updateIsawardByCondition(record);
		return c;
	}

    @Override
    public List<Map<String, Object>> getWeekPayinfoByDate(Map<String, Object> parameters) {
        return taxesInviteWeekSendMapper.getWeekPayInfoByDate(parameters);
    }

	@Override
	public List<TaxesInviteWeekSend> getWeekPayListByCondition(Map<String, Object> parameters) {
		return taxesInviteWeekSendMapper.getWeekPayListByCondition(parameters);
	}



}
