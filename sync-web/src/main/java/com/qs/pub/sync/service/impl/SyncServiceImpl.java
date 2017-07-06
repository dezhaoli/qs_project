package com.qs.pub.sync.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.common.ip2region.DbSearcher;
import com.qs.common.util.DateUtil;
import com.qs.pub.sync.common.SyncLogTool;
import com.qs.pub.sync.service.ICreateRoomService;
import com.qs.pub.sync.service.IPlayingService;
import com.qs.pub.sync.service.ISyncUserKeepService;
import com.qs.pub.sync.service.IUserLoginLogService;
import com.qs.pub.sync.service.LogErrorService;
import com.qs.pub.sync.service.LogSuccessService;
import com.qs.pub.sync.service.SyncService;
import com.qs.sync.model.LogError;
import com.qs.sync.model.LogSuccess;
import com.qs.sync.model.SyncCreateRoom;
import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncPlaying;
import com.qs.sync.model.SyncUserKeep;
import com.qs.sync.model.SyncUserLoginLog;

/**
 * 同步数据服务实现
 */
@Service
public class SyncServiceImpl implements SyncService {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(SyncServiceImpl.class);

	@Autowired
	private LogErrorService logErrorService;
	@Autowired
	private LogSuccessService logSuccessService;
	@Autowired
	private IUserLoginLogService userLoginLogService;
	
	@Autowired
	private ISyncUserKeepService syncUserKeepService;
	
	
	/**
	 * 在线用户业务层
	 */
	@Resource
	private IPlayingService playingService;
	/**
	 * 创建房间业务层
	 */
	@Resource
	private ICreateRoomService createRoomService;
	
	@Autowired
	private DbSearcher ipSearcher;
	
	 

	

	public int syncPlaying(SyncPlaying syncPlaying)
	{
		
		logger.debug("syncPlaying===========::"+syncPlaying.getIp());
		
		String ip=syncPlaying.getIp();
	
		
		int result = 0;
		try{
			
			if(!StringUtils.isBlank(ip)){
				 String region="";
			     region = ipSearcher.memorySearch(ip).getRegion();
			     String[] regions = StringUtils.split(region, '|');
			     syncPlaying.setProvince(regions[2]);
			     syncPlaying.setCity(regions[3]);
			     syncPlaying.setRegion(regions[2]+regions[3]);
			     

			}
			
			result = playingService.insert(syncPlaying);
			result = playingService.insertPlayingDistinct(syncPlaying);
		} catch (Exception e)
		{
			this.saveErrorLog(syncPlaying, SyncPlaying.class.getSimpleName(), "", "","0");
			e.printStackTrace();
		}
		return result;
	}
	public int syncPlayingLog(SyncPlaying syncPlaying)
	{
		int result = 0;
		try{
			result = playingService.insert(syncPlaying);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			return result;
	}






	public int syncCreateRoom(SyncCreateRoom syncCreateRoom) {
		
		int result=0;
		try{
		   result = createRoomService.insert(syncCreateRoom);
		   return result;
		}catch(Exception e){
			this.saveErrorLog(syncCreateRoom, SyncCreateRoom.class.getSimpleName(), "", "", "0");
			e.printStackTrace();
		}
		return result;
	}
	
	public int syncCreateRoomLog(SyncCreateRoom syncCreateRoom) {
		int result=0;
		try{
		   result = createRoomService.insert(syncCreateRoom);
		   return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}



	
/*	private int saveUserSyncLog(SyncUser syncUser,String sysCode,String resultStr,boolean isSaveLog) {
		 int result=0;
		 logger.info("resultStr======::"+resultStr);
		 if(resultStr.contains("result\":true")){
			  result=1; 
			  //保存成功日志
			  this.saveSucceLog(syncUser,SyncUser.class.getSimpleName());
		  }else if(resultStr.contains(":false")){
			  //保存sql异常错误日志
			  if(isSaveLog){
			  this.saveErrorLog(syncUser,SyncUser.class.getSimpleName(),sysCode,resultStr,SyncContans.ExceptCode.SQL);
			  }
		  }else {
			   //保存httpclient异常错误日志
			  if(isSaveLog){
		  	   this.saveErrorLog(syncUser,SyncUser.class.getSimpleName(),sysCode,SyncContans.ExceptContent.HTTPCLIENT,SyncContans.ExceptCode.HTTPCLIENT);
			  }
		 }
		return result;
	}
	*/
/*
	private int saveOrgSyncLog(SyncOrganization org,
			String sysCode, String resultStr,boolean isSaveLog) {
		    int result=0;
		    logger.info("resultStr======::"+resultStr);
		    if(resultStr.contains("result\":true")){
			  result=1; 
			//保存成功日志
			  this.saveSucceLog(org,SyncOrganization.class.getSimpleName());
		  }else if(resultStr.contains(":false")){
			  //保存sql异常错误日志
			  if(isSaveLog){
			  this.saveErrorLog(org,SyncOrganization.class.getSimpleName(),sysCode,resultStr,SyncContans.ExceptCode.SQL);
			  }
		  }else {
			  //保存httpclient异常错误日志
			  if(isSaveLog){
		  	   this.saveErrorLog(org,SyncOrganization.class.getSimpleName(),sysCode,SyncContans.ExceptContent.HTTPCLIENT,SyncContans.ExceptCode.HTTPCLIENT);
		     }
		 }
		return result;
	}
	*/
 /**
  * 操作成功的日志
  * @param record
  * @param modelClassName
  */
	public void saveSucceLog(SyncObject record,String modelClassName){
		LogSuccess log=SyncLogTool.getSucceLog(record,modelClassName,null);
		//设置调用方法
		log.setExtend1(record.getFromSysMethod());
		log.setExtend2(getCallerMethod());
		logSuccessService.save(log);
	}
	
	/**
	 * 错误日志
	 * @param record
	 * @param modelClassName
	 * @param receiveSysCode
	 * @param excepMessage
	 */
	public void saveErrorLog(SyncObject record,String modelClassName,String receiveSysCode,String excepMessage,String excType){  
		LogError log=SyncLogTool.getErrorLog(record,receiveSysCode,excepMessage,modelClassName,excType);
		//设置调用方法
		log.setExtend1(record.getFromSysMethod());
		log.setExtend2(getCallerMethod());
		logErrorService.save(log);
	}
	
	/**
	 * 获取上层调用方法信息(即调用数据同步的方法,便于错误定位)
	 * @return
	 */
	protected static String getCallerMethod(){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
		return ste.getClassName() + "." + ste.getMethodName() + "(line" + ste.getLineNumber() + ")";
	}
	
	/**
	 * app  用户登录日志信息同步
	 */
	@Override
	public int addSyncUserLoginLog(SyncUserLoginLog syncUserLoginLog)
	{
		String ip = syncUserLoginLog.getIp();
		int result=0;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			if(!StringUtils.isBlank(ip)){
				 String region="";
			     region = ipSearcher.memorySearch(ip).getRegion();
			     String[] regions = StringUtils.split(region, '|');
			     syncUserLoginLog.setProvince(regions[2]);
			     syncUserLoginLog.setCity(regions[3]);
			     syncUserLoginLog.setRegion(regions[2]+regions[3]);
			}
			//保存登录日志
		   result = userLoginLogService.insert(syncUserLoginLog);
		   
		   /////////////////留存用户统计开始/////////////////
		   Date nowDate = new Date();
		   String currDateStr = DateUtil.fromatDateToStr(nowDate, "yyyyMMdd");
		   //获取登录用户的注册时间int字符串
		   String mtimeInt = syncUserLoginLog.getMtime();
		   String mtimeDateStr = DateUtil.intToDateStr(mtimeInt, "yyyyMMdd");
		   
		   SyncUserKeep syncUserKeep = new SyncUserKeep();
		   syncUserKeep.setAppId(syncUserLoginLog.getAppId());
		   syncUserKeep.setUserId(syncUserLoginLog.getMid());
		   syncUserKeep.setCreateDate(StringUtils.isBlank(mtimeDateStr)?null:sdf.parse(mtimeDateStr));
		   syncUserKeep.setExtend1(DateUtil.fromatDateToStr(nowDate, "yyyy-MM-dd HH:mm:ss"));
		   result = addSyncUserKeep(syncUserKeep);
			   
			   
				   	//获取上次登录时间int类型字符串
					String lgtimeInt = syncUserLoginLog.getLgtm();
					String lgtimeStr = DateUtil.intToDateStr(lgtimeInt, "yyyyMMdd");
						//如果用户上次登录时间等于当天日期则该用户不是第一次登录
						if(!currDateStr.equals(lgtimeStr) && !StringUtils.isBlank(mtimeDateStr) ){
							syncUserKeep.setExtend1(DateUtil.fromatDateToStr(nowDate, "yyyy-MM-dd HH:mm:ss"));
							Calendar calst = Calendar.getInstance();;
					        Calendar caled = Calendar.getInstance();
					        calst.setTime(sdf.parse(mtimeDateStr));
					        caled.setTime(sdf.parse(currDateStr));
					         //设置时间为0时   
					         calst.set(Calendar.HOUR_OF_DAY, 0);   
					         calst.set(Calendar.MINUTE, 0);   
					         calst.set(Calendar.SECOND, 0);   
					         caled.set(Calendar.HOUR_OF_DAY, 0);   
					         caled.set(Calendar.MINUTE, 0);   
					         caled.set(Calendar.SECOND, 0);   
					        //得到两个日期相差的天数   
					         int days = ((int)(caled.getTime().getTime()/1000)-(int)(calst.getTime().getTime()/1000))/3600/24; 
					         boolean ifUpdate = true;
					         if(days == 1){
					        	 syncUserKeep.setOne(1);
					         }else if(days == 2){
					        	 syncUserKeep.setTwo(1);
					         }else if(days == 3){
					        	 syncUserKeep.setThree(1);
					         }else if(days == 4){
					        	 syncUserKeep.setFour(1);
					         }else if(days == 5){
					        	 syncUserKeep.setFive(1);
					         }else if(days == 6){
					        	 syncUserKeep.setSix(1);
					         }else if(days == 7){
					        	 syncUserKeep.setSeven(1);
					         }else if(7 < days && days <= 15){
						         syncUserKeep.setFifteen(1);
					         }else if(15 < days && days <= 30){
					        	 syncUserKeep.setThirty(1); 
					         }else{
					        	 ifUpdate = false;
					         }
							if(ifUpdate){
								result = syncUserKeepService.update(syncUserKeep);
							}
						}
		   
		   return result;
		}catch(Exception e){
			this.saveErrorLog(syncUserLoginLog, SyncUserLoginLog.class.getSimpleName(), "", "", "0");
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @标题: syncUserLoginLogError 
	 * @描述:  用户登录 同步失败信息，重新保存
	 *
	 * @参数信息
	 *    @param syncUserLoginLog
	 *    @return
	 *
	 * @返回类型 int
	 * @开发者 wangzhen
	 * @可能抛出异常
	 */
	public int syncUserLoginLogError(SyncUserLoginLog syncUserLoginLog) {
		int result=0;
		try{
		   result = userLoginLogService.insert(syncUserLoginLog);
		   return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 创建用户同步
	 */
	@Override
	public int addSyncUserKeep(SyncUserKeep syncUserKeep)
	{
		int result=0;
		try{
		   result = syncUserKeepService.insert(syncUserKeep);
		   return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
 

}
