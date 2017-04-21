package com.qs.log.game.service.impl;

import com.qs.common.exception.AgentemException;
import com.qs.common.util.DateUtil;
import com.qs.log.game.mapper.TaxesDirectlyWeekMapper;
import com.qs.log.game.model.TaxesDirectlyDay;
import com.qs.log.game.model.TaxesDirectlyWeek;
import com.qs.log.game.service.ITaxesDirectlyDayService;
import com.qs.log.game.service.ITaxesDirectlyWeekService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 代理商周统计表
 * Created by zun.wei on 2017/3/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class TaxesDirectlyWeekServiceImpl implements ITaxesDirectlyWeekService {

    //@Value("${game.gameCode}")
    //private String dbName;

    @Resource
    private TaxesDirectlyWeekMapper taxesDirectlyWeekMapper;
    
    @Resource 
    private ITaxesDirectlyDayService taxesDirectlyDayService;

    @Override
    public int insert(TaxesDirectlyWeek record) {
        return taxesDirectlyWeekMapper.insert(record);
    }

    @Override
    public int insertSelective(TaxesDirectlyWeek record) {
        return taxesDirectlyWeekMapper.insertSelective(record);
    }

    @Override
    public List<Map<String,Object>> queryListPageByAgentBelongIdAndSunDayDate(Map<String, Object> belongIdAndDate) {
        if (belongIdAndDate.get("date") == null || "".equals(belongIdAndDate.get("date")))
            return new ArrayList<Map<String,Object>>();
        return taxesDirectlyWeekMapper.queryListPageByAgentBelongIdAndSunDayDate(belongIdAndDate);
    }

	@Override
	public TaxesDirectlyWeek selectVipNotWeekData(Map<String, Object> parma) {
		
		TaxesDirectlyWeek taxesDirectlyWeek=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Object time="";
		try {
			
			if(!parma.containsKey("data")){
				//默认参数当周最后天
				parma.put("data", DateUtil.getDatalastDay(sdf.format(new Date())));
			}
			time=parma.get("data");
			//判断是当前系统时间的周内
			if(DateUtil.getDateTOWeek(time.toString())== DateUtil.getDateTOWeek(DateUtil.getNewDate())){
				TaxesDirectlyDay	taxesDirectlyDay=taxesDirectlyDayService.getVipWeekDataStatQuery(parma);

				if (taxesDirectlyDay !=null){
					taxesDirectlyWeek.setBindpeople(taxesDirectlyDay.getBindpeople());
					taxesDirectlyWeek.setPlaytimes(taxesDirectlyDay.getPlaytimes());
					taxesDirectlyWeek.setPaycount(taxesDirectlyDay.getPaycount());
				}
			} else {
				
				taxesDirectlyWeek=taxesDirectlyWeekMapper.selectVipNotWeekData(parma);
			}
			if (taxesDirectlyWeek ==null){
				taxesDirectlyWeek=new TaxesDirectlyWeek(0,new Short("0"),0);
			}
			
		} catch (ParseException e) {
			throw new AgentemException(e);
		}
		return taxesDirectlyWeek;
	}

}
