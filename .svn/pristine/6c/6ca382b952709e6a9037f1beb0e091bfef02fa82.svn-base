package com.qs.webside.agent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.DateUtil;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.model.CommonAgents;
import com.qs.webside.agent.model.AgentRebatescale;
import com.qs.webside.agent.service.IAgentRebatescaleService;
import com.qs.webside.member.model.MemberAgents;

@Controller
@RequestMapping(value = "/agentRebate")
public class AgentRebateController extends BaseController{	
	
	Logger log = Logger.getLogger(AgentRebateController.class);

	@Autowired
	private IAgentRebatescaleService agentRebatescaleService;
	
    @RequestMapping("rebateUi.html")
	public String rebateUi( HttpServletRequest request,Model model){
    	 PageUtil page = super.getPage(request);
         model.addAttribute("page", page);
         return "WEB-INF/view/web/agent/agent_rebate_list";
	}
    
    /**
     * 返利比例管理列表清单
     * @param gridPager
     * @return
     * @author:zyy
     * @time:2017年4月17日
     */
    @ResponseBody
    @RequestMapping("rebateList.html")
    public Object rebateList(String gridPager){
    	 Map<String, Object> parameters = null;
         // 映射Pager对象
         Pager pager = JSON.parseObject(gridPager, Pager.class);
         // 判断是否包含自定义参数
         parameters = pager.getParameters();
         if (parameters.size() < 0) {
             parameters.put("mid", null);
         } else if ("".equals(parameters.get("mid"))){
             parameters.put("mid", null);
         }
         // 设置分页，page里面包含了分页信息
         Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
         List<AgentRebatescale> list = agentRebatescaleService.getRebateByMidAllList(parameters);
         return getReturnPage(pager, page, list);
    }
    
    /**
     * 返利比例管理添加信息
     * @param rebatescale
     * @return
     * @author:zyy
     * @time:2017年4月17日
     */
    @ResponseBody
    @RequestMapping("insertRebateInfo.html")
    public Map<String,Object> insertRebateInfo(AgentRebatescale rebate){
    	Map<String,Object> result=new HashMap<String,Object>();
    	rebate.setPMktime(DateUtil.currentTimeToInt());;
    	int count=agentRebatescaleService.insertSelective(rebate);
    	if (count ==1){
    		result.put(CommonContants.SUCCESS, true);
    		result.put(CommonContants.MESSAGE, "添加成功！");
    	}else {
    		result.put(CommonContants.SUCCESS, false);
    		result.put(CommonContants.MESSAGE, "添加失败！");
    	}
    	return result;
    }
    
    /**
     * 根据mid删除信
     * @return
     * @author:zyy
     * @time:2017年4月17日
     */
   @ResponseBody
   @RequestMapping("deleteToId.html")
    public Map<String,Object> deleteToId(int id){
    	Map<String,Object> result=new HashMap<String,Object>();
    	int count=agentRebatescaleService.deleteById(id);
    	if (count ==1){
    		result.put(CommonContants.SUCCESS, true);
    		result.put(CommonContants.MESSAGE, "删除成功！");
    	}
    	return result;
    }
   
   /**
    * 根据mid查数据
    * @param mid
    * @param model
    * @return
    * @author:zyy
    * @time:2017年4月17日
    */
   @ResponseBody
   @RequestMapping("selectByid.html")
   public Map<String,Object> selectByid(Integer id,Model model){
   	Map<String,Object> result=new HashMap<String,Object>();
    AgentRebatescale agentRebates= agentRebatescaleService.getRebateById(id);
    result.put("data",agentRebates);
    result.put(CommonContants.SUCCESS, true);
     return result;
   }
   
   @ResponseBody
   @RequestMapping("updateRebateInfo.html")
   public Map<String,Object> updateRebateInfo(AgentRebatescale rebate){
   	Map<String,Object> result=new HashMap<String,Object>();
   	rebate.setPMktime(DateUtil.currentTimeToInt());
   	int count=agentRebatescaleService.updateByPrimaryKeySelective(rebate);
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
