/*
 * 文件名：TaxesInviteController.java	 
 * 时     间：下午5:49:23
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.constant.Constant;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInvite;
import com.qs.log.game.service.ITaxesInviteService;
import com.qs.pub.sys.model.BusinessGroup;
import com.qs.pub.sys.model.Group;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.BusinessService;
import com.qs.pub.sys.service.GroupService;
import com.qs.pub.util.DateHandle;

/** 
 * @ClassName: TaxesInviteController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年6月30日 下午5:49:23 
 */
@Controller
@RequestMapping("/taxesInvite/")
public class TaxesInviteController extends BaseController
{	
	@Resource
	private ITaxesInviteService taxesInviteService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate; 
	@Resource
	private GroupService groupService;
	@Resource
	private BusinessService businessService;
	Logger log = Logger.getLogger(TaxesInviteController.class); 
	
	@RequestMapping("toTaxesInviteUi.html")
	private String toTaxesInviteUi(){
		
		return "/WEB-INF/view/loginfo/taxesInvite_list";
	}
	
	@RequestMapping("taxesInvite.html")
	@ResponseBody
	private Object taxesInvite(String gridPager,HttpServletResponse response){
		try
		{
			ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
	    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			parameters.put("dbtable", dataSourceName);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<TaxesInvite> list = taxesInviteService
							.queryListByPage(parameters);
					ExportUtils.exportAll(response, pager, list);
					return null;
				} else
				{
					// 3.2、导出当前页数据
					ExportUtils.export(response, pager);
					return null;
				}
			} else
			{
				// 设置分页，page里面包含了分页信息
				Page<Object> page = PageHelper.startPage(pager.getNowPage(),
						pager.getPageSize());
				
				List<TaxesInvite> list = taxesInviteService.queryListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping("toArppuUi.html")
	private String toArppuUi(){
		
		return "/WEB-INF/view/loginfo/arppu_list";
	}
	
	
	@RequestMapping("arppuList.html")
	@ResponseBody
	private Object arppuList(String gridPager,HttpServletResponse response){
		try
		{
			ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
	    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			parameters.put("dbtable", dataSourceName);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<TaxesInvite> list = taxesInviteService
							.queryListByPageOfArppu(parameters);
					ExportUtils.exportAll(response, pager, list);
					return null;
				} else
				{
					// 3.2、导出当前页数据
					ExportUtils.export(response, pager);
					return null;
				}
			} else
			{
				// 设置分页，page里面包含了分页信息
				Page<Object> page = PageHelper.startPage(pager.getNowPage(),
						pager.getPageSize());
				
				List<TaxesInvite> list = taxesInviteService.queryListByPageOfArppu(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("taxesInviteCountOfCompanyUi.html")
	public String taxesInviteCountOfCompanyUi(){
		
		return "/WEB-INF/view/loginfo/company_paytotal_count";
	}
	@RequestMapping("taxesInviteCountOfCompanyList.html")
	@ResponseBody
	public Object taxesInviteCountOfCompanyList(String stime,String etime){
		try
		{	
			
			ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			
			JSONArray lengend=new JSONArray();
			//获取年月数值
			JSONArray dateHandle = DateHandle.dateHandle(stime,etime);
			//xAxis项
			JSONArray xAxis= dateHandle;
			JSONArray datas=new JSONArray();
			
			
			Map<Integer, Object> groupBusinessId = new HashMap<Integer,Object>();
			Map<String, Object> parameters = new HashMap<String,Object>();
			// 映射Pager对象
			// 判断是否包含自定义参数
			parameters.put("gameType", gameType);
			parameters.put("dbtable", dataSourceName);
			parameters.put("stime", stime);
			parameters.put("etime", etime);
			//根据gameType查询分公司
			List<Group> groupList = groupService.queryListGroupPrivilege(parameters);
			List<Integer> businessIds = new ArrayList<Integer>();
			//将查出来的分公司信息放入map集合中
			if(groupList != null && !groupList.isEmpty()){
				for(Group group:groupList){
					Integer groupId = group.getId();
					businessIds = groupService.queryBusinessIdListByGroupId(groupId);
					groupBusinessId.put(groupId, businessIds);
				}
			}else{
				JSONObject js=new JSONObject();
				js.put("lengend", JSON.toJSONString(lengend));
				js.put("xAxis", JSON.toJSONString(xAxis));
				js.put("data", JSON.toJSONString(datas));
				return js;
			}
			
			DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			/**
			 * 遍历分公司集合
			 */
			Set<Integer> set = groupBusinessId.keySet();
			Iterator<Integer> it = set.iterator();
			while(it.hasNext()){
				Integer key = it.next();
				List<Integer> businessIdList = (List<Integer>) groupBusinessId.get(key);
				parameters.put("businessIdList",businessIdList != null && !businessIdList.isEmpty()?businessIdList:null);
				List<TaxesInvite> taxList = taxesInviteService.queryListCountByBusinessId(parameters);
				
				//lengend项  分公司
				for(Group group:groupList){
					Integer groupId = group.getId();
					if(key == groupId){
						lengend.add(group.getUserGroupName());
					}
				}
				//如果查出来的数据不为空
				if(taxList != null && !taxList.isEmpty()){
					List<BigDecimal> da  = new ArrayList<BigDecimal>();
					for(int i = 0;i<dateHandle.size();i++){
						boolean flag = true;
						int index = taxList.size();
						for(int j=0;j<index && flag;j++){
							if(dateHandle.get(i).equals(taxList.get(j).getDateStr())){
								da.add(taxList.get(j).getPaytotal());
								flag = false;
							}
						}
						if(flag){
							da.add(new BigDecimal(0));
						}
					}
					datas.add(da);
				}else{
					List<BigDecimal> da  = new ArrayList<BigDecimal>();
					for(int i = 0;i<dateHandle.size();i++){
						da.add(new BigDecimal(0));
					}
					datas.add(da);
				}
			}
			JSONObject js=new JSONObject();
			js.put("lengend", JSON.toJSONString(lengend));
			js.put("xAxis", JSON.toJSONString(xAxis));
			js.put("data", JSON.toJSONString(datas));
			return js;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("taxesInviteCountOfBusinessUi.html")
	public String taxesInviteCountOfBusinessUi(){
		
		return "/WEB-INF/view/loginfo/business_paytotal_count";
	}
	
	@RequestMapping("taxesInviteCountOfBusinessList.html")
	@ResponseBody
	public Object taxesInviteCountOfBusinessList(String stime,String etime,String groupId){
		try
		{	
			ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			
			JSONArray lengend=new JSONArray();
			//获取年月数值
			JSONArray dateHandle = DateHandle.dateHandle(stime,etime);
			//xAxis项
			JSONArray xAxis= dateHandle;
			JSONArray datas=new JSONArray();
			
			
			Map<String, Object> parameters = new HashMap<String,Object>();
			// 映射Pager对象
			// 判断是否包含自定义参数
			parameters.put("groupId", groupId);
			parameters.put("gameType", gameType);
			parameters.put("dbtable", dataSourceName);
			parameters.put("stime", stime);
			parameters.put("etime", etime);
			//根据gameType，groupId查询商务
			List<BusinessGroup> businessList = businessService.queryBusinessListByGroupId(parameters);
			//将查出来的分公司信息放入map集合中
			if(businessList == null || businessList.isEmpty()){
				JSONObject js=new JSONObject();
				js.put("lengend", JSON.toJSONString(lengend));
				js.put("xAxis", JSON.toJSONString(xAxis));
				js.put("data", JSON.toJSONString(datas));
				return js;
			}
			
			DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			/**
			 * 遍历商务集合
			 */
			for(BusinessGroup business : businessList){
				parameters.put("businessId",business.getBusinessId());
				List<TaxesInvite> taxList = taxesInviteService.queryBusinessCountOfPaytotal(parameters);
				//lengend项  分公司
				lengend.add(business.getBusinessName());
				//如果查出来的数据不为空
				if(taxList != null && !taxList.isEmpty()){
					List<BigDecimal> da  = new ArrayList<BigDecimal>();
					for(int i = 0;i<dateHandle.size();i++){
						boolean flag = true;
						int index = taxList.size();
						for(int j=0;j<index && flag;j++){
							if(dateHandle.get(i).equals(taxList.get(j).getDateStr())){
								da.add(taxList.get(j).getPaytotal());
								flag = false;
							}
						}
						if(flag){
							da.add(new BigDecimal(0));
						}
					}
					datas.add(da);
				}else{
					List<BigDecimal> da  = new ArrayList<BigDecimal>();
					for(int i = 0;i<dateHandle.size();i++){
						da.add(new BigDecimal(0));
					}
					datas.add(da);
				}
			}
			JSONObject js=new JSONObject();
			js.put("lengend", JSON.toJSONString(lengend));
			js.put("xAxis", JSON.toJSONString(xAxis));
			js.put("data", JSON.toJSONString(datas));
			return js;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
