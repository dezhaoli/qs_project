package com.qs.webside.api.controller;

import com.qs.cfg.acti.model.ActiTimeControl;
import com.qs.cfg.acti.service.IActiTimeControlService;
import com.qs.cfg.acti.service.IAgentStoreSerivce;
import com.qs.cfg.acti.service.StoreService;
import com.qs.cfg.sys.service.SystemRoomService;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.exception.SystemException;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.member.model.Memberagents;
import com.qs.webside.member.service.IMemberAgentService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@Scope("prototype")
@RequestMapping("/api/config/")
public class ConfigController extends BaseController {
	

	
	@Autowired
	private StoreService storeService;
	@Autowired
	private SystemRoomService systemRoomService;

	@Value("${game.gametype}")
	private int gameType;

	@Resource
	private IMemberAgentService memberAgentService;

	@Resource
	private IAgentStoreSerivce agentStoreSerivce;

	@Resource
	private IActiTimeControlService actiTimeControlService;
	
	/**
	 * 房间
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("getRoom.do")
	@ResponseBody
	public Object getRoom(Model model, HttpServletRequest request) {
		String content="";
		try{		
	
			content=systemRoomService.createRoomJson();
			
			
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
		return this.getReturnData(content,AppConstants.Result.SUCCESS);
	}
	
	
	/**
	 * 商城
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("getStore.do")
	@ResponseBody
	public Object getStore(Model model, HttpServletRequest request,BaseRequest baseRequest) {
		String content="";
		try{
			content=storeService.createStoreJson();
		}catch(Exception e){
			 e.printStackTrace();
			//throw new SystemException(e);
		}
		return this.getReturnData(content,AppConstants.Result.SUCCESS);
	}

	/**
	 * (新)商城
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("getAgentStore.do")
	@ResponseBody
	public Object getAgentStore(Model model, HttpServletRequest request,BaseRequest baseRequest) {
		String content="";
		try{
			if (20 == gameType) {//如果是江西麻将，类型为20；
				Map<String, Object> contentMap = new HashMap<>();
				contentMap.put("agentStore", agentStoreSerivce.createAgentStoreJson());
				contentMap.put("store", storeService.createStoreJson());
				return this.getReturnData(contentMap,AppConstants.Result.SUCCESS);
			} else {//其他游戏
				content=storeService.createStoreJson();
			}
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
		return this.getReturnData(content,AppConstants.Result.SUCCESS);
	}

	/**
	 * @Author:zun.wei , @Date:2017/5/25 17:57
	 * @Description:活动时间预告
	 * @param model
	 * @param request
	 * @param baseRequest
	 * @return
	 */
	@RequestMapping("getActiTimeControl.do")
	@ResponseBody
	public Object getActiTimeControl(Model model, HttpServletRequest request,BaseRequest baseRequest,Integer type) {
		Map<String,Object> actiTimeControl = actiTimeControlService.getActTimeControlLimit01(type);
		if (actiTimeControl == null || actiTimeControl.size() == 0) {
			return this.getReturnData(actiTimeControl, AppConstants.Result.FAILURE);
		} else {
			return this.getReturnData(actiTimeControl, AppConstants.Result.SUCCESS);
		}
	}
	
	
}
