/*
 * 文件名：MemberagentsController.java	 
 * 时     间：上午9:36:53
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.agent.game.controller;

import java.math.BigDecimal;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.agent.game.model.Memberagents;
import com.qs.agent.game.model.Memberbusiness;
import com.qs.agent.game.service.IMemberagentsService;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.common.exception.SystemException;
import com.qs.constant.Constant;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.TaxesInvite;
import com.qs.pub.sys.model.BusinessGroup;
import com.qs.pub.sys.model.Group;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.BusinessService;
import com.qs.pub.sys.service.GroupService;
import com.qs.pub.util.DateHandle;

/**
 * @ClassName: MemberagentsController
 * @描述: 代理商清理功能控制器
 * @author wangzhen
 * @date 2017年5月17日 上午9:36:53
 */
@Controller
@RequestMapping(value = "/memberagents/")
public class MemberagentsController extends BaseController
{
	@Resource
	private IMemberagentsService memberagentsService;
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Resource
	private GroupService groupService;
	@Resource
	private BusinessService businessService;
	
	/**
	 * 
	 * @标题: toMemberagentsListUi
	 * @描述: 跳转到商务数据列表页面
	 *
	 * @参数信息
	 * @param gameType
	 * @param model
	 * @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toMemberagentsListUi.html")
	public String toMemberagentsListUi(String gameType, Model model)
	{
		model.addAttribute("gameType", gameType);
		return "/WEB-INF/view/loginfo/memberagents_list";
	}
	
	/**
	 * 
	 * @标题: queryListByPage
	 * @描述: 商务数据查询接口
	 *
	 * @参数信息
	 * @param gameType
	 * @param gridPager
	 * @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("memberagentsList.html")
	@ResponseBody
	public Object queryListByPage(String gameType, String gridPager,
			HttpServletResponse response)
	{
		try
		{
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String accountName  = userEntity.getAccountName();
			String roleId = valueOper.get(Constant.USER_ROLE_ID+userEntity.getId());
			
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
			dataSourceName = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("dbtable", dataSourceName);
			
			//判断用户是否代理商
			if(roleId != null && roleId.equals("2")){
				parameters.put("accountName", accountName);
			}
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Memberbusiness> list = memberagentsService
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
				
				List<Memberbusiness> list = memberagentsService
						.queryListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	
	/**
	 * 
	 * @标题: toMemberagentsDetailsUi
	 * @描述: 跳转到 不合格代理商佣金汇总页面
	 *
	 * @参数信息
	 * @param gameType
	 * @param mid
	 * @param model
	 * @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toMemberagentsDetailsUi.html")
	public String toMemberagentsDetailsUi(Model model)
	{
		return "/WEB-INF/view/loginfo/memberagents_details_list";
	}
	
	/**
	 * 
	 * @标题: queryListByPageDetails
	 * @描述: 不合格代理商佣金汇总查询接口
	 *
	 * @参数信息
	 * @param gameType
	 * @param mid
	 * @param gridPager
	 * @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("memberagentsDetails.html")
	@ResponseBody
	public Object queryListByPageDetails(String gridPager,HttpServletResponse response)
	{
		try
		{
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
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters = pager.getParameters();
			parameters.put("dbtable", dataSourceName);
			parameters.put("gameType", gameType);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Memberagents> list = memberagentsService
							.selectByMid(parameters);
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
				
				List<Memberagents> list = memberagentsService
						.selectByMid(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	
	/**
	 * 
	 * @标题: toMemberagentsAddListUi 
	 * @描述:  跳转到每日新增代理商-商务页面
	 *
	 * @参数信息
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toMemberagentsAddListUi.html")
	public String toMemberagentsAddListUi(Model model)
	{
		return "/WEB-INF/view/loginfo/memberagents_add_list";
	}
	
	/**
	 * 
	 * @标题: queryMemberagentsAddListByPage 
	 * @描述:  日新增代理商查询接口-商务
	 *
	 * @参数信息
	 *    @param gridPager
	 *    @param response
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("queryMemberbusinessAddListByPage.html")
	@ResponseBody
	public Object queryMemberbusinessAddListByPage(String gridPager,
			HttpServletResponse response)
	{
		try
		{
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
			
			dataSourceName = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("dbtable", dataSourceName);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Memberbusiness> list = memberagentsService
							.queryMemberbusinessAddListByPage(parameters);
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
				
				List<Memberbusiness> list = memberagentsService
						.queryMemberbusinessAddListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	
	/**
	 * 
	 * @标题: toMemberagentsAddDetailsUi 
	 * @描述:  跳转到代理商-新增代理商统计页面
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param id
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toMemberagentsAddDetailsUi.html")
	public String toMemberagentsAddDetailsUi(String gameType, String id,String date,
			Model model)
	{
		model.addAttribute("id", id);
		model.addAttribute("date", date);
		return "/WEB-INF/view/loginfo/memberagents_add_details_list";
	}
	
	/**
	 * 
	 * @标题: queryMemberagentsAddListDetails 
	 * @描述:  代理商-新增代理商统计报表接口
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param id
	 *    @param gridPager
	 *    @param response
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("queryMemberagentsAddListDetails.html")
	@ResponseBody
	public Object queryMemberagentsAddListDetails(String id,String date,
			String gridPager,HttpServletResponse response)
	{
		try
		{
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
			dataSourceName = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("id", id);
			parameters.put("date", date);
			parameters.put("dbtable", dataSourceName);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Memberagents> list = memberagentsService
							.queryMemberagentsAddListDetailsByPage(parameters);
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
				
				List<Memberagents> list = memberagentsService
						.queryMemberagentsAddListDetailsByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	/**
	 * 
	 * @标题: toMemberpaymentAddDetailsUi 
	 * @描述:  跳转到当日充值商务页面
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param id
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toMemberpaymentUi.html")
	public String toMemberpaymentAddDetailsUi(String gameType, String id,
			Model model)
	{
		model.addAttribute("id", id);
		return "/WEB-INF/view/loginfo/memberpayment_list";
	}
	
	/**
	 * 
	 * @标题: queryMemberpaymentAddListDetails 
	 * @描述: 当日充值总额-商务 接口
	 *
	 * @参数信息
	 *    @param id
	 *    @param gridPager
	 *    @param response
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("queryMemberpaymentList.html")
	@ResponseBody
	public Object queryMemberpaymentAddListDetails(String id,
			String gridPager,HttpServletResponse response)
	{
		try
		{
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
			dataSourceName = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("id", id);
			parameters.put("dbtable", dataSourceName);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Memberagents> list = memberagentsService
							.queryMemberpaymentListByPage(parameters);
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
				
				List<Memberagents> list = memberagentsService
						.queryMemberpaymentListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	/**
	 * 
	 * @标题: toMemberpaymentDetailsUi 
	 * @描述:  跳转到商务当日统计金额页面
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param id
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toMemberpaymentDetailsUi.html")
	public String toMemberpaymentDetailsUi(String gameType, String id,String date,
			Model model)
	{
		model.addAttribute("id", id);
		model.addAttribute("date", date);
		return "/WEB-INF/view/loginfo/memberpayment_details_list";
	}
	/**
	 * 
	 * @标题: queryMemberpaymentListDetails 
	 * @描述:  商务当日充值金额接口
	 *
	 * @参数信息
	 *    @param id
	 *    @param gridPager
	 *    @param response
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("queryMemberpaymentListDetails.html")
	@ResponseBody
	public Object queryMemberpaymentListDetails(String id,String date,
			String gridPager,HttpServletResponse response)
	{
		try
		{
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
			dataSourceName = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("id", id);
			parameters.put("date", date);
			parameters.put("dbtable", dataSourceName);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Memberagents> list = memberagentsService
							.queryMemberpaymentListDetailsByPage(parameters);
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
				
				List<Memberagents> list = memberagentsService
						.queryMemberpaymentListDetailsByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	/**
	 * 
	 * @标题: toUserAddListUi 
	 * @描述:  跳转到新增用户页面-商务
	 *
	 * @参数信息
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toUserAddListUi.html")
	public String toUserAddListUi(Model model)
	{
		return "/WEB-INF/view/loginfo/user_add_list";
	}
	/**
	 * 
	 * @标题: queryUserAddListByPage 
	 * @描述:  新增用户查询接口-商务
	 *
	 * @参数信息
	 *    @param gridPager
	 *    @param response
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("queryUserAddListByPage.html")
	@ResponseBody
	public Object queryUserAddListByPage(String gridPager,
			HttpServletResponse response)
	{
		try
		{
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
			
			dataSourceName = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("dbtable", dataSourceName);
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Memberbusiness> list = memberagentsService
							.queryUserAddListByPage(parameters);
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
				
				List<Memberbusiness> list = memberagentsService
						.queryUserAddListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	/**
	 * 
	 * @标题: toUserAddDetailsUi 
	 * @描述:  跳转到当日新增用户页面-代理商
	 *
	 * @参数信息
	 *    @param gameType
	 *    @param id
	 *    @param model
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("toUserAddDetailsUi.html")
	public String toUserAddDetailsUi(String gameType, String id,String date,
			Model model)
	{
		model.addAttribute("id", id);
		model.addAttribute("date", date);
		return "/WEB-INF/view/loginfo/user_add_details_list";
	}
	/**
	 * 
	 * @标题: queryUserAddDetailsListByPage 
	 * @描述:  新增用户统计-代理商
	 *
	 * @参数信息
	 *    @param gridPager
	 *    @param response
	 *    @return
	 *
	 * @返回类型 Object
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	@RequestMapping("queryUserAddDetailsListByPage.html")
	@ResponseBody
	public Object queryUserAddDetailsListByPage(String gridPager,String date,String id,
			HttpServletResponse response)
	{
		try
		{
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
			
			dataSourceName = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("dbtable", dataSourceName);
			parameters.put("id", id);
			parameters.put("date", date);
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Memberbusiness> list = memberagentsService
							.queryUserAddListDetailsByPage(parameters);
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
				
				List<Memberbusiness> list = memberagentsService
						.queryUserAddListDetailsByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("toMemberagentsLevelListUi.html")
	public String toMemberagentsLevelListUi()
	{
		return "/WEB-INF/view/loginfo/memberagents_level_list";
	}
	
	@RequestMapping("memberagentsLevelList.html")
	@ResponseBody
	public Object memberagentsLevelList(String gridPager,
			HttpServletResponse response){
		try
		{
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
			
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<Memberagents> list = memberagentsService
							.queryAgentLevel(parameters);
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
				
				List<Memberagents> list = memberagentsService
						.queryAgentLevel(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping("toMemberagentsListOfAddCountUi.html")
	public String toMemberagentsListOfAddCountUi(){
		
		return "/WEB-INF/view/loginfo/memberagents_count";
	}
	@RequestMapping("memberagentsListOfAddCount.html")
	@ResponseBody
	public Object memberagentsListOfAddCount(String stime,String etime){
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
				List<Memberagents> taxList = memberagentsService.queryListCountByBusinessId(parameters);
				
				//lengend项  分公司
				for(Group group:groupList){
					Integer groupId = group.getId();
					if(key == groupId){
						lengend.add(group.getUserGroupName());
					}
				}
				//如果查出来的数据不为空
				if(taxList != null && !taxList.isEmpty()){
					List<Integer> da  = new ArrayList<Integer>();
					for(int i = 0;i<dateHandle.size();i++){
						boolean flag = true;
						int index = taxList.size();
						for(int j=0;j<index && flag;j++){
							if(dateHandle.get(i).equals(taxList.get(j).getMktimeStr())){
								da.add(taxList.get(j).getTotals());
								flag = false;
							}
						}
						if(flag){
							da.add(0);
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
	
	@RequestMapping("analyseCountOfBusinessUi.html")
	public String analyseCountOfBusinessUi(){
		
		return "/WEB-INF/view/loginfo/business_memberagent_count";
	}
	
	@RequestMapping("analyseCountOfBusinessList.html")
	@ResponseBody
	public Object analyseCountOfBusinessList(String stime,String etime,String groupId){
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
				List<Memberagents> taxList = memberagentsService.queryBusinessCountOfAdd(parameters);
				//lengend项  分公司
				lengend.add(business.getBusinessName());
				//如果查出来的数据不为空
				if(taxList != null && !taxList.isEmpty()){
					List<Integer> da  = new ArrayList<Integer>();
					for(int i = 0;i<dateHandle.size();i++){
						boolean flag = true;
						int index = taxList.size();
						for(int j=0;j<index && flag;j++){
							if(dateHandle.get(i).equals(taxList.get(j).getMktimeStr())){
								da.add(taxList.get(j).getTotals());
								flag = false;
							}
						}
						if(flag){
							da.add(0);
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
