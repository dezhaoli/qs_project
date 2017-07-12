/*
 * 文件名：TaxesInviteController.java	 
 * 时     间：下午5:49:23
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import com.qs.common.util.DateUtil;
import com.qs.constant.Constant;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInvite;
import com.qs.log.game.service.ITaxesInviteService;
import com.qs.pub.sys.model.Group;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.GroupService;

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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			
//			List<String> lengend = new ArrayList<String>();
//			List<String> xAxis = new ArrayList<String>();
//			List<Object> datas = new ArrayList<Object>();
			JSONArray lengend=new JSONArray();
			JSONArray xAxis=new JSONArray();
			JSONArray datas=new JSONArray();
			
			Map<Integer, Object> groupBusinessId = new HashMap<Integer,Object>();
			Map<String, Object> parameters = new HashMap<String,Object>();
			// 映射Pager对象
			// 判断是否包含自定义参数
			parameters.put("gameType", gameType);
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
				return null;
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
				
				if(taxList != null && !taxList.isEmpty()){
//					List<BigDecimal> da = null;
					//xAxis项  月份 （从数据库中获取）
					for(TaxesInvite bean : taxList){
						if(!xAxis.contains(bean.getDateStr())){
							xAxis.add(bean.getDateStr());
						}
//						da = new ArrayList<BigDecimal>();
//						da.add(bean.getPaytotal());
						datas.add(bean.getPaytotal());
					}
				}else{
						//xAxis项  月份 （如果从数据库中查询出0条数据）
						if (xAxis.size() > 0)
						{
							String dats = xAxis.getString(xAxis.size() - 1);
							if (!xAxis.contains(dats))
							{
								Integer val = Integer.valueOf(dats) + 1;
								if (val < 10)
								{
									xAxis.add("0" + val);
								} else
								{
									xAxis.add("" + val);
								}
							}
						} else
						{
							Integer month = sdf.parse(etime).getMonth()
									+ 1;
							if (month < 10)
							{
								xAxis.add("0" + month);
							} else
							{
								xAxis.add("" + month);
							}
						}
						
							/*List<BigDecimal> da =  new ArrayList<BigDecimal>();
							da.add(new BigDecimal(0));*/
							
							datas.add(new BigDecimal(0));
					}
			}
			/*String lengendStr = JSON.toJSONString(lengend);
			String xAxisStr = JSON.toJSONString(xAxis);
			String datasStr = JSON.toJSONString(datas);*/
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
