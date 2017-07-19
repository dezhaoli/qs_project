package com.qs.webside.api.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.log.game.service.GameRecordService;

/**
 * 
 * @ClassName: ShareLinkController 
 * @描述: 分享链接
 * @author moys
 * @date 2017年7月19日 上午10:38:58
 */
@Controller
@Scope("prototype")
@RequestMapping("/api/")
public class ShareLinkController extends BaseController {
	
	Logger log = Logger.getLogger(ShareLinkController.class);  
	
	@Autowired
	private GameRecordService gameRecordService;


	
}
