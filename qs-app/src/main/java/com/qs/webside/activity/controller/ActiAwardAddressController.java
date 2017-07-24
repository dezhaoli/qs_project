package com.qs.webside.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.Constants;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.webside.activity.model.ActiAwardAddress;
import com.qs.webside.activity.service.IActiAwardAddressService;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;


@Controller
@RequestMapping(value = "/api/activity/")
public class ActiAwardAddressController extends BaseController{

	Logger log = Logger.getLogger(ActiAwardAddressController.class);
	
	@Resource
	private IActiAwardAddressService actiAwardAddressService ;

	/**
	 * 获取当前用户地址信息
	 * @param model
	 * @param request
	 * @param baseRequest
	 * @return
	 * @author:zyy
	 * @time:2017年6月7日
	 */
	@ResponseBody
	@RequestMapping("getAddressInfo.do")
	public Object getActivityCenterData(Model model, HttpServletRequest request, BaseRequest baseRequest) {
		AccessToken token=ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
		ActiAwardAddress  address = actiAwardAddressService.selectByMidKey(token.getMid());
		return this.getReturnData(address, AppConstants.Result.SUCCESS);
	}
	
	/**
	 * 更新活动用户发货信息表
	 * @param model
	 * @param request
	 * @param baseRequest
	 * @return
	 * @author:zyy
	 * @time:2017年6月7日
	 */
	@ResponseBody
	@RequestMapping("updateAddress.do")
	public Object updateAddress(Model model, HttpServletRequest req, BaseRequest baseRequest){
		Map<String,Object> result=new HashMap<>();
		int count=0;
		ActiAwardAddress address=new ActiAwardAddress();
		address.setName(req.getParameter("name"));
		address.setQq(req.getParameter("qq"));
		address.setWechat(req.getParameter("wechat"));
		address.setPhone(req.getParameter("phone"));
		address.setEmail(req.getParameter("email"));
		address.setAddress(req.getParameter("address"));
		AccessToken token=ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
		ActiAwardAddress  addr = actiAwardAddressService.selectByMidKey(token.getMid());
		if(addr==null) {
			address.setMid(token.getMid());
			count=actiAwardAddressService.insertSelective(address);
		}else {
			address.setId(Integer.valueOf(req.getParameter("id")));
			count=actiAwardAddressService.updateByPrimaryKeySelective(address);
		}
		if (count>0){
			result.put(CommonContants.SUCCESS,true);
			result.put(CommonContants.MESSAGE,"修改成功！");
		}else {
			result.put(CommonContants.SUCCESS,false);
			result.put(CommonContants.MESSAGE,"修改失败！");
		}
		return this.getReturnData(result, AppConstants.Result.SUCCESS);
	}
	
}
