package com.qs.pub.pay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.util.PageUtil;
import com.qs.pub.pay.model.WeixinMsg;
import com.qs.pub.pay.service.IWeinxinMsgService;

@Controller
@RequestMapping(value = "/weixinMsg/")
public class WeixinMsgController extends BaseController{
 
	Logger log = Logger.getLogger(WeixinMsgController.class);  
	
	@Autowired
	private IWeinxinMsgService weinxinMsgService;
	   
	 @RequestMapping("weixinMsgUI.html")
	 public Object weixinMsgUI(Model model,HttpServletRequest req){
		 
		 Map<String, Object> parameters = new HashMap<>();
		 PageUtil page = new PageUtil(req);

	     List<WeixinMsg> resultList =weinxinMsgService.getCacheList("weixinPay"+"*");
	     model.addAttribute("resultList", resultList);
	 	return "/WEB-INF/view/pay/weixin_msg_list";
	 }
	 

	
}
