package com.qs.sync.common;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qs.common.util.ID;
import com.qs.sync.constant.SyncContans;
import com.qs.sync.model.LogError;
import com.qs.sync.model.LogSuccess;
import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncOrganization;
import com.qs.sync.model.SyncUser;

public class SyncLogTool {

	private static final Log log = LogFactory.getLog(SyncLogTool.class);
	
	//线程池
    private static  ExecutorService logExecThread=Executors.newFixedThreadPool(20);  	
	
	/**
	 * 错误的操作日志
	 * @param record
	 * @param receiveSysCode 接收系统编码
	 * @param excepMessage
	 */
	public static LogError getErrorLog(SyncObject record,String receiveSysCode,String excepMessage,String modelClassName,String excType)
	{  
		JSONObject jsonObject = getJsonObject(record);
		LogError errorLog=new LogError();
		errorLog.setId(ID.generateUUID());
		errorLog.setSource(record.getFromSysName());
		errorLog.setSourceCode(record.getFromSysCode());
		errorLog.setReceiveCode(receiveSysCode);
		errorLog.setModuleCode(modelClassName);
		errorLog.setContent(jsonObject.toString());
		errorLog.setStatus("0");
		errorLog.setExcepContent(excepMessage);	
		if("0".equals(excType)){
			//异常类型 0：sql异常 1：mq异常 2 httpClient异常
			errorLog.setExcepContent(SyncContans.ExceptContent.SQL);	
			if(null!=excepMessage&&excepMessage.length()>1000){
				excepMessage=excepMessage.substring(0,1000);
			}
			errorLog.setRemark(excepMessage);
		}
		
		errorLog.setExcepType(excType);
		errorLog.setCreateTime(new Date());
	    return errorLog; 
	    
	}
	
	/**
	 * 成功操作日志(excepMessage有值是不处理的异常)
	 * @param record
	 * @param receiveSysCode接收系统编码
	 */
	public static LogSuccess getSucceLog(SyncObject record,String modelClassName,String excepMessage)
	{  
		JSONObject jsonObject = getJsonObject(record);
		LogSuccess log=new LogSuccess();
		log.setId(ID.generateUUID());
		log.setSource(record.getFromSysName());
		log.setSourceCode(record.getFromSysCode());
		log.setModuleCode(modelClassName);
		log.setContent(jsonObject.toString());
		if(StringUtils.isBlank(excepMessage)){
			log.setStatus("1");
		}else{
			log.setStatus("0");
		}
		log.setExcepContent(excepMessage);
		log.setRemark(excepMessage);
		log.setCreateTime(new Date());
	    return log; 
	    
	}
	



	private static JSONObject getJsonObject(SyncObject record) {
		  JSONObject jsonObject =null;
			if(record instanceof SyncUser){
				SyncUser user = (SyncUser)record;
				JsonConfig config = MyJsonConfig.getJsonConfig();
				jsonObject= JSONObject.fromObject(user,config);  
			}else if(record instanceof SyncOrganization){
				SyncOrganization org = (SyncOrganization)record;
				JsonConfig config = MyJsonConfig.getJsonConfig();
				jsonObject= JSONObject.fromObject(org,config);  
			}
		return jsonObject;
	}
	
	

}
