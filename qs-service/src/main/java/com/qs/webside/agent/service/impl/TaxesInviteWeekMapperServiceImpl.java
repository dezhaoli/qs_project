package com.qs.webside.agent.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.qs.log.agent.mapper.TaxesInviteWeekMapper;
import com.qs.log.agent.model.TaxesInviteWeek;
import com.qs.log.agent.model.TeamInfo;
import com.qs.webside.agent.service.IMemberAgentService;
import com.qs.webside.agent.service.ITaxesInviteWeekMapperService;
import com.qs.webside.member.model.MemberAgents;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TaxesInviteWeekMapperServiceImpl implements ITaxesInviteWeekMapperService {
 
	@Resource
    private TaxesInviteWeekMapper taxesInviteWeekMapper;

	@Resource
	private IMemberAgentService memberAgentService;

	//@Value("${game.gameCode}")
	//private String dbName;
	
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
		Double rebateTotal=0.0;
		DecimalFormat df = new DecimalFormat("######0.00");//double 用于保留两位小数金额
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if (!param.containsKey("date")){
			param.put("date", sdf.format(new Date()));
		}
		
		TaxesInviteWeek  results=taxesInviteWeekMapper.selectByIdTexesInviteWeek(param);
		if (results != null){
			info=results.getInfo();
			map= setStringToMap( info);
			if(results.getPaytotal()==null){
				
				map.put("paytotal", "0.00");
			}else {
				map.put("paytotal", df.format(results.getPaytotal()));
			}
		}else{
			//数据不存在
			info="[['0.00','0.00','0.00'],['0.00','0.00','0.00'],['0.00','0.00','0.00']]";
			map= setStringToMap( info);
			map.put("paytotal", "0.00");
			results=new TaxesInviteWeek( new BigDecimal("0.00"),new Byte("0"));
		}
		Object one=map.get("teamInfo01");
		Object tow=map.get("teamInfo11");
		Object thr=map.get("teamInfo21");
		rebateTotal=Double.valueOf(one.toString())+Double.valueOf(tow.toString())+Double.valueOf(thr.toString());
		map.put("rebateTotal", df.format(rebateTotal));
		map.put("result", results);
		return map;
	}

	@Override
	public List<TaxesInviteWeek> findInfoRebatetotalByAgentMidDate(Map<String, Object> parameters) {
		//parameters.put("dbName", dbName + ".memberagents");
		return taxesInviteWeekMapper.findInfoRebatetotalByAgentMidDate(parameters);
	}

	@Override
	public List<Map<String, Object>> findMidPaytotalRebatetotalIsawardInfoAgentRealname(Map<String, Object> parameters) {
		if (parameters.get("date") == null || "".equals(parameters.get("date"))) {
			parameters.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			//return new ArrayList<Map<String,Object>>();
		}
		List<TaxesInviteWeek> taxesInviteWeekList = findInfoRebatetotalByAgentMidDate(parameters);
		Double weekPayTotal = 0.0;
		Float weekSettleTotal = 0.0F;
		for (TaxesInviteWeek taxesInviteWeek : taxesInviteWeekList) {
			String stringInfo = taxesInviteWeek.getInfo();
			if (!StringUtil.isBlank(stringInfo)) {
				JSONArray jsonArray = JSONArray.parseArray(stringInfo);
				Object [] arr1 = jsonArray.toArray();
				if ("null".equals(arr1[0] + "")) {
					arr1[0] = 0;
				}
				JSONArray jsonArray2 = JSONArray.parseArray(arr1[0] + "");
				Object [] arr2 = jsonArray2.toArray();
				if ("null".equals(arr2[0] + "")) {
					arr2[0] = 0;
				}
				weekPayTotal += Double.parseDouble(arr2[0] + "");
			}
			BigDecimal bigDecimal = taxesInviteWeek.getRebatetotal();
			weekSettleTotal += bigDecimal.floatValue();
		}
		//parameters.put("dbName", dbName + ".memberagents");
		List<Map<String, Object>> mapList = taxesInviteWeekMapper
				.findMidPaytotalRebatetotalIsawardInfoAgentRealname(parameters);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : mapList) {
			map.put("weekPayTotal", weekPayTotal);
			map.put("weekSettleTotal", weekSettleTotal);
			list.add(map);
		}
		return list;
	}

	@Override
	public Map<String, Object> getPayDetail(Map<String, Object> parameters) {
		Double rebateTotal = 0.0;
		Map<String,Object> memberAgents = memberAgentService.getAgentAndBusizInfoByMid(parameters);
		if (memberAgents == null) {
			return new HashMap<String, Object>();
			//TODO代理商不存在
		}
		Map<String, Object> map = new HashMap<String, Object>();
		TaxesInviteWeek results = taxesInviteWeekMapper.getAgentSettelDetailByAgentIdAndDate(parameters);
		String info = "";
		if (results != null) {
			info = results.getInfo();
			if (results.getPaytotal() == null) {
				map.put("paytotal", results.getPaytotal());
			} else {
				map.put("paytotal", "0.0");
			}
		} else {
			//数据不存在
			info = "[['0.0','0.0','0.0'],['0.0','0.0','0.0'],['0.0','0.0','0.0']]";
			map.put("paytotal", "0.0");
		}
		map = setStringToMap(info);
		Object zo = map.get("teamInfo00");
		Object one = map.get("teamInfo01");
		Object onezo = map.get("teamInfo10");
		Object tow = map.get("teamInfo11");
		Object towone = map.get("teamInfo20");
		Object thr = map.get("teamInfo21");

		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("info00", zo);
		infoMap.put("info01", one);
		infoMap.put("info10", onezo);
		infoMap.put("info11", tow);
		infoMap.put("info20", towone);
		infoMap.put("info21", thr);

		rebateTotal = Double.valueOf(one.toString()) + Double.valueOf(tow.toString()) + Double.valueOf(thr.toString());
		//$rebateTotal = number_format($rebateTotal, '2', '.', '');//取两位小数

		Double firTeamScale = Double.valueOf(one.toString())/Double.valueOf(zo.toString()) * 100 ;
		if(firTeamScale != 100.0){
			Double[][] settles = new Double[3][3];
			settles[0][0] = 0.0; //low
			settles[0][1] = 3500.0;//height
			settles[0][2] = 40.0;

			settles[1][0] = 3500.0;
			settles[1][1] = 7000.0;
			settles[1][2] = 45.0;

			settles[2][0] = 7000.0;
			settles[2][1] = 10000.0;//最后一维不比较
			settles[2][2] = 50.0;

			firTeamScale = getFirstTeamSettleScale(settles, Double.valueOf(zo.toString()));
		}
		Double secTeam = 8.0;
		Double thdTeam = 5.0;
		/*'team_2'    =>  8,
		'team_3'    =>  5*/
		map.put("firTeam", firTeamScale);
		map.put("secTeam", secTeam);
		map.put("thdTeam", thdTeam);
		map.put("rebateTotal", rebateTotal);
		map.put("memberAgents", memberAgents);
		map.put("detailInfo", results);
		map.put("infoMap", infoMap);
		return map;
	}

	@Override
	public List<Map<String, Object>> getWeekPayCheckInfoByDate(Map<String, Object> parameters) {
		return taxesInviteWeekMapper.getWeekPayCheckInfoByDate(parameters);
	}

	@Override
	public int updateWeekPayCheckInfoByDate(Map<String, Object> parameters) {
		return taxesInviteWeekMapper.updateWeekPayCheckInfoByDate(parameters);
	}

	@Override
	public int updateWeekPayCheckInfoByMidDate(Map<String, Object> parameters) {
		return taxesInviteWeekMapper.updateWeekPayCheckInfoByMidDate(parameters);
	}

	@Override
	public int getSumRebateTotalByDate(String date) {
		return taxesInviteWeekMapper.getSumRebateTotalByDate(date);
	}

	@Override
	public List<Map<String, Object>> getHistoryAgentsRebateList(Map<String, Object> parameters) {
		return taxesInviteWeekMapper.getHistoryAgentsRebateList(parameters);
	}

	@Override
	public List<Map<String, Object>> getWeekPayHistoryDetailInfoByDate(Map<String, Object> parameters) {
		return taxesInviteWeekMapper.getWeekPayHistoryDetailInfoByDate(parameters);
	}


	private Double getFirstTeamSettleScale(Double[][] settleConfig, Double zo) {
		if (zo > settleConfig[0][0] && zo < settleConfig[0][1]) {//
			return settleConfig[0][2];
		}
		if (zo > settleConfig[1][0] && zo < settleConfig[1][1]) {//
			return settleConfig[1][2];
		}
		/*if (settleConfig[0][0] > settleConfig[0][1]) {
			return settleConfig[0][2];
		}*/
		return settleConfig[2][2];
	}


	public static Map<String,Object> setStringToMap(String info){
		  Map<String,Object> map=new HashMap<String ,Object>();
		  DecimalFormat df = new DecimalFormat("######0.00");//double 用于保留两位小数金额
		  JSONArray js=JSONArray.parseArray(info);
		  for (int i = 0; i < js.size(); i++) {
			  JSONArray jsa=js.getJSONArray(i);
			  for (int j = 0; j < jsa.size(); j++) {
				  map.put("teamInfo"+i+j, df.format(Double.parseDouble(jsa.getString(j).toString())));
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
