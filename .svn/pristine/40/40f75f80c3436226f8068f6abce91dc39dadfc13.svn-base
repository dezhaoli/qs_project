package com.qs.log.game.service.impl;

import com.qs.log.game.mapper.TaxesInviteWeekMapper;
import com.qs.log.game.model.TaxesInviteWeek;
import com.qs.log.game.service.ITaxesInviteWeekService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: TaxesInviteWeekServiceImpl 
 * @描述:周结算支付
 * @author moys
 * @date 2017年7月11日 下午7:43:58
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
    
    /**
     * 
     * @标题: getPaoDeKuaiWeekPayInfoByDate 
     * @描述: 跑得快周结算列表(单独方法)
     *
     * @参数信息
     *    @param parameters
     *    @return
     *
     * @返回类型 List<Map<String,Object>>
     * @开发者 QS
     * @可能抛出异常
     */
    @Override
    public List<Map<String, Object>> getPaoDeKuaiWeekPayInfoByDate(Map<String, Object> parameters) {
        return taxesInviteWeekMapper.getPaoDeKuaiWeekPayInfoByDate(parameters);
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
