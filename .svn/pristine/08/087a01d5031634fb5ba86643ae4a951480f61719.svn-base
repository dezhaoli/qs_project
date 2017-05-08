package com.qs.pub.pay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Around;
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
import com.qs.common.util.DateUtils;
import com.qs.common.util.PageUtil;
import com.qs.pub.pay.model.PayLog;
import com.qs.pub.pay.model.PayWhiteList;
import com.qs.pub.pay.service.IPayWhiteListService;
import com.qs.webside.shiro.ShiroAuthenticationManager;

/**
 * 白名单控制层
 * @author zyy
 *
 */
@Controller
@RequestMapping("/payWhite")
public class PayWhiteController extends BaseController{

	@Autowired
	private IPayWhiteListService payWhiteListService;
	
	@RequestMapping("getPayWhiteInfoList.html")
	public String getPayWhiteInfoList(Model model,HttpServletRequest request){
		 PageUtil page = new PageUtil(request);
		 if (request.getParameterMap().containsKey("page")) {
	            page.setPageSize(Integer.valueOf(request.getParameter("rows")));
	            page.setPageNum(Integer.valueOf(request.getParameter("page")));
	            page.setOrderByColumn(request.getParameter("sidx"));
	            page.setOrderByType(request.getParameter("sord"));
	        }
	     model.addAttribute("page", page);
	     return "/WEB-INF/view/web/agent_payWhite_list";
	}
	
	/**
	 * 获取列表清单
	 * @param gridPager
	 * @param response
	 * @return
	 * @author:zyy
	 * @time:2017年4月14日
	 */
	@ResponseBody
	@RequestMapping("getPayWhiteInfoListUi.html")
	public Object getPayWhiteInfoListUi(String gridPager,HttpServletResponse response){
		 Map<String, Object> parameters = new HashMap<>();

		 // 映射Pager对象
		 Pager pager = JSON.parseObject(gridPager, Pager.class);

		 parameters = pager.getParameters();

		 Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
		 List<PayWhiteList> list =payWhiteListService.selectPayWhiteListAll(parameters);
		 return getReturnPage(pager, page, list);
	}
	
 
	/**
	 * 根据id删除白名单信息
	 * @param id
	 * @return
	 * @author:zyy
	 * @time:2017年4月14日
	 */
	@ResponseBody
	@RequestMapping("deleteToMid.html")
	public Map<String,Object> deleteToMid(int id){
		Map<String,Object> result =new HashMap<>();
		int returnCount=payWhiteListService.deleteByPrimaryKey(id);
		if (returnCount==1){
			result.put(CommonContants.SUCCESS, true);
			result.put(CommonContants.MESSAGE, "删除成功！");
		}
		return result;
	}
	

	/**
	 * 添加白名单按钮
	 * @return
	 * @author:zyy
	 * @time:2017年4月14日
	 */
	@RequestMapping("addPayWhiteInfo.html")
	public String addPayWhiteInfo(HttpServletRequest request,Model model){
		 PageUtil page = new PageUtil(request);
		 model.addAttribute("page", page);
		 return "/WEB-INF/view/web/agent_payWhiteAdd_from";
	}
	
	/**
	 * 添加白名单对象入库
	 * @param mid
	 * @param gameType
	 * @return
	 * @author:zyy
	 * @time:2017年4月14日
	 */
	@ResponseBody
	@RequestMapping("insertSelectiveInfo.html")
	public Map<String,Object> insertSelectiveInfo(PayWhiteList payWhiteList){
		 Map<String,Object> result=new HashMap<>();
		/* if (StringUtils.isBlank(mid)){
			 result.put(CommonContants.SUCCESS, false);
			 result.put(CommonContants.MESSAGE, "mid不能为空！");
			 return result;
		 }
		 if (StringUtils.isBlank(gameType)){
			 result.put(CommonContants.SUCCESS, false);
			 result.put(CommonContants.MESSAGE, "游戏类型不能为空！");
			 return result;
		 }
		 if (StringUtils.isBlank(money)){
			 result.put(CommonContants.SUCCESS, false);
			 result.put(CommonContants.MESSAGE, "金额不能 为空！");
			 return result;
		 }*/
		 
		//PayWhiteList payWhiteList=new PayWhiteList();
		Long userId=ShiroAuthenticationManager.getUserId();

		//payWhiteList.setMid(Integer.parseInt(mid));
		//payWhiteList.setGameType(Integer.parseInt(gameType));
		payWhiteList.setCreateTime(DateUtil.getNowDate());
		payWhiteList.setCreatorId(userId.intValue());
		//payWhiteList.setMoney(Integer.parseInt(money));
		
		int count=payWhiteListService.insertSelective(payWhiteList);
		if (count ==1) {
			result.put(CommonContants.SUCCESS, true);
			result.put(CommonContants.MESSAGE, "添加成功！");
		}
		return result;
	}
}
