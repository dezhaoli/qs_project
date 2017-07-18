/*
 * 文件名：TaxesInviteWeekDownController.java	 
 * 时     间：上午9:36:53
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.common.exception.SystemException;
import com.qs.common.util.DateUtil;
import com.qs.common.util.DateUtils;
import com.qs.common.util.PageUtil;
import com.qs.constant.Constant;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInviteWeekDown;
import com.qs.log.game.service.ITaxesInviteWeekService;
import com.qs.log.game.service.IWeekDownService;
import com.qs.pub.datacenter.model.CreateRoom;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.BusinessService;

/** 
 * @ClassName: TaxesInviteWeekDownController 
 * @描述: 代理商业绩下滑统计控制器 
 * @author wangzhen
 * @date 2017年5月17日 上午9:36:53 
 */
@Controller
@RequestMapping(value="/weekdown/")
public class AgentStarStatController extends BaseController
{
	@Resource
	private IWeekDownService weekDownService;
	
	@Autowired
	private ITaxesInviteWeekService taxesInviteWeekService;
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	@Resource
	private BusinessService businessService; 
	
	/**
	 * 
	 * @标题: toMemberagentsListUi 
	 * @描述: 跳转到商务数据列表页面 
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toTaxesInviteWeekDownListUi.html")
	public String toTaxesInviteWeekDownListUi(String gameType,Model model){
		model.addAttribute("gameType",gameType);
		return "/WEB-INF/view/loginfo/taxes_week_down_list";
	}
	
	/**
	 * 
	 * @标题: queryListByPage 
	 * @描述:  商务数据查询接口
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param gridPager
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("TaxesInviteWeekDownList.html")
	@ResponseBody
	public Object queryListByPage(String gameType,String gridPager){
	try {
		ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
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
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
       // parameters.put("dbTable", gameType);
		List<TaxesInviteWeekDown> list = weekDownService.queryListByPage(parameters);
		return getReturnPage(pager, page, list);
		}catch(Exception e){
			throw new SystemException(e);
		}
		
	}
	@RequestMapping("TaxesInviteWeekDownListCount.html")
	@ResponseBody
	public Object TaxesInviteWeekDownListCount(String mid,String date,String gameType){
		ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
		UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
	    String logDataSourceType=dataSourceName+"LogDataSource";
		String mainDataSourceType = dataSourceName + "AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("mid", mid);
        parameters.put("date", date);
        
		List<TaxesInviteWeekDown> list = weekDownService.queryListAll(parameters);
		
		String json  = JSON.toJSONString(list);
		return json;
	}
	
	
	/**
	 * 代理商用户星级统计
	 * @return
	 * @author:zyy
	 * @time:2017年5月25日
	 */
	@RequestMapping("userGradeUi.html")
	public String userGradeUi(String gameType,Model model,HttpServletRequest request){
		
		Map<String, List<String>> date;
		try {
			date = DateUtils.getAgentInfoDateTime();
			String json = JSON.toJSONString(date);
			List<String> keys = new ArrayList<String>();
			Set<String> keySet = date.keySet();
			Iterator<String> ki = keySet.iterator();
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			}
			while (ki.hasNext()) {
				String key = ki.next();
				keys.add(key.substring(1));
			}
			model.addAttribute("page", page);
			model.addAttribute("years", keys);
			model.addAttribute("jsonDate", json);
			model.addAttribute("gameType", gameType);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "WEB-INF/view/agent/agent_userGradeUi_list";
	}
	
	@ResponseBody
	@RequestMapping("userGradeList.html")
	public Map<String ,Object> userGradeList(String gridPager,HttpServletResponse response) {
			try
			{
				UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
				ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
				String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
				String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
				Map<String, Object>  map = new HashMap<String,Object>();
				
				
				Map<String, Object> parameters = null;
				// 映射Pager对象
				Pager pager = JSON.parseObject(gridPager, Pager.class);
				// 判断是否包含自定义参数
				parameters = pager.getParameters();
				
				
				//如果是领导人可查看的商务id
				map.put("gameType", gameType);
				map.put("uId", userEntity.getId());
				List businessIdList = businessService.findByuId(map);
				//如果式商务，只能看自己
				map.put("accountName", userEntity.getAccountName());
				List businessIdList2 = businessService.selectBusiness(map);
				
				
				//条件查询（组id）
				String groupId = parameters.get("groupId") != null?parameters.get("groupId").toString():"";
				//通过组id查询商务id
				List businessIdListByGroup = businessService.findBusinessByGroupId(StringUtils.isEmpty(groupId)?-1:Integer.valueOf(groupId));
				
				Integer leaderTotals = businessService.ifLeader(map);
				
				//判断是否是管理员
				if(userEntity.getIfBusiness() != null && userEntity.getIfBusiness()){
					//判断是否是公司负责人
					if(leaderTotals > 0){
						parameters.put("businessIdList",businessIdList != null && businessIdList.size()>0?businessIdList:null);
						parameters.put(Constant.DataPrivilege.IF_LEADER,1);
					}else{
						    //判断是否式普通商务
							parameters.put(Constant.DataPrivilege.IF_BUSINESS,1);
							parameters.put("businessIdList2",businessIdList2 != null && businessIdList2.size()>0?businessIdList2:null);
					}
				}
				
				String logDataSourceType=dataSourceName+"LogDataSource";
				String mainDataSourceType = dataSourceName + "AgentDataSource";
				DataSourceSwitch.setLogDataSourceType(logDataSourceType);
				DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
				
				if (StringUtils.isEmpty(parameters.get("eDate").toString())){
					parameters.put("eDate", DateUtil.getDatalastWeek());
				}
				
				parameters.put("dbTable", dataSourceName);
				parameters.put("gameType", gameType);
				parameters.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);
				
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Map<String, Object>> list = taxesInviteWeekService
							.getWeekCountGradeList(parameters);
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
				List<Map<String, Object>> list = taxesInviteWeekService
						.getWeekCountGradeList(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	       
	}
	@ResponseBody
	@RequestMapping("queryStarCountTotals.html")
	public Object queryStarCountTotals(String eDate,String groupId,String businessId) {
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			Map<String, Object>  map = new HashMap<String,Object>();
			
			
			Map<String, Object> parameters = new HashMap<String,Object>();
			
			
			//如果是领导人可查看的商务id
			map.put("gameType", gameType);
			map.put("uId", userEntity.getId());
			List businessIdList = businessService.findByuId(map);
			//如果式商务，只能看自己
			map.put("accountName", userEntity.getAccountName());
			List businessIdList2 = businessService.selectBusiness(map);
			
			
			//通过组id查询商务id
			List businessIdListByGroup = businessService.findBusinessByGroupId(StringUtils.isEmpty(groupId)?-1:Integer.valueOf(groupId));
			
			Integer leaderTotals = businessService.ifLeader(map);
			
			//判断是否是管理员
			if(userEntity.getIfBusiness() != null && userEntity.getIfBusiness()){
				//判断是否是公司负责人
				if(leaderTotals > 0){
					parameters.put("businessIdList",businessIdList != null && businessIdList.size()>0?businessIdList:null);
					parameters.put(Constant.DataPrivilege.IF_LEADER,1);
				}else{
					//判断是否式普通商务
					parameters.put(Constant.DataPrivilege.IF_BUSINESS,1);
					parameters.put("businessIdList2",businessIdList2 != null && businessIdList2.size()>0?businessIdList2:null);
				}
			}
			
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
			
			if (StringUtils.isEmpty(eDate)){
				parameters.put("eDate", DateUtil.getDatalastWeek());
			}else{
				parameters.put("eDate", eDate);
			}
			parameters.put("dbTable", dataSourceName);
			parameters.put("groupId", groupId);
			parameters.put("businessId", businessId);
			parameters.put("gameType", gameType);
			parameters.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);
			Double total = taxesInviteWeekService.queryStarCountTotals(parameters);
			return total == null?0:total;
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	@ResponseBody
	@RequestMapping("userGradeChar.html")
	public Map<String,Object> userGradeChar(String eDate,String belongid,String grade,String groupId,String businessId) {
		UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
		String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
		String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
		Map<String, Object>  map = new HashMap<String,Object>();

		map.put("gameType", gameType);
		map.put("uId", userEntity.getId());
		List businessIdList = businessService.findByuId(map);
		List businessIdListByGroup = businessService.findBusinessByGroupId(StringUtils.isEmpty(groupId)?-1:Integer.valueOf(groupId));
		//List businessIdAllList = taxesInviteWeekService.findByAll();
		
		
		map.put("accountName", userEntity.getAccountName());
        List businessIdList2 = businessService.selectBusiness(map);
        Integer leaderTotals = businessService.ifLeader(map);
		
		
		Map<String,Object> result=new HashMap<>();
		Map<String,Object> parma=new HashMap<>();
		Map<String,Object> entity=new HashMap<>();
	    String logDataSourceType=dataSourceName+"LogDataSource";
		String mainDataSourceType = dataSourceName + "AgentDataSource";
    	DataSourceSwitch.setLogDataSourceType(logDataSourceType);
		DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
		if(StringUtils.isEmpty(eDate)) {
			parma.put("eDate", DateUtil.getDatalastWeek());
		 }else{
				parma.put("eDate",eDate);
		}
		 parma.put("dbTable", dataSourceName);
		 parma.put("gameType", gameType);
		 parma.put("belongid", belongid);
		 parma.put("grade", grade);
		 parma.put("groupId", groupId);
		 parma.put("businessId",businessId);
		 parma.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);
		 
		 
		 	//判断是否是管理员
			if(userEntity.getIfBusiness() != null && userEntity.getIfBusiness()){
				//判断是否是公司负责人
				if(leaderTotals > 0){
					parma.put("businessIdList",businessIdList != null && businessIdList.size()>0?businessIdList:null);
					parma.put(Constant.DataPrivilege.IF_LEADER,1);
				}else{
					    //判断是否式普通商务
					parma.put(Constant.DataPrivilege.IF_BUSINESS,1);
					parma.put("businessId",businessIdList2);
				}
			}
		 
		List<Map<String,Object>> list= taxesInviteWeekService.getWeekCountGrade(parma);
		int[] arr={0,0,0,0,0};
		if (list !=null ) {
			for (int i = 0; i < list.size(); i++) {
				entity=list.get(i);
				arr[i]=Integer.valueOf(entity.get("counts").toString());
			}
		}
		result.put(CommonContants.SUCCESS,true);
		result.put("data", arr);
		return result;
	}
	
	/**
	 * 
	 * @标题: userGradSecondeUi 
	 * @描述:  代理商用户星级统计-明细
	 *
	 * @参数信息
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("userGradSecondeUi.html")
	public String userGradSecondeUi(Model model,String id,String StrDate,String grade,HttpServletRequest request){
		
		Map<String, List<String>> date;
		try {
			date = DateUtils.getAgentInfoDateTime();
			String json = JSON.toJSONString(date);
			List<String> keys = new ArrayList<String>();
			Set<String> keySet = date.keySet();
			Iterator<String> ki = keySet.iterator();
			
			while (ki.hasNext()) {
				String key = ki.next();
				keys.add(key.substring(1));
			}
			PageUtil page = new PageUtil();
			/*if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}*/
			model.addAttribute("page", page);
			model.addAttribute("years", keys);
			model.addAttribute("jsonDate", json);
			model.addAttribute("id", id);
			model.addAttribute("date", StrDate);
			model.addAttribute("grade", grade);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "/WEB-INF/view/agent/agent_userGrade_details_list";
	}
	
	@ResponseBody
	@RequestMapping("userGradeSecondList.html")
	public Map<String ,Object> userGradeSecondList(String gridPager,HttpServletResponse response) {
			
			try
			{
				Map<String, Object> parameters = null;
				// 映射Pager对象
				Pager pager = JSON.parseObject(gridPager, Pager.class);
				// 判断是否包含自定义参数
				parameters = pager.getParameters();
				ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
				UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
				String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
				String logDataSourceType=dataSourceName+"LogDataSource";
				String mainDataSourceType = dataSourceName + "AgentDataSource";
				DataSourceSwitch.setLogDataSourceType(logDataSourceType);
				DataSourceSwitch.setMainDataSourceType(mainDataSourceType);
				if (StringUtils.isEmpty(parameters.get("eDate").toString())){
					parameters.put("eDate", DateUtil.getDatalastWeek());
				}
				
				String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
				parameters.put("dbTable", dataSourceName);
				parameters.put("gameType", gameType);
				
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Map<String, Object>> list = taxesInviteWeekService
							.getWeekCountGradeSecondList(parameters);
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
				// parameters.put("dbTable", gameType);
				
				List<Map<String, Object>> list = taxesInviteWeekService
						.getWeekCountGradeSecondList(parameters);
				
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
			
	       
	}


	/**
	 * 代理商付费排行榜
	 * @param gameType
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("memberagentRankingListUi.html")
	public String memberagentRankingListUi(String gameType,Model model,HttpServletRequest request){

		Map<String, List<String>> date;
		try {
			date = DateUtils.getAgentInfoDateTime();
			String json = JSON.toJSONString(date);
			List<String> keys = new ArrayList();
			Set<String> keySet = date.keySet();
			Iterator<String> ki = keySet.iterator();
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			}
			while (ki.hasNext()) {
				String key = ki.next();
				keys.add(key.substring(1));
			}
			model.addAttribute("page", page);
			model.addAttribute("years", keys);
			model.addAttribute("jsonDate", json);
			model.addAttribute("gameType", gameType);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "WEB-INF/view/agent/memberagent_ranking_list";
	}

	@ResponseBody
	@RequestMapping("memberagentRankingList.html")
	public Map<String ,Object> memberagentRankingList(String gridPager,HttpServletResponse response) {
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			Map<String, Object>  map = new HashMap();


			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();


			//如果是领导人可查看的商务id
			map.put("gameType", gameType);
			map.put("uId", userEntity.getId());
			List businessIdList = businessService.findByuId(map);
			//如果式商务，只能看自己
			map.put("accountName", userEntity.getAccountName());
			List businessIdList2 = businessService.selectBusiness(map);


			//条件查询（组id）
			String groupId = parameters.get("groupId") != null?parameters.get("groupId").toString():"";
			//通过组id查询商务id
			List businessIdListByGroup = businessService.findBusinessByGroupId(StringUtils.isEmpty(groupId)?-1:Integer.valueOf(groupId));

			Integer leaderTotals = businessService.ifLeader(map);

			//判断是否是管理员
			if(userEntity.getIfBusiness() != null && userEntity.getIfBusiness()){
				//判断是否是公司负责人
				if(leaderTotals > 0){
					parameters.put("businessIdList",businessIdList != null && businessIdList.size()>0?businessIdList:null);
					parameters.put(Constant.DataPrivilege.IF_LEADER,1);
				}else{
					//判断是否式普通商务
					parameters.put(Constant.DataPrivilege.IF_BUSINESS,1);
					parameters.put("businessIdList2",businessIdList2 != null && businessIdList2.size()>0?businessIdList2:null);
				}
			}

			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);

			if (StringUtils.isEmpty(parameters.get("eDate").toString())){
				parameters.put("eDate", DateUtil.getDatalastWeek());
			}

			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			parameters.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);

			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Map<String, Object>> list = taxesInviteWeekService
							.getMemberagentRankingList(parameters);
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
				List<Map<String, Object>> list = taxesInviteWeekService
						.getMemberagentRankingList(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}
}
