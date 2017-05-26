package com.qs.pub.sync.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.pub.sync.common.SyncLogTool;
import com.qs.pub.sync.service.DataCenterSyncService;
import com.qs.pub.sync.service.ICreateRoomService;
import com.qs.pub.sync.service.IPlayingService;
import com.qs.pub.sync.service.LogErrorService;
import com.qs.pub.sync.service.LogSuccessService;
import com.qs.sync.model.LogError;
import com.qs.sync.model.LogSuccess;
import com.qs.sync.model.SyncCreateRoom;
import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncPlaying;

/**
 * 同步数据服务实现
 */
@Service
public class DataCenterSyncServiceImpl implements DataCenterSyncService {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(DataCenterSyncServiceImpl.class);

	@Autowired
	private LogErrorService logErrorService;
	@Autowired
	private LogSuccessService logSuccessService;
	
	
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
	
	 

	

	public int syncPlaying(SyncPlaying syncPlaying) {
		   int result=0;
		   result = playingService.insert(syncPlaying);
		return result;
	}






	public int syncCreateRoom(SyncCreateRoom syncCreateRoom) {
		int result=0;
		try{
		   
		   result = createRoomService.insert(syncCreateRoom);
		   return result;
		}catch(Exception e){
			
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
 

}
