package com.qs.pub.pay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.DateUtil;
import com.qs.common.util.PageUtil;
import com.qs.log.game.util.MyExportUtils;
import com.qs.pub.pay.model.PayLog;
import com.qs.pub.pay.service.IPayLogService;

@Controller
@RequestMapping(value = "/enterprise")
public class PayLogController extends BaseController{
 
	Logger log = Logger.getLogger(PayLogController.class);  
	
	@Autowired
	private IPayLogService payLog;
	   
	 @RequestMapping("payLog.html")
	 public String payLog(Model model,HttpServletRequest req){
		 PageUtil page = new PageUtil(req);
	     model.addAttribute("page", page);
	 	return "/WEB-INF/view/pay/pay_log_list";
	 }
	 
	 /**
	  * 直属会员列表信息代理信息
	  * @param gridPager
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping("payLogUi.html")
	 public Object payLogUi(String gridPager,HttpServletResponse response){
		 
		 log.debug("into payLogUi……");
		 Map<String, Object> parameters = new HashMap<>();

		 // 映射Pager对象
		 Pager pager = JSON.parseObject(gridPager, Pager.class);

		 parameters = pager.getParameters();
		 
		 if(parameters.isEmpty()){
			 log.debug("default param date!");
			 
			 parameters.put("startTime", DateUtil.getNewDate());
			 parameters.put("endTime", DateUtil.getNewDate());
			 parameters.put("gameType", 1);
			 parameters.put("status", 0);
			 parameters.put("id", null);
			 parameters.put("mid", null);
			 parameters.put("rebatetotal", null);
		 }
		
		 if(pager.getIsExport()){//判断是否是导出操作
	            if(pager.getExportAllData()){
	                //3.1、导出全部数据
	            	 List<PayLog> list =payLog.selectPayLogAll(parameters);
	                try {
	                	pager.setNowPage(0);
	                	pager.setPageSize(0);
						MyExportUtils.exportAll(response, pager, list);
					} catch (Exception e) {
						log.error(e);
						e.printStackTrace();
					}
	                return null;
	            }else{
	                //3.2、导出当前页数据
	                try {
						MyExportUtils.export(response, pager);
					} catch (Exception e) {
						log.error(e);
						e.printStackTrace();
					}
	                return null;
	            }
	        }
		 // 设置分页，page里面包含了分页信息
		 Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
		 List<PayLog> list =payLog.selectPayLogAll(parameters);
		 return getReturnPage(pager, page, list);
	 }
	
}
