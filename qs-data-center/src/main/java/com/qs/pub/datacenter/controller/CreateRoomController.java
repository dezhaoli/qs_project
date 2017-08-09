/*
 * 文件名：CreateRoomController.java	 
 * 时     间：下午7:56:27
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Column;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.constant.Constant;
import com.qs.pub.datacenter.model.CreateRoom;
import com.qs.pub.datacenter.service.ICreateRoomService;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.BusinessService;

/** 
 * @ClassName: CreateRoomController 
 * @描述: 房间创建查询接口 
 * @author qs
 * @date 2017年5月23日 下午7:56:27 
 */
@Controller
@RequestMapping("/createRoom/")
public class CreateRoomController extends BaseController
{
	@Resource
	private ICreateRoomService createRoomService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Resource
	private BusinessService businessService; 
	/**
	 * 
	 * @标题: toCreateRoom 
	 * @描述:  跳转到创建房间统计页面
	 *
	 * @参数信息
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toCreateRoom.html")
	public String toCreateRoom(){
		
		return "WEB-INF/view/web/user/create_room_list";
	}
	/**
	 * 
	 * @标题: createRoom 
	 * @描述:  创建房间统计数据接口
	 *
	 * @参数信息
	 *    @param gridPager
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("createRoom.html")
	@ResponseBody
	public Object createRoom(String gridPager,HttpServletResponse response){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
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
				// 3.1、导出全部数据
				if (pager.getExportAllData())
				{
					
					if(gameType != null && gameType.equals("runfast")){
						parameters.putAll(map);
						List<CreateRoom> list = createRoomService
								.queryListOfExport(parameters);
						List<Column> columns = pager.getExportColumns();
						Column bean = new Column();
						bean.setId("businessName");
						bean.setTitle("商务名称");
						Column bean2 = new Column();
						bean2.setId("userGroupName");
						bean2.setTitle("区域");
						
						columns.add(bean);
						columns.add(bean2);
						
						pager.setExportColumns(columns);
						ExportUtils.exportAll(response, pager, list);
						
					}else{
						List<CreateRoom> list = createRoomService
								.queryPageList(parameters);
						ExportUtils.exportAll(response, pager, list);
					}
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
				List<CreateRoom> list = createRoomService
						.queryPageList(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("queryCreateRoomCountTotal.html")
	@ResponseBody
	public Object queryCreateRoomCountTotal(String stime,String etime,String appName,String playName,String businessId,String groupId){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			Map<String, Object>  map = new HashMap<String,Object>();
			
			
			//如果是领导人可查看的商务id
			map.put("gameType", gameType);
			map.put("uId", userEntity.getId());
			List businessIdList = businessService.findByuId(map);
			//如果式商务，只能看自己
			map.put("accountName", userEntity.getAccountName());
			List businessIdList2 = businessService.selectBusiness(map);
			
			Map<String, Object> parameters = new HashMap<String,Object>();
			// 判断是否包含自定义参数
			
			
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
			
			parameters.put("stime", stime);
			parameters.put("etime", etime);
			parameters.put("businessId", businessId);
			parameters.put("groupId", groupId);
			parameters.put("playName", playName);
			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			parameters.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);
			parameters.put("gameCode", gameCode);
			Long total = createRoomService.queryCreateRoomCountTotal(parameters);
			return total == null?0:total;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("createRoomCount.html")
	@ResponseBody
	public Object createRoomCount(String stime,String etime,String appName,String playName){
		ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
		UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("stime", stime);
        parameters.put("etime", etime);
        parameters.put("appName", appName);
        parameters.put("playName", playName);
        parameters.put("gameCode", gameCode);
		List<CreateRoom> list = createRoomService.queryCount(parameters);
		List<String> list2 = new ArrayList<String>();
		List<Integer> list3 = new ArrayList<Integer>();
		for(CreateRoom data : list){
			list2.add(data.getPlayName());
			list3.add(data.getTotals());
		}
		String json2  = JSON.toJSONString(list2);
		String json3  = JSON.toJSONString(list3);
		return json2+"@"+json3;
	}
	
	@RequestMapping("toCreateRoomSecondDetailsUi.html")
	public String toCreateRoomSecondDetailsUi(Model model,String appId,String playId,String stime,String etime,String groupId,String businessId){
		model.addAttribute("appId", appId);
		model.addAttribute("playId", playId);
		model.addAttribute("groupId", groupId);
		model.addAttribute("businessId", businessId);
		model.addAttribute("stime", stime);
		model.addAttribute("etime", etime);
		
		return "WEB-INF/view/web/user/create_room_second_list";
	}
	
	@RequestMapping("queryCreateRoomSecondDetails.html")
	@ResponseBody
	public Object queryCreateRoomSecondDetails(String gridPager,HttpServletResponse response){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
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
					List<CreateRoom> list = createRoomService
							.queryListSecondDetails(parameters);
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
				List<CreateRoom> list = createRoomService
						.queryListSecondDetails(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}


	@RequestMapping("toCreateRoomThreeDetailsUi.html")
	public String toCreateRoomThreeDetailsUi(Model model,String appId,String playId,String stime,String etime,String businessId){
		model.addAttribute("appId", appId);
		model.addAttribute("playId", playId);
		model.addAttribute("businessId", businessId);
		model.addAttribute("stime", stime);
		model.addAttribute("etime", etime);

		return "WEB-INF/view/web/user/create_room_three_list";
	}

	@RequestMapping("queryCreateRoomThreeDetails.html")
	@ResponseBody
	public Object queryCreateRoomThreeDetails(String gridPager,HttpServletResponse response){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);

			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();

			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			parameters.put("gameCode", gameCode);
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<CreateRoom> list = createRoomService
							.queryListThreeDetails(parameters);
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
				List<CreateRoom> list = createRoomService
						.queryListThreeDetails(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}


	@RequestMapping("toCreateRoomFourDetailsUi.html")
	public String toCreateRoomFourDetailsUi(Model model,String appId,String playId,String stime,String etime,String businessId,String mid){
		model.addAttribute("appId", appId);
		model.addAttribute("playId", playId);
		model.addAttribute("businessId", businessId);
		model.addAttribute("mid", mid);
		model.addAttribute("stime", stime);
		model.addAttribute("etime", etime);

		return "WEB-INF/view/web/user/create_room_four_list";
	}

	@RequestMapping("queryCreateRoomFourDetails.html")
	@ResponseBody
	public Object queryCreateRoomFourDetails(String gridPager,HttpServletResponse response){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);

			Map<String, Object> parameters = null;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();

			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			parameters.put("gameCode", gameCode);
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<CreateRoom> list = createRoomService
							.queryListFourDetails(parameters);
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
				List<CreateRoom> list = createRoomService
						.queryListFourDetails(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}
}
