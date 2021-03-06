package com.qs.webside.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CommonContants;
import com.qs.common.ip2region.DbSearcher;
import com.qs.common.util.CommonUtils;
import com.qs.common.util.DateUtil;
import com.qs.common.util.IpUtil;
import com.qs.log.game.model.GameRecordShare;
import com.qs.log.game.model.RoomRecord;
import com.qs.log.game.service.GameRecordService;
import com.qs.pub.game.model.Memberinvite;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.api.model.GameRecordRequest;
import com.qs.webside.api.model.GameRecordShareRequest;
import com.qs.webside.api.model.HonorDetailRequest;
import com.qs.webside.api.model.MemberInviteRequest;
import com.qs.webside.api.model.RoomRecordRequest;
import com.qs.webside.game.service.GameService;
import com.qs.webside.member.model.AgentClubMember;
import com.qs.webside.member.model.Game;
import com.qs.webside.member.model.Memberfides;
import com.qs.webside.member.model.Members;
import com.qs.webside.member.service.AgentClubMemberService;
import com.qs.webside.member.service.MemberService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;

@Controller
@Scope("prototype")
@RequestMapping("/api/")
public class GameController extends BaseController {
	
	Logger log = Logger.getLogger(GameController.class);  


    @Value("${game.gametype}")
    private int gameType;
	
	@Autowired
	private GameRecordService gameRecordService;
	@Autowired
	private DbSearcher ipSearcher;
	@Autowired
	private MemberService memberService;
	@Autowired
	private GameService gameService;
	@Autowired
  	private AgentClubMemberService agentClubMemberService ;
	

	/***
	 * 获取牌局回放数据
	 * @param roomRecordRequest
	 * @return
	 */
	@RequestMapping(value = "getPaiJuData.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getPaiJuData(RoomRecordRequest roomRecordRequest){
		log.debug("getPaiJuData.do===:"+roomRecordRequest.toString());
		
		RoomRecord paraRecord=new RoomRecord();
		paraRecord.setUuid(roomRecordRequest.getUuid());
		if (20 != gameType) paraRecord.setType(gameType);//江西麻将不添加类型条件
		Map<String, Object> map = new HashMap<String, Object>();
		RoomRecord roomRecord=gameRecordService.getPaiJuData(paraRecord);
	    String infoJson="";
	    if(null!=roomRecord){
	    	infoJson=roomRecord.getInfo();
	    }
	    
	    map.put("arr", infoJson);
	    
		 return this.getReturnData(map,AppConstants.Result.SUCCESS);
	}

    /**
     * 获取分享的牌局的数据
     * @param roomRecordRequest
     * @return
     */
	@RequestMapping(value = "getShareGameRecord.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getShareGameRecord(GameRecordShareRequest shareRequest){
		log.debug("getShareGameRecord.do=="+shareRequest.toString());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> shareMap=gameRecordService.getShareGameRecord(shareRequest.getId());
		if(null!=shareMap){
		  resultMap.put("arr", shareMap);
	      return this.getReturnData(resultMap,AppConstants.Result.SUCCESS);
		}else{
		  return this.getReturnData(resultMap,AppConstants.Result.FAILURE);
		}
	}
	
	/**
	 * 分享牌局(创建并返回分享ID)
	 * @param shareRequest
	 * @return
	 */
	@RequestMapping(value = "shareGameRecord.do", method = RequestMethod.POST)
	@ResponseBody
	public Object shareGameRecord(GameRecordShareRequest shareRequest){
		log.debug("shareGameRecord.do==::"+shareRequest.toString());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AccessToken token=ContextUtil.getAccessTokenInfo(shareRequest.getSesskey());
		GameRecordShare record=new GameRecordShare();
		record.setMid(token.getMid());
		record.setRecordid(shareRequest.getRecordid());
		
		int c=gameRecordService.addGameRecordShare(record);
		int code=record.getSid();
		resultMap.put("code", code);
	    return this.getReturnData(resultMap,AppConstants.Result.SUCCESS);
	}
	
	
	/**
     * 获取详细战绩
     * @param roomRecordRequest
     * @return
     */
	@RequestMapping(value = "getHonorDetail.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getHonorDetail(HonorDetailRequest honorDetailRequest){
		log.debug("getHonorDetail.do==="+honorDetailRequest.toString());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AccessToken token=ContextUtil.getAccessTokenInfo(honorDetailRequest.getSesskey());
		 List<Object>  detailMap=gameRecordService.getHonorDetail(token.getMid(),honorDetailRequest.getIds());
		resultMap.put("arr", detailMap);
	   return this.getReturnData(resultMap,AppConstants.Result.SUCCESS);
	}
	
	/**
	 * 获取当前IP
	 * @param baseRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getIP.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getIP(BaseRequest baseRequest,HttpServletRequest request){
		log.debug("getIP.do===::"+baseRequest.toString());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String ip = IpUtil.getIpAddr(request);
		String region="";
		try {
			region = ipSearcher.memorySearch(ip).getRegion();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] regions = StringUtils.split(region, '|');
		resultMap.put("ip",ip);
		resultMap.put("city",regions[2]+regions[3]+regions[4]);
	   return this.getReturnData(resultMap,AppConstants.Result.SUCCESS);
	}
	
	/**
	 * 获取我的战绩
	 * @param baseRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getGameRecord.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getGameRecord(GameRecordRequest gameRecordRequest ,HttpServletRequest request){
		log.debug("getGameRecord.do===::"+gameRecordRequest.toString());
		AccessToken token=ContextUtil.getAccessTokenInfo(gameRecordRequest.getSesskey());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		int pageNum=gameRecordRequest.getPage();
		if(pageNum<1){
			pageNum=1;
		}
        int pageSize=10;
		int startRow=(pageNum-1)*pageSize;
		parameter.put("pageSize",pageSize);
		parameter.put("startRow",startRow);
		parameter.put("mid",token.getMid());
		parameter.put("type", gameRecordRequest.getType());//添加玩法条件.

		List<Object> listResult=gameRecordService.queryGameRecordListByPage(parameter);
		Game game=memberService.findGameById(token.getMid());
		resultMap.put("arr", listResult);
		if(null!=game){
			resultMap.put("jifen",game.getJifen());
		}else{
		  resultMap.put("jifen",0);
		}
	
	   return this.getReturnData(resultMap,AppConstants.Result.SUCCESS);
	}
	
	/***
	 * 绑定领奖(绑定邀请码)
	 * @param roomRecordRequest
	 * @return
	 */
	@RequestMapping(value = "bindInviteCode.do", method = RequestMethod.POST)
	@ResponseBody
	public Object bindInviteCode(MemberInviteRequest inviteRequest){
		log.debug("bindInviteCode.do===::"+inviteRequest.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		 AccessToken token=ContextUtil.getAccessTokenInfo(inviteRequest.getSesskey());
		Memberinvite  memberinvite= gameService.findByInviteCode(inviteRequest.getCode());
		if(null==memberinvite){
			 return this.getReturnData(map,AppConstants.Result.FAILURE_1102);
		}
		Members inviteMember=memberService.findMembersBySitemid(memberinvite.getSitemid());
		
		if(null==inviteMember){
			 return this.getReturnData(map,AppConstants.Result.FAILURE_1102);
		}
		Memberfides mUser=memberService.findMemberfidesById(token.getMid());
	   int result=gameService.saveInviteBind(token.getMid(), inviteMember.getMid());
		if(AppConstants.Result.SUCCESS!=result){
			 return this.getReturnData(map,result);
		}
		
		int bindSendGold=CommonUtils.checkIntegerNull(gameService.getBaseParamValueByCode(AppConstants.BaseParam.BINDSEND_GOLD_CODE));
		if(CommonUtils.checkIntegerNull(mUser.getBindtime())>0){
			bindSendGold=0;
		}
		Map<String, Object> goldMap=gameService.updateGold(token.getMid(),bindSendGold,AppConstants.GoldLogSourceType.BIND_AWARD);
		List<Object> resultList=new ArrayList<Object>();
		Memberfides aGent=memberService.findMemberfidesById(inviteMember.getMid());
		boolean resultFlag=(boolean)goldMap.get(CommonContants.RESULT);
		if(!resultFlag){
		    log.debug("bindInviteCode.do=errcode===::"+goldMap.get("errcode"));
			return this.getReturnData(map,(int)goldMap.get("errcode"));
		}
		resultList.add(goldMap.get("totalGold"));
		resultList.add(bindSendGold);
		resultList.add(inviteMember.getMid());
		resultList.add(aGent.getName());		
		
		map.put("arr",resultList);
		
		/*
		AgentClubMember agentClubMember=new AgentClubMember();
		agentClubMember.setCmid(inviteMember.getMid());
		agentClubMember.setMid(token.getMid());
		agentClubMember.setCreateTime(DateUtil.getNowDate());
		agentClubMemberService.insertSelective(agentClubMember);*/
	
		 return this.getReturnData(map,AppConstants.Result.SUCCESS);
	}
	
	
}
