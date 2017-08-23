/*
 * 文件名：TaxesInviteDayController.java	 
 * 时     间：下午8:48:28
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.log.game.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Column;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.dtgrid.util.ExportUtils;
import com.qs.common.exception.SystemException;
import com.qs.constant.Constant;
import com.qs.datasource.DataSourceSwitch;
import com.qs.log.game.model.MajiangGameRecord;
import com.qs.log.game.model.TaxesInviteDay;
import com.qs.log.game.service.ITaxesInviteDayService;
import com.qs.pub.sys.model.UserEntity;
import com.qs.pub.sys.service.BusinessService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TaxesInviteDayController
 * @描述: (这里用一句话描述这个类的作用)
 * @author qs
 * @date 2017年6月2日 下午8:48:28 
 */
@Controller
@RequestMapping(value="/businessCount/")
public class TaxesInviteDayController extends BaseController
{
	@Resource
	private ITaxesInviteDayService taxesInviteDayService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Resource
	private BusinessService businessService;


	@RequestMapping("toBusinessCountListUi.html")
	public String toBusinessCountListUi(){
		return "WEB-INF/view/web/businessAgent/memberbusiness_list";
	}

	@RequestMapping("queryBusinessCountList.html")
	@ResponseBody
	public Object queryBusinessCountList(String gridPager,HttpServletResponse response){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			Map<String, Object>  map = new HashMap<String,Object>();
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);


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
			parameters.put("gameCode", Constant.getDataCenterBusinessGameCode(gameCode));
			parameters.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);
			if (gameType != null && !gameType.trim().equals(""))
			{
				if (gameType.endsWith("_pub"))
				{
					parameters.put("stat", gameType);
				} else
				{
					parameters.put("stat", gameType + "_stat");
				}
			}
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<TaxesInviteDay> list = taxesInviteDayService.queryBusinessListByPage(parameters);

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
				List<TaxesInviteDay> list = taxesInviteDayService.queryBusinessListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}

	}
	@RequestMapping("queryBusinessAmountTotal.html")
	@ResponseBody
	public Object queryBusinessAmountTotal(String stime,String etime,String name,String businessId,String groupId){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			Map<String, Object>  map = new HashMap<String,Object>();
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);

			//如果是领导人可查看的商务id
			map.put("gameType", gameType);
			map.put("uId", userEntity.getId());
			List businessIdList = businessService.findByuId(map);
			//如果式商务，只能看自己
			map.put("accountName", userEntity.getAccountName());
			List businessIdList2 = businessService.selectBusiness(map);

			Map<String, Object> parameters = new HashMap<String,Object>();

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


			// 判断是否包含自定义参数
			parameters.put("dbTable", dataSourceName);
			parameters.put("startDate", stime);
			parameters.put("endDate", etime);
			parameters.put("name", name);
			parameters.put("businessId", businessId);
			parameters.put("groupId", groupId);
			parameters.put("gameType", gameType);
			parameters.put("gameCode", Constant.getDataCenterBusinessGameCode(gameCode));
			parameters.put("businessIdListByGroup", businessIdListByGroup != null && businessIdListByGroup.size()>0?businessIdListByGroup:null);
			if (gameType != null && !gameType.trim().equals(""))
			{
				if (gameType.endsWith("_pub"))
				{
					parameters.put("stat", gameType);
				} else
				{
					parameters.put("stat", gameType + "_stat");
				}
			}
			Double totals = taxesInviteDayService.queryBusinessAmountTotal(parameters);
			return totals == null?0:totals;
		} catch (Exception e)
		{
			throw new SystemException(e);
		}

	}


	@RequestMapping("queryAgentAmountTotal.html")
	@ResponseBody
	public Object queryAgentAmountTotal(String stime,String etime,String name,String id){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			Map<String, Object>  map = new HashMap<String,Object>();
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);

			Map<String, Object> parameters = new HashMap<String,Object>();
			// 判断是否包含自定义参数
			parameters.put("dbTable", dataSourceName);
			parameters.put("startDate", stime);
			parameters.put("endDate", etime);
			parameters.put("name", name);
			parameters.put("id", id);
			parameters.put("gameType", gameType);
			Double totals = taxesInviteDayService.queryAgentAmountTotal(parameters);
			return totals == null?0:totals;
		} catch (Exception e)
		{
			throw new SystemException(e);
		}

	}

	@RequestMapping("toAgentCountListUi.html")
	public String toAgentCountListUi(String id,Model model){
		model.addAttribute("id",id);
		return "WEB-INF/view/web/businessAgent/agent_list";
	}

	@RequestMapping("queryAgentCountList.html")
	@ResponseBody
	public Object queryAgentCountList(String gridPager,String id,HttpServletResponse response){
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
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					List<TaxesInviteDay> list = taxesInviteDayService.queryAgentListByPage(parameters);

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
				List<TaxesInviteDay> list = taxesInviteDayService.queryAgentListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}

	}
	@RequestMapping("toGameRecordListUi.html")
	public String toGameRecordListUi(){
		return "WEB-INF/view/web/user/game_record_list";
	}

	@RequestMapping("queryGameRecordList.html")
	@ResponseBody
	public Object queryGameRecordList(String gridPager,HttpServletResponse response){
		try
		{
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameCode = valueOper.get(Constant.DATA_CENTER_GAME_CODE+userEntity.getId());
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);

			Map<String, Object> parameters = null;
			List<MajiangGameRecord> list;
			// 映射Pager对象
			Pager pager = JSON.parseObject(gridPager, Pager.class);
			// 判断是否包含自定义参数
			parameters = pager.getParameters();
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			parameters.put("dbTable", dataSourceName);
			parameters.put("gameType", gameType);
			parameters.put("gameCode", Constant.getDataCenterBusinessGameCode(gameCode));
			if (gameType != null && !gameType.trim().equals(""))
			{
				if (gameType.equals("_pub"))
				{
					parameters.put("stat", gameType);
				} else
				{
					parameters.put("stat", gameType + "_stat");
				}
			}
			// 3、判断是否是导出操作
			if (pager.getIsExport())
			{
				if (pager.getExportAllData())
				{
					// 3.1、导出全部数据
					list = taxesInviteDayService.queryGameRecordListByPage(parameters);
					ExportUtils.exportAll(response, pager, list);
					list = null;
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
				/*Page<Object> page = PageHelper.startPage(pager.getNowPage(),
						pager.getPageSize());*/
				Page<Object> page = new Page<Object>();
				list = taxesInviteDayService.queryGameRecordListByPage(parameters);
				return getReturnPage(pager, page, list);
			}
		} catch (Exception e)
		{
			throw new SystemException(e);
		}

	}
	@RequestMapping("exportAll.html")
	@ResponseBody
	public void exportAll(String startDate,String endDate,HttpServletResponse response){
		try
		{
			UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
			ValueOperations<String, String> valueOper=redisTemplate.opsForValue();
			String dataSourceName = valueOper.get(Constant.DATA_CENTER_GAME_TYPE+userEntity.getId());
			String gameType = Constant.getDataCenterBusinessGameType(dataSourceName);
			String logDataSourceType=dataSourceName+"LogDataSource";
			String mainDataSourceType = dataSourceName + "AgentDataSource";
			DataSourceSwitch.setLogDataSourceType(logDataSourceType);
			DataSourceSwitch.setMainDataSourceType(mainDataSourceType);

			Map<String, Object> parameters = new HashMap<String,Object>();
			// 判断是否包含自定义参数
			parameters.put("dbTable", dataSourceName);
			parameters.put("startDate", startDate);
			parameters.put("endDate", endDate);
			parameters.put("gameType", gameType);
			List<TaxesInviteDay> list = taxesInviteDayService.queryAllBusinessAgent(parameters);
			Pager pager = new Pager();
			pager.setExportFileName("代理商团队充值明细");
			pager.setExportType("excel");

			List<Column> columns = new ArrayList<Column>();
			Column bean = new Column();
			bean.setId("businessName");
			bean.setTitle("商务名称");
			Column bean2 = new Column();
			bean2.setId("mid");
			bean2.setTitle("代理商ID");

			Column bean3 = new Column();
			bean3.setId("name");
			bean3.setTitle("代理商名称");

			Column bean4 = new Column();
			bean4.setId("invitetotal");
			bean4.setTitle("新增注册用户数量");

			Column bean5 = new Column();
			bean5.setId("paytotal");
			bean5.setTitle("充值总金额");

			Column bean6 = new Column();
			bean6.setId("startDate");
			bean6.setTitle("开始日期  ");

			Column bean7 = new Column();
			bean7.setId("endDate");
			bean7.setTitle("结束日期  ");



			columns.add(bean);
			columns.add(bean2);
			columns.add(bean3);
			columns.add(bean4);
			columns.add(bean5);
			columns.add(bean6);
			columns.add(bean7);

			pager.setExportColumns(columns);
			ExportUtils.exportAll(response, pager, list);

		} catch (Exception e)
		{
			throw new SystemException(e);
		}

	}
}
