package com.qs.webside.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CacheConstan;
import com.qs.log.game.service.NoticeService;
import com.qs.webside.api.model.MailRequest;
import com.qs.webside.api.model.MemberInviteRequest;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;


@Controller
@Scope("prototype")
@RequestMapping("/api/")
public class NoticeController extends BaseController {
	
	 Logger log = Logger.getLogger(MemberController.class);  
	 
	@Autowired
	private NoticeService noticeService;


	/**
	 * 获取所有公告信息
	 * @param inviteRequest
	 * @return
	 */
	@RequestMapping(value = "notice/getAllNotice.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllNotice(MemberInviteRequest inviteRequest){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		return noticeService.getAllNotice(parameterMap);
	}
    /**
     * 获取邮件
     * @param inviteRequest
     * @return
     */
	@RequestMapping(value = "notice/getMails.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMails(MailRequest mailRequest){
		log.debug("notice/getMails.do===::"+mailRequest.toString());
		AccessToken token=ContextUtil.getAccessTokenInfo(mailRequest.getSesskey());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> listMails= noticeService.getMails(token.getMid());
		map.put("arr", listMails);
	    return this.getReturnData(map,AppConstants.Result.SUCCESS);
	}
	/**
	 * 阅读邮件
	 * @param inviteRequest
	 * @return
	 */
	@RequestMapping(value = "notice/checkMail.do", method = RequestMethod.POST)
	@ResponseBody
	public Object checkMail(MailRequest mailRequest){
		log.debug("notice/checkMail.do===::"+mailRequest.toString());
	    AccessToken token=ContextUtil.getAccessTokenInfo(mailRequest.getSesskey());
		Map<String, Object> map = new HashMap<String, Object>(); 
		int record=noticeService.saveReadMails(token.getMid(), mailRequest.getMailid(),CacheConstan.EMAIL_CACHE_KEY_PREFIX+token.getMid());
	
		 if(record==AppConstants.Result.SUCCESS){
			 return this.getReturnData(map,AppConstants.Result.SUCCESS);
		 }else{
			 return this.getReturnData(map,record);
		 }
	}
	

}
