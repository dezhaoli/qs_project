/*
 * 文件名：PlayingController.java	 
 * 时     间：下午7:56:09
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.pub.datacenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.constant.Constant;
import com.qs.pub.datacenter.model.Playing;
import com.qs.pub.datacenter.service.IPlayingService;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.BusinessService;

/** 
 * @ClassName: PlayingController 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月23日 下午7:56:09 
 */
@Controller
@RequestMapping("/playing/")
public class PlayingController extends BaseController
{
	@Resource
	private IPlayingService playService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Resource
	private BusinessService businessService;
	/**
	 * 
	 * @标题: toPlayListUi 
	 * @描述:  跳转到在线在玩主页面
	 *
	 * @参数信息
	 *    @param pag
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toPlayListUi.html")
	public String toPlayListUi(){
		
		return "WEB-INF/view/web/user/playing_list";
	}
	/**
	 * 
	 * @标题: playList 
	 * @描述:  在线在玩数据查询接口
	 *
	 * @参数信息
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("playList.html")
	@ResponseBody
	public Object playList(String gridPager,HttpServletResponse response){
		try
		{
			///////////////////////公共代码 start/////////////////
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			///////////////////////公共代码 end/////////////////
			
			Map<String, Object>  map = new HashMap<String,Object>();
			//如果是领导人可查看的商务id
			map.put("gameType", gameType);
			map.put("uId", userEntity.getId());
			List businessIdList = businessService.findByuId(map);
			//如果式商务，只能看自己
			map.put("accountName", userEntity.getAccountName());
			List businessIdList2 = businessService.selectBusiness(map);
			
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			
			
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
			
			
			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			parameters.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);
			parameters.put("gameCode", gameCode);
			
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Playing> list = playService.queryPageList(parameters);
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
				List<Playing> list = playService.queryPageList(parameters);
				
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("queryCountTotal.html")
	@ResponseBody
	public Object queryCountTotal(String stime,String etime,String playName,String groupId,String businessId){
		try
		{
			///////////////////////公共代码 start/////////////////
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			///////////////////////公共代码 end/////////////////
			
			Map<String, Object>  map = new HashMap<String,Object>();
			//如果是领导人可查看的商务id
			map.put("gameType", gameType);
			map.put("uId", userEntity.getId());
			List businessIdList = businessService.findByuId(map);
			//如果式商务，只能看自己
			map.put("accountName", userEntity.getAccountName());
			List businessIdList2 = businessService.selectBusiness(map);
			
			Map<String, Object> parameters = new HashMap<String,Object>();
			parameters.put("stime", stime);
			parameters.put("etime", etime);
			parameters.put("playName", playName);
			parameters.put("groupId", groupId);
			parameters.put("businessId", businessId);
			
			
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
			
			
			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			parameters.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);
			parameters.put("gameCode", gameCode);
			
			
			Long totals = playService.queryCountTotal(parameters);
			return totals == null ?0:totals;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	@RequestMapping("queryCountTotalActive.html")
	@ResponseBody
	public Object queryCountTotalActive(String stime,String etime){
		try
		{
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			
			Map<String, Object> parameters = new HashMap<String,Object>();
			// 判断是否包含自定义参数
			parameters.put("gameCode", gameCode);
			parameters.put("stime", stime);
			parameters.put("etime", etime);
			Long totals = playService.queryCountTotalActive(parameters);
			return totals == null ?0:totals;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 
	 * @标题: playCount 
	 * @描述:  
	 *
	 * @参数信息
	 *    @param stime
	 *    @param etime
	 *    @param appName
	 *    @param playName
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("playCount.html")
	@ResponseBody
	public Object playCount(String stime,String etime,String playName,String groupId,String businessId){
		///////////////////////公共代码 start/////////////////
		UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
		String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
		String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
		String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
		///////////////////////公共代码 end/////////////////
		
		Map<String, Object>  map = new HashMap<String,Object>();
		//如果是领导人可查看的商务id
		map.put("gameType", gameType);
		map.put("uId", userEntity.getId());
		List businessIdList = businessService.findByuId(map);
		//如果式商务，只能看自己
		map.put("accountName", userEntity.getAccountName());
		List businessIdList2 = businessService.selectBusiness(map);
		
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("stime", stime);
        parameters.put("etime", etime);
        parameters.put("playName", playName);
        parameters.put("groupId", groupId);
        parameters.put("businessId", businessId);
		
		
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
		
		
		parameters.put("dbTable", dataSourceName);
		parameters.put("gameType", gameType);
		parameters.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);
		parameters.put("gameCode", gameCode);
		
		
        
		List<Playing> list = playService.queryCount(parameters);
		List<String> list2 = new ArrayList<String>();
		List<Integer> list3 = new ArrayList<Integer>();
		for(Playing data : list){
			list2.add(data.getPlayName());
			list3.add(data.getTotals());
		}
		String json2  = JSON.toJSONString(list2);
		String json3  = JSON.toJSONString(list3);
		return json2+"@"+json3;
	}
	
	
	@RequestMapping("toActiveUi.html")
	public String toActiveUi(){
		
		return "WEB-INF/view/web/user/active_list";
	}
	@RequestMapping("activeList.html")
	@ResponseBody
	public Object activeList(String gridPager,HttpServletResponse response){
		try
		{
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			
			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			parameters.put("gameCode", gameCode);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Playing> list = playService.queryPageListActive(parameters);
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
				List<Playing> list = playService.queryPageListActive(parameters);
				
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
}
