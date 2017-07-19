package com.qs.webside.game.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.CommonUtils;
import com.qs.common.util.DateUtil;
import com.qs.common.util.SocketPacketUtil;
import com.qs.log.game.service.GameRecordService;
import com.qs.pub.game.mapper.MemberinviteMapper;
import com.qs.pub.game.model.Memberinvite;
import com.qs.webside.game.mapper.BaseParamMapper;
import com.qs.webside.game.mapper.MobileVersionMapper;
import com.qs.webside.game.model.BaseParam;
import com.qs.webside.game.model.Ipaddress;
import com.qs.webside.game.model.IpaddressUseLog;
import com.qs.webside.game.model.MobileVersion;
import com.qs.webside.game.service.GameService;
import com.qs.webside.member.model.Commongame;
import com.qs.webside.member.model.Memberagents;
import com.qs.webside.member.model.Memberfides;
import com.qs.webside.member.service.MemberService;
import com.qs.webside.member.service.PaymentService;
import com.qs.webside.game.mapper.IpaddressMapper;
import com.qs.webside.game.mapper.IpaddressUseLogMapper;


@Service("gameService")
public class GameServiceImpl implements GameService {
    Logger log = Logger.getLogger(GameServiceImpl.class);
	@Autowired
	private MobileVersionMapper mobileVersionMapper;
	
	@Autowired
	private MemberinviteMapper memberinviteMapper;
	
	@Autowired
	private BaseParamMapper baseParamMapper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private IpaddressMapper ipaddressMapper;
	
	@Autowired
	private IpaddressUseLogMapper ipaddressUseLogMapper;
	
	

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private GameRecordService gameRecordService;
	
    @Value("${game.goldhost}")
    private String goldHost;
    
    @Value("${game.goldport}")
    private int goldPort;
    

	@Override
	@Cacheable(value={CacheConstan.MOBLIE_VERSION_CACHE_STORE_NAME},key="#root.args[1]")
	public MobileVersion findLatestMobileVersion(Map<String, Object> map,String cacheKey) {
		return mobileVersionMapper.findLatestMobileVersion(map);
	}

	@Override
	public Memberinvite findByInviteCode(String code) {
		return memberinviteMapper.findByInviteCode(code);
	}

	@Override
	@Cacheable(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
	public BaseParam getBaseParamByCode(String code) {
		return baseParamMapper.getBaseParamByCode(code);
	}
	
	
	@Override
	@Cacheable(value={CacheConstan.BASEPARAM_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
	public String getBaseParamValueByCode(String code) {
		BaseParam  param=baseParamMapper.getBaseParamByCode(code);
		String paramValue="";
		if(null!=param){
			paramValue=param.getValue();
		}
		return paramValue;
	}
	
	/**
	 *	邀请绑定
	 * @param mid 被邀请者
	 * @param inviteMid 邀请者
	 */
	@Override
	public int saveInviteBind(int mid, int inviteMid) {
		// TODO Auto-generated method stub
		mid=Math.max(0, mid);
		inviteMid=Math.max(0, inviteMid);
		if(mid==0||inviteMid==0){
			return  AppConstants.Result.FAILURE;
		}
		if(mid==inviteMid){
			return AppConstants.Result.FAILURE_1003;
		}
		
		Memberfides midUser=memberService.findMemberfidesById(mid);
		if(null==midUser){
			return AppConstants.Result.FAILURE_1002;
		}
        //已经存在邀请者
		if(CommonUtils.checkIntegerNull(midUser.getInvite())>0){
			return AppConstants.Result.FAILURE_1002;
		}
		
		Memberfides inviteUser=memberService.findMemberfidesById(inviteMid);
		if(null==inviteUser){
			return AppConstants.Result.FAILURE_1005;
		}
		//非代理商不可被绑定
		 Memberagents inviteAgents=memberService.findMemberagentsByMid(inviteMid);
		 if(null==inviteAgents){
			 return AppConstants.Result.FAILURE_1006;
		 }
		//顶级代理商不能绑定人
		 Memberagents agents=memberService.findMemberagentsByMid(mid);
		 if(null!=agents&&agents.getParentId()==0){
			 return AppConstants.Result.FAILURE_1007;
		 }
		//绑定
		 Memberfides mUser=memberService.findMemberfidesById(mid);
		 if(null==mUser){
			 return AppConstants.Result.FAILURE_1004;
		 }
		 if(null!=mUser){
			 mUser.setInvite(inviteMid);
			 mUser.setBindtime(DateUtil.currentTimeToInt());
		 }
		 
		 int c=memberService.updateMemberfides(mUser);
		 //添加绑定人数
		paymentService.addAgentInviteMembers(mid);
		return AppConstants.Result.SUCCESS;
	}
    
	/**
	 * 更新金币日志
	 */
	@Override
	public Map<String, Object> updateGold(int mid, int gold, int goldLogType) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int cmd=1001;
		byte action = 0;
		if(Math.max(0, mid)==0){
			 map.put(CommonContants.RESULT,Boolean.FALSE);
			 map.put("errcode",AppConstants.Result.FAILURE);
			 log.debug("updateGold.errcode===::"+AppConstants.Result.FAILURE);
			 return  map;
		}
		if(Math.abs(gold) > 5000){//不能超过21亿
			 map.put(CommonContants.RESULT,Boolean.FALSE);
			 map.put("errcode",AppConstants.Result.FAILURE_1002);
			 log.debug("updateGold.errcode===::"+AppConstants.Result.FAILURE_1002);
			 return  map;
		}	
		Commongame goldCommongam=memberService.findCommongameById(mid);
		//不能减成负
		if(goldCommongam.getGold()+gold<0){
			 map.put(CommonContants.RESULT,Boolean.FALSE);
			 map.put("errcode",AppConstants.Result.FAILURE_1003);
			 log.debug("updateGold.errcode===::"+AppConstants.Result.FAILURE_1003);
			return  map;
		}
		
		int flag=0;
		//更新金币
		Commongame goldCom=new Commongame();
		goldCom.setMid(mid);
		Long totalGold=gold+goldCommongam.getGold();
		goldCom.setGold(totalGold);
		int r=memberService.updateCommongame(goldCom);
		//发通知给c++
		SocketPacketUtil socketUtil=new SocketPacketUtil(goldHost,goldPort);
		 Map<String, Object> jsonMsgMap = new HashMap<String, Object>();
		 jsonMsgMap.put("type", 1);
		 jsonMsgMap.put("msg", totalGold);
	   
	     String jsonMsg=JSON.toJSONString(jsonMsgMap);
	     if(null!=socketUtil.getSocket()){
		   boolean socketFlag=socketUtil.sendData(10008, mid, jsonMsg);
		   log.debug("socketFlag===::"+socketFlag);
	     }else{
	    	 log.error("updateGold socket is null===::"+mid);
	     }
		 socketUtil.close();
		 
		 
		 if(flag==0){
			 //更新金币日志
			gameRecordService.saveGoldLog(mid, gold, totalGold, (byte)goldLogType, action);
		 }
		
		 map.put(CommonContants.RESULT,Boolean.TRUE);
		 map.put("totalGold",totalGold);
		 
		 return map;
	}

	@Override
	@Cacheable(value={CacheConstan.IP_ADDRESS_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
	public Ipaddress findIpaddressByType(String type) {
		return ipaddressMapper.findIpaddressByType(type);
	}

	@Override
	public int saveIpaddressUseLog(IpaddressUseLog record) {
		return ipaddressUseLogMapper.insertSelective(record);
	}


	
	



}
