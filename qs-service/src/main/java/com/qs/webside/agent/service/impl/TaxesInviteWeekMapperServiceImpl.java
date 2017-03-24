package com.qs.webside.agent.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.qs.log.agent.mapper.TaxesInviteWeekMapper;
import com.qs.log.agent.model.TaxesInviteWeek;
import com.qs.log.agent.model.TeamInfo;
import com.qs.webside.agent.service.ITaxesInviteWeekMapperService;

import weixin.popular.bean.message.templatemessage.TemplateMessageItem;

@Service
public class TaxesInviteWeekMapperServiceImpl implements ITaxesInviteWeekMapperService {
 
	@Resource
    private TaxesInviteWeekMapper taxesInviteWeekMapper;
	
	@Override
	public int insert(TaxesInviteWeek record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TaxesInviteWeek record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> selectByIdTexesInviteWeek(Map<String, Object> param) {
		TaxesInviteWeek  result=new TaxesInviteWeek(); 
		Map<String,Object> map=new HashMap<String,Object>();
		String info="";
		int rebateTotal=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if (!param.containsKey("date")){
			param.put("date", sdf.format(new Date()));
		}
		
		TaxesInviteWeek  results=taxesInviteWeekMapper.selectByIdTexesInviteWeek(param);
		if (results != null){
			info=results.getInfo();
			if(result.getPaytotal()==null){
				
				map.put("paytotal", result.getPaytotal());
			}else {
				map.put("paytotal", "0");
			}
		}else{
			//数据不存在
			info="[['0','0','0'],['0','0','0'],['0','0','0']]";
			map.put("paytotal", "0");
		}
		map= setStringToMap( info);
		Object one=map.get("teamInfo01");
		Object tow=map.get("teamInfo11");
		Object thr=map.get("teamInfo21");
		rebateTotal=Integer.valueOf(one.toString())+Integer.valueOf(tow.toString())+Integer.valueOf(thr.toString());
		map.put("rebateTotal", rebateTotal);
		return map;
	}

	
	public static Map<String,Object> setStringToMap(String info){
		  Map<String,Object> map=new HashMap<String ,Object>();
		  JSONArray js=JSONArray.parseArray(info);
		  for (int i = 0; i < js.size(); i++) {
			  JSONArray jsa=js.getJSONArray(i);
			  for (int j = 0; j < jsa.size(); j++) {
				  map.put("teamInfo"+j+i, jsa.getString(i));
			}
		}
		  return map;
	}
	/**
	 * 将一根字符数组转为map对象
	 * @param info
	 * @return
	 */
	public static Map<String,Object> setStringToMapList(String info){
		  Map<String,Object> map=new HashMap<String ,Object>();
		  JSONArray js=JSONArray.parseArray(info);
		  List<TeamInfo> team=new ArrayList<>();
		  for (int i = 0; i < js.size(); i++) {
			  JSONArray jsa=js.getJSONArray(i);
			  TeamInfo ti=new TeamInfo();
			  for (int j = 0; j < jsa.size(); j++) {
				  if (j==0){
					  ti.setTopUp(jsa.getString(j));
				  }
				  if (j==1){
					  ti.setCount(jsa.getString(j));
				  }
			}
			  team.add(ti);
		}
		  map.put("teamInfo", team);
		  return map;
	}
	
}
