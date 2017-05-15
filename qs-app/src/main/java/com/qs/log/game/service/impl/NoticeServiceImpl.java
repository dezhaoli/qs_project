package com.qs.log.game.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.qs.common.constant.AppConstants;
import com.qs.common.constant.CacheConstan;
import com.qs.log.game.mapper.MailsMapper;
import com.qs.log.game.mapper.MailsUserMapper;
import com.qs.log.game.mapper.NoticeMapper;
import com.qs.log.game.model.Mails;
import com.qs.log.game.model.MailsUser;
import com.qs.log.game.model.Notice;
import com.qs.log.game.service.GameRecordService;
import com.qs.log.game.service.NoticeService;
import com.qs.webside.game.service.GameService;


@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private MailsMapper mailsMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private MailsUserMapper mailsUserMapper;
	@Autowired
	private GameService gameService;
	
	@Autowired
	private GameRecordService gameRecordService;
	
	  
    @Value("${game.gametype}")
    private int gameType;


	@Override
	public List<Notice>  getAllNotice(Map<String, Object> parameter) {
		return noticeMapper.queryListByPage(parameter); 
	}


	@Override
	@Cacheable(value={CacheConstan.EMAIL_CACHE_STORE_NAME},key="#root.methodName+':'+#root.args[0]")
	public List<Object> getMails(int mid) {
		List<Object> returnList=new ArrayList<Object>();
		Map<String, Object> parameter=new HashMap<String, Object>();
		parameter.put("nowDataTime", new Date(System.currentTimeMillis()));
		List<Mails> mailsList=mailsMapper.queryListByPage(parameter);
		if(null!=mailsList&&mailsList.size()>0){
		  for(Mails mail:mailsList){
			  
				MailsUser mailsUser=new MailsUser();
				mailsUser.setMid(mid);
				mailsUser.setMailid(mail.getId());
				MailsUser mailsUserRecord=mailsUserMapper.findByCondition(mailsUser);
				//暂时没有考虑游戏类型
			    if(null!=mailsUserRecord){
			       //此邮件已发放
			    	  List<Object> mailList=new ArrayList<Object>();
				      mailList.add(mail.getId());
				      mailList.add(mail.getTitle());
				      mailList.add(mail.getContent());
				      mailList.add(mailsUserRecord.getIsread());
				      mailList.add(mail.getImportant());
				      returnList.add(mailList);
			    }else{
			    	 boolean ishit=false;
			    	 if(mail.getTarget()==1){
			    		 //全部用户
			    		 ishit=true;
			    	 }else  if(mail.getTarget()==2){
			    		  String mids=mail.getMids()+",";
			    		  String curMid=mid+",";
			    		  if(mids.contains(curMid)){
			    			  ishit=true;
			    		  }
			    		 
			    	 }else{
			    		 continue;
			    	 }
			    	 //命中
			    	 if(ishit){
			    		 if(!StringUtils.isBlank(mail.getFujian())){
			    		   Map maps = (Map)JSON.parse(mail.getFujian());
			               List<Map> mapList = JSON.parseArray(maps.get("gold") + "", Map.class);
			               if(null!=mapList){
			                for (Map map : mapList) {
			            	   String gold=(String)map.get("number");
			            	   int goldNum=0;
			            	   if(!StringUtils.isBlank(gold)){
			            		   goldNum=Integer.parseInt(gold);
			            	   }
			            	    if(goldNum>0){
			            			byte action = 0;
			            	    	 //更新金币日志
			            			 gameService.updateGold(mid, goldNum, AppConstants.GoldLogSourceType.EMAIL_SEND);
			
			            	   }
			               }
			              }
			               
			          
			    	}
			    		 
						MailsUser saveMUser = new MailsUser();
						saveMUser.setMailid(mail.getId());
						saveMUser.setMid(mid);
						saveMUser.setIsread((byte) 0);
						mailsUserMapper.insertSelective(saveMUser);
						
						List<Object> mailList = new ArrayList<Object>();
						mailList.add(mail.getId());
						mailList.add(mail.getTitle());
						mailList.add(mail.getContent());
						mailList.add(0);
						mailList.add(mail.getImportant());
						returnList.add(mailList);
			    	 
			      }
			    }
			
		    	
		  }
		}
		
		return returnList;
	}


	@Override
	@CacheEvict(value={CacheConstan.EMAIL_CACHE_STORE_NAME},key="#root.args[2]")
	public int saveReadMails(int mid,int emailid,String cacheKey) {
		int result=0;
		MailsUser record=new MailsUser();
		record.setMid(mid);
		record.setMailid(emailid);

		MailsUser mailsUser=mailsUserMapper.findByCondition(record);
		if(null==mailsUser){
			result=AppConstants.Result.FAILURE_1002;
		}
		int c=mailsUserMapper.updateByCondition(record);
		if(c>0){
			result=AppConstants.Result.SUCCESS;
		}
		return result;
	}
	
	



}
