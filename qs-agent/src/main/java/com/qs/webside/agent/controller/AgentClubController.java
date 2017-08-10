package com.qs.webside.agent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.JsonArray;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.DateUtil;
import com.qs.common.util.PageUtil;
import com.qs.webside.agent.model.AgentClubGroup;
import com.qs.webside.agent.model.AgentClubMember;
import com.qs.webside.agent.model.AgentClubwhiteList;
import com.qs.webside.agent.service.IAgentClubGroupService;
import com.qs.webside.agent.service.IAgentClubMemberService;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.service.IAgentClubwhiteListService;
import com.qs.webside.member.service.IMemberFidesService;
import com.qs.webside.util.AgentClubRoom;
import com.qs.webside.util.AgentDataSourceUtil;
import com.qs.webside.util.AgentUtil;


@Controller
@RequestMapping(value = "/agentClub")
public class AgentClubController extends BaseController{

	Logger log = Logger.getLogger(AgentClubController.class);  
	
	@Value("${club.roomLow}")
	private String roomLow;
	
	@Value("${club.roomMid}")
	private String roomMid;
	
	@Value("${club.roomHig}")
	private String roomHig;
	
	@Autowired
	private IAgentClubMemberService agentClubMemberService ;
	
	@Autowired 
	private IAgentClubGroupService agentClubGroupService ;
	
	@Autowired
	private AgentDataSourceUtil agentDataSourceUtil;
	
	@Autowired
	private IMemberFidesService memberFidesService;
	
	@Autowired
	private IAgentClubwhiteListService agentClubwhiteListService;
	
	@RequestMapping("infoAgentClubUi.html")
	public String infoAgentClubUi(Model model,HttpServletRequest req){
		agentDataSourceUtil.setReadDataSourceType();//设置数据源
		
		MemberAgents memberAgents = (MemberAgents) SecurityUtils.getSubject().getPrincipal();
		AgentClubwhiteList agentClubwhiteList =agentClubwhiteListService.selectByMid(memberAgents.getMid());
		if (agentClubwhiteList ==null){
			model.addAttribute("clubType", 0);
		}else {
			if (agentClubwhiteList.getClubType()==0){
				model.addAttribute("clubType", 0);
			}
			if (agentClubwhiteList.getClubType()==1){
				model.addAttribute("clubType", 1);
			}
		}
		PageUtil page = new PageUtil(req);
	    model.addAttribute("gameType", agentDataSourceUtil.getGameType());
        model.addAttribute("page", page);
        return "/WEB-INF/view/web/agent/club/agent_club_list";
	}
	
	/**
	 * 根据当前cmid获取当前俱乐部成员
	 * @param gridPager
	 * @return
	 * @author:zyy
	 * @time:2017年5月10日
	 */
	@ResponseBody
	@RequestMapping("infoAgentClubList.html")
	public Object infoAgentClubList(String gridPager){
		
		agentDataSourceUtil.setReadDataSourceType();//设置数据源
		
		Map<String, Object> parameters = null;
		MemberAgents memberAgents = (MemberAgents) SecurityUtils.getSubject().getPrincipal();
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			return null;//如果没有mid传过来则不执行查询。
		}
		//查询俱乐部用户
		parameters.put("cmid", memberAgents.getMid());
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
		List<Map<String,Object>> list=agentClubMemberService.selectByMid(parameters);
		return getReturnPage(pager, page, list);
	}
	
	/**
	 * 给当前代理商添加俱乐部成员
	 * @param mid
	 * @return
	 * @author:zyy
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("addAgentClubInfo.html")
	public Map<String, Object> addAgentClubInfo(int mid){
		
		agentDataSourceUtil.setWriteDataSourceType();//设置数据源
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		AgentClubMember aClubMember=new AgentClubMember();
		if (StringUtils.isBlank(mid+"")) {
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "请输入MID！");
			return result;
		}
		//判断用户存在否
		 MemberFides memberFides =memberFidesService.selectByPrimaryKey(mid);
		 if (memberFides ==null){
			 result.put(CommonContants.SUCCESS, false);
			 result.put(CommonContants.MESSAGE, "当前用户不存在！");
			 return result;
		 }

		//判断用户是否已经存在俱乐部里面
		MemberAgents memberAgents = (MemberAgents) SecurityUtils.getSubject().getPrincipal();
		parameters.put("cmid", memberAgents.getMid());
		parameters.put("mid", mid);
		AgentClubMember agentClubMember =agentClubMemberService.selectByMidInfo(parameters);
		if (agentClubMember !=null ){
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "该用户已经是您成员！");
			return result;
		}
		
		aClubMember.setCmid(memberAgents.getMid());
		aClubMember.setCreateTime(DateUtil.getNowDate());
		aClubMember.setMid(mid);
		agentClubMemberService.insertSelective(aClubMember);
		result.put(CommonContants.SUCCESS, true);
		result.put(CommonContants.MESSAGE, "添加成功！");
		// 映射Pager对象
		// 判断是否包含自定义参数
		return result;
	}
	
	/**
	 * 删除当前代理商指定mid俱乐部成员
	 * @param id
	 * @return
	 * @author:zyy
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("deleteAgentClubInfo.html")
	public Map<String, Object> deleteAgentClubInfo(int id){
		
		agentDataSourceUtil.setWriteDataSourceType();//设置数据源
		
		Map<String, Object> result = new HashMap<String, Object>();
		agentClubMemberService.deleteByPrimaryKey(id);
		result.put(CommonContants.SUCCESS, true);
		result.put(CommonContants.MESSAGE, "删除成功！");
		return result;
	}
	
	/**
	 * 代开房
	 * @param id
	 * @param state
	 * @return
	 * @author:zyy
	 * @time:2017年5月11日
	 */
	@ResponseBody
	@RequestMapping("updateAgentClubInfo.html")
	public Map<String, Object> updateAgentClubInfo(int id,int state){
		
		agentDataSourceUtil.setWriteDataSourceType();//设置数据源
		
		Map<String, Object> result = new HashMap<String, Object>();
		AgentClubMember agentClubMember= new AgentClubMember();
		agentClubMember.setId(id);
		agentClubMember.setOpenRoom(state);
		int count =agentClubMemberService.updateByPrimaryKeySelective(agentClubMember);
		if (count !=0 ){
			result.put(CommonContants.SUCCESS, true);
			result.put(CommonContants.MESSAGE, "开房成功！");
		}else {
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "开房失败！");
		}
		return result;
	}
	
	@RequestMapping("editClubRoomChargingUi.html")
	public String editClubRoomCharging(Model model){
		agentDataSourceUtil.setWriteDataSourceType();//设置数据源
		AgentClubRoom	agentClubRoom =new AgentClubRoom();
		model.addAttribute("roomLow", roomLow);
		model.addAttribute("roomMid", roomMid);
		model.addAttribute("roomHig", roomHig);
		AgentClubGroup agentClubGroup=agentClubGroupService.selectByCmidKeyInfo(AgentUtil.getAgentMid());
		if (agentClubGroup !=null) {
			agentClubRoom=getParam(agentClubGroup);
		}
		model.addAttribute("clubRoom", agentClubRoom);
		return "/WEB-INF/view/web/agent/club/agent_clubRoomCharging_edit";
	}
	
	
	/**
	 * 代理商俱乐部房卡扣费参数设置
	 * @param agentClubRoom
	 * @return
	 * @author:zyy
	 * @time:2017年5月18日
	 */
	@ResponseBody
	@RequestMapping("updateClubRoomCharging.html")
	public Map<String,Object> updateClubRoomCharging(AgentClubRoom agentClubRoom){
		agentDataSourceUtil.setWriteDataSourceType();//设置数据源
		AgentClubGroup agentClubGroup=agentClubGroupService.selectByCmidKeyInfo(AgentUtil.getAgentMid());
		Map<String,Object>  result=new HashMap<>();
		if(agentClubRoom ==null) {
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "参数不能为空！");
		}
		if (agentClubGroup ==null ){
			result.put(CommonContants.SUCCESS, false);
			result.put(CommonContants.MESSAGE, "该俱乐部暂不存在！");
			return result;
		}
		agentClubGroup=setParam(agentClubRoom,agentClubGroup);
	  int count =agentClubGroupService.updateByPrimaryKeySelective(agentClubGroup);
	  if (count >0){
		  result.put(CommonContants.SUCCESS, true);
		  result.put(CommonContants.MESSAGE, "设置成功！");
	  }
		return result;
	}
	

	/**
	 * 数据封装入库俱乐部房间参数设置
	 * @param agentClubRoom
	 * @param agentClubGroup
	 * @return
	 * @author:zyy
	 * @time:2017年5月22日
	 */
	public AgentClubGroup setParam(AgentClubRoom agentClubRoom,AgentClubGroup agentClubGroup){
		
		JSONArray big=new JSONArray();
		JSONArray yous=new JSONArray();
		JSONArray replace=new JSONArray();
		big.add( agentClubRoom.getBigLows());
		big.add( agentClubRoom.getBigMid());
		big.add( agentClubRoom.getBigHig());
		agentClubGroup.setPayBig(big.toString());
		
		yous.add( agentClubRoom.getYoursLows());
		yous.add( agentClubRoom.getYoursMid());
		yous.add( agentClubRoom.getYoursHig());
		agentClubGroup.setPayYourself(yous.toString());
		
		replace.add( agentClubRoom.getReplaceLows());
		replace.add( agentClubRoom.getReplaceMid());
		replace.add( agentClubRoom.getReplaceHig());
		agentClubGroup.setPayReplace(replace.toString());
		return agentClubGroup;
	}
	
	/**
	 * 设置页面呈现参数封装
	 * @param agentClubGroup
	 * @return
	 * @author:zyy
	 * @time:2017年5月22日
	 */
	public AgentClubRoom getParam(AgentClubGroup agentClubGroup){
		
		AgentClubRoom agentClubRoom =new AgentClubRoom();
		JSONArray big=JSONArray.parseArray(agentClubGroup.getPayYourself());
		JSONArray yous=JSONArray.parseArray(agentClubGroup.getPayBig());
		JSONArray replace=JSONArray.parseArray(agentClubGroup.getPayReplace());
		
		agentClubRoom.setBigLows(big.getIntValue(0));
		agentClubRoom.setBigMid(big.getIntValue(1));
		agentClubRoom.setBigHig(big.getIntValue(2));
		
		agentClubRoom.setYoursLows(yous.getIntValue(0));
		agentClubRoom.setYoursMid(yous.getIntValue(1));
		agentClubRoom.setYoursHig(yous.getIntValue(2));
		
		agentClubRoom.setReplaceLows(replace.getIntValue(0));
		agentClubRoom.setReplaceMid(replace.getIntValue(1));
		agentClubRoom.setReplaceHig(replace.getIntValue(2));
		return agentClubRoom;
	}
}
