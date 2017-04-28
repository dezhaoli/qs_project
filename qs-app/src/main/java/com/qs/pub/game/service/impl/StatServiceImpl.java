package com.qs.pub.game.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.qs.common.util.DateUtil;
import com.qs.pub.game.mapper.PlayerPayDayMapper;
import com.qs.pub.game.model.PlayerPayDay;
import com.qs.pub.game.service.StatService;


@Service("statService")
public class StatServiceImpl implements StatService {

	@Autowired
	private PlayerPayDayMapper playerPayDayMapper;
	
	@Value("${game.gametype}")
	private byte gameType;
	
	/**
	 * 增加商务收入
	 */
	@Override
	public int addBizCharge(short bizid,float money)
	{
		PlayerPayDay record=new PlayerPayDay();
		BigDecimal payMoney= new BigDecimal(Float.toString(money));
		record.setPaytotal(payMoney);
		record.setBizid(bizid);
		record.setDate(DateUtil.getNowDate());
		record.setGametype(gameType);
		
		return playerPayDayMapper.insertOrUpdateBizCharge(record);
	}

	
}
