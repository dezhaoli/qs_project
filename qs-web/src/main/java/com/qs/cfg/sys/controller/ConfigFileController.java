package com.qs.cfg.sys.controller;

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
import com.qs.common.exception.SystemException;
import com.qs.common.util.FileUtils;
import com.qs.common.util.PageUtil;



@Controller
@Scope("prototype")
@RequestMapping("/cfg/configFile/")
public class ConfigFileController extends BaseController {
	
	@Value("${cfg.dir}")
	private String cfgDir;
	
	@Autowired
	private StoreService storeService;
	@Autowired
	private SystemRoomService systemRoomService;
	
	/**
	 * 配置文件列表
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("listUI.html")
	public String addCfgUI(Model model, HttpServletRequest request) {
		try
		{
			PageUtil page = new PageUtil(request);
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/cfg/cfg_list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	/**
	 * 下载配置文件
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("downloadUI.html")
	public String viewFileUI(Model model, HttpServletRequest request,String fileName) {
		try{		
			
			model.addAttribute("filePath",cfgDir);
			model.addAttribute("fileName",fileName);
			
			return Common.BACKGROUND_PATH + "/cfg/download_file";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	/**
	 * 配置文件查看
	 * @param model
	 * @param request
	 * @param fileName
	 * @return
	 */
	@RequestMapping("viewConfigFile.html")
	@ResponseBody
	public String viewFile(Model model, HttpServletRequest request,String fileName) {
		String content="";
		try{		
			fileName=cfgDir+fileName;
			content=FileUtils.readFileByLines(fileName);
			
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
		return content;
	}
	
	
	/**
	 * 生成房间配置
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("add.html")
	public String createConfigFile(Model model, HttpServletRequest request) {
		try{
			//创建房间Json
			systemRoomService.createRoomJson();
			//创建房间xml
			systemRoomService.createRoomXml();
			//创建商家Json
			storeService.createStoreJson();
			//创建商家Xml
			storeService.createStoreXml();

			model.addAttribute("roomData", "room.data");
			model.addAttribute("configXml", "c_config.xml");
			model.addAttribute("commonData", "common.data");
			model.addAttribute("commonXml", "common.xml");
			
			return Common.BACKGROUND_PATH + "/cfg/cfg_view";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	
	

	
}
