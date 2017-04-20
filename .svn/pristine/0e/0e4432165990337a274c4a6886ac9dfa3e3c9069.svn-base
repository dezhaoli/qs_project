package com.qs.webside.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qs.cfg.acti.service.StoreService;
import com.qs.cfg.sys.service.SystemRoomService;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.constant.AppConstants;
import com.qs.common.exception.SystemException;
import com.qs.common.util.FileUtils;
import com.qs.common.util.PageUtil;



@Controller
@Scope("prototype")
@RequestMapping("/api/config/")
public class ConfigController extends BaseController {
	

	
	@Autowired
	private StoreService storeService;
	@Autowired
	private SystemRoomService systemRoomService;
	
	
	/**
	 * 房间
	 * @param model
	 * @param request
	 * @param fileName
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
	 * @param fileName
	 * @return
	 */
	@RequestMapping("getStore.do")
	@ResponseBody
	public Object getStore(Model model, HttpServletRequest request) {
		String content="";
		try{		
	
			content=storeService.createStoreJson();
			
			
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
		return this.getReturnData(content,AppConstants.Result.SUCCESS);
	}
	
	
	
	
	
}
