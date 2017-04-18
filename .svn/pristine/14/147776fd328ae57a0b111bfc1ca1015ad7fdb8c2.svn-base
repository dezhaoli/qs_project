package com.qs.webside.agent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.DateUtil;
import com.qs.common.util.DateUtils;
import com.qs.common.util.PageUtil;
import com.qs.webside.agent.model.AdminAgentPublish;
import com.qs.webside.agent.model.AgentRebatescale;
import com.qs.webside.agent.service.IAdminAgentPublishService;

/**
 * 活动公告
 * @author zyy
 *
 */

@Controller
@RequestMapping(value = "/publish")
public class adminAgentPublishController extends BaseController{
	
	Logger log = Logger.getLogger(adminAgentPublishController.class);

	@Autowired
	private IAdminAgentPublishService adminAgentPublishService ;
	
	 @Value("${uploadServer.img}")
	 private String imgUploadUrl;
	 
	@RequestMapping("agentPublishUi.html")
	public String agentPublishUi(HttpServletRequest request,Model model){
		 PageUtil page = super.getPage(request);
         model.addAttribute("page", page);
         model.addAttribute("imgUploadUrl", imgUploadUrl);
		return "/WEB-INF/view/web/agent/agent_publish_form";
	}
	
	@ResponseBody
    @RequestMapping("agentPublishList.html")
    public Object puhlishList(String gridPager){
    	 Map<String, Object> parameters = null;
         // 映射Pager对象
         Pager pager = JSON.parseObject(gridPager, Pager.class);
         // 判断是否包含自定义参数
         parameters = pager.getParameters();
         // 设置分页，page里面包含了分页信息
         Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
         List<AdminAgentPublish> list = adminAgentPublishService.getPublishByAllList(parameters);
         return getReturnPage(pager, page, list);
    }
	
	@ResponseBody
    @RequestMapping("agentPublishAdd.html")
    public Map<String, Object> agentPublishAdd(String url,String sTime,String eTime,String type){
		 Map<String, Object> result = new HashMap<String, Object>();
		 AdminAgentPublish publish=new AdminAgentPublish();
		 publish.setImgName(url);
		 publish.setShowtype(Byte.valueOf(type));
         publish.setIstime(DateUtil.convertToInt(sTime));
         publish.setIetime(DateUtil.convertToInt(eTime));
         publish.setImktime(DateUtil.currentTimeToInt());
		 int  count = adminAgentPublishService.insertSelective(publish);
         if (count ==1){
        	 result.put(CommonContants.SUCCESS, true);
        	 result.put(CommonContants.MESSAGE, "添加成功！");
         }else {
        	 result.put(CommonContants.SUCCESS, false);
        	 result.put(CommonContants.MESSAGE, "添加失败！");
         }
         return result;
    }
	
}
