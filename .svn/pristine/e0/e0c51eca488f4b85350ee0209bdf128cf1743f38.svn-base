package com.qs.cfg.acti.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qs.cfg.acti.mapper.StoreMapper;
import com.qs.cfg.acti.model.Store;
import com.qs.cfg.acti.service.StoreService;
import com.qs.cfg.sys.model.SystemRoom;
import com.qs.common.util.DateUtil;
import com.qs.common.util.FileUtils;


@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreMapper storeMapper;
	
	@Override
	public Integer getGoldByPayMoney(Integer money) {
		Store store=storeMapper.getGoldByPayMoney(money);
		Integer gold=0;
		if(null!=store){
			Integer nowTime=DateUtil.currentTimeToInt();
			Integer startTime=store.getStarttime();
			Integer endTime=store.getStarttime();
			if(nowTime>=startTime&&nowTime<=endTime){
				gold=Integer.parseInt(store.getSong());
			}else{
				gold=store.getGold();
			}
			
		}
		return gold;
		
	}


	

}
