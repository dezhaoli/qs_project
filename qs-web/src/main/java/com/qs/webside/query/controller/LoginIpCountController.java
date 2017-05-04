/*
 * 文件名：LoginIpCountController.java	 
 * 时     间：下午4:23:41
 * 作     者：wangzhen       
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
package com.qs.webside.query.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.DayLoginUserIp;
import com.qs.log.game.service.ILoginIpCountRecordService;

/** 
 * @ClassName: LoginIpCountController 
 * @描述: 登陆ip统计控制层
 * @author wangzhen
 * @date 2017年5月2日 下午4:23:41 
 */
@Controller
@RequestMapping(value = "/query/")
public class LoginIpCountController extends BaseController 
{
	@Resource
	private ILoginIpCountRecordService loginIpCountRecordService;
	
	/**
	 * 
	 * @标题: gameRecordUi 
	 * @描述:  跳转到登陆ip统计页面
	 *
	 * @参数信息
	 *    @param model
	 *    @param request
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	 @RequestMapping(value = "loginIpCountUi.html", method = RequestMethod.GET)
	    public String gameRecordUi(Model model, HttpServletRequest request) {
	        PageUtil page = super.getPage(request);
	        model.addAttribute("page", page);
	        return "WEB-INF/view/web/query/login_user_id_list";
	    }
	 /**
	  * 
	  * @标题: orderCount 
	  * @描述:  登陆ip统计查询接口
	  *
	  * @参数信息
	  *    @param gridPager
	  *    @return
	  *    @throws Exception
	  *
	  * @返回类型 Object
	  * @开发者 wangzhen
	  * @可能抛出异常
	  */
	   @RequestMapping("loginIpCount.html")
	    @ResponseBody
	    public Object orderCount(String gridPager) throws Exception {
	        Map<String, Object> parameters = null;
	        // 映射Pager对象
	        Pager pager = JSON.parseObject(gridPager, Pager.class);
	        // 判断是否包含自定义参数
	        parameters = pager.getParameters();
	        if (parameters.size() < 0) {
	            parameters.put("name", null);
	        }
	        // 设置分页，page里面包含了分页信息
	        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
	        List<DayLoginUserIp> list = loginIpCountRecordService.queryListByPage(parameters);
	        return getReturnPage(pager, page, list);
	    }

	
}
