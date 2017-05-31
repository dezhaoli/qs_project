package com.qs.cfg.acti.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qs.cfg.acti.mapper.StoreMapper;
import com.qs.cfg.acti.model.Store;
import com.qs.cfg.acti.service.StoreService;
import com.qs.common.constant.CacheConstan;
import com.qs.common.util.DateUtil;


@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreMapper storeMapper;
	
	@Override
	//@Cacheable(value={CacheConstan.STORE_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
	public Integer getGoldByPayMoney(Integer money) {
		Store store=storeMapper.getGoldByPayMoney(money);
		Integer gold=0;
		if(null!=store){
			Integer nowTime=DateUtil.currentTimeToInt();
			Integer startTime=store.getStarttime();
			Integer endTime=store.getEndtime();
			if(nowTime>=startTime&&nowTime<=endTime){
				gold=Integer.parseInt(store.getSong());
			}else{
				gold=store.getGold();
			}
	   	     if(gold==0){
			 gold=store.getGold();
			}
			
		}
		return gold;
		
	}
	
	
	@Override
	@Cacheable(value={CacheConstan.STORE_CACHE_STORE_NAME},key="#root.methodName")
	public String createStoreJson() {
		Map<String, Object> parameter=new HashMap<String, Object>();
		List<Store> storeList=storeMapper.queryListByPage(parameter);
		
		 JSONObject root=new JSONObject();
		 JSONObject contentJson=new JSONObject();
		 JSONObject itemJson=new JSONObject();
		 JSONArray storeJsonArr = new JSONArray();
		 if(null!=storeList){
		 for(Store store:storeList){
			 JSONObject chilJson=new JSONObject();
			 JSONObject chilJson_type=new JSONObject();
			 JSONObject chilJson_item=new JSONObject();
			 JSONObject chilJson_item_att=new JSONObject();
			 chilJson_item_att.put("money",store.getMoney());
			 chilJson_item_att.put("gold",store.getGold());
			 chilJson_item_att.put("song",store.getSong());
			 chilJson_item_att.put("img",store.getImg());
			 //chilJson_item_att.put("starttime",store.getStarttimeStr());
			 //chilJson_item_att.put("endtime",store.getEndtimeStr());
			 chilJson_item_att.put("starttime",store.getStarttime());
			 chilJson_item_att.put("endtime",store.getEndtime());
			 chilJson_item_att.put("productId",store.getProductId());		 
			 chilJson_item.put("@attributes",chilJson_item_att);
			// chilJson.put("item",chilJson_item);  
			 //chilJson_type.put("type",systemRoom.getType());
			 //chilJson.put("@attributes",chilJson_type);
			 storeJsonArr.add(chilJson_item);
		 }
		}
		 itemJson.put("item",storeJsonArr);
		 contentJson.put("store",itemJson);
		 root.put("content",contentJson);
		 //创建文件room.data
		 System.out.println(root.toJSONString());
		return root.toJSONString();
	}

	
}
