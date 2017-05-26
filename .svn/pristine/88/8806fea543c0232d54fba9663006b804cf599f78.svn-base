package com.qs.pub.sync.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.common.util.HttpClientUtil;
import com.qs.pub.sync.common.DataSyncUrl;
import com.qs.pub.sync.common.MyJsonConfig;
import com.qs.pub.sync.common.SyncLogTool;
import com.qs.pub.sync.service.DataSyncService;
import com.qs.pub.sync.service.LogErrorService;
import com.qs.pub.sync.service.LogSuccessService;
import com.qs.sync.model.LogError;
import com.qs.sync.model.LogSuccess;
import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncOrganization;
import com.qs.sync.model.SyncUser;
/**
 * 同步数据服务实现
 */
@Service
public class DataSyncServiceImpl implements DataSyncService {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(DataSyncServiceImpl.class);

	@Autowired
	private LogErrorService logErrorService;
	@Autowired
	private LogSuccessService logSuccessService;
	
	 

	

	public int syncUser(SyncUser syncUser,boolean isSaveLog) {
		  List<String> syncSysCodeList=DataSyncUrl.getSyncSysCodeList();
		   int result=0;
		   if(null!=syncSysCodeList&&syncSysCodeList.size()>0){
			   for(String sysCode:syncSysCodeList){
				   
			    result = httpSyncUser(syncUser, sysCode,isSaveLog);
				   
			   }
		   }
		
		return result;
	}



	public int httpSyncUser(SyncUser syncUser,String sysCode,boolean isSaveLog) {
		 Map<String,String> syncSysUrlMap=DataSyncUrl.getSyncSysUrlMap();  
		String userSyncKey=sysCode+".user";
		String userSyncUrl=syncSysUrlMap.get(userSyncKey);
		JsonConfig config = MyJsonConfig.getJsonConfig();
		JSONObject json = JSONObject.fromObject(syncUser,config);   
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("id",syncUser.getId()));  
		nvps.add(new BasicNameValuePair("code",syncUser.getCode()));  
		nvps.add(new BasicNameValuePair("userName",syncUser.getUserName()));  
		nvps.add(new BasicNameValuePair("empName",syncUser.getEmpName()));  
		nvps.add(new BasicNameValuePair("orgUnitId",syncUser.getOrgUnitId()));  
		nvps.add(new BasicNameValuePair("type",syncUser.getType()));  
		nvps.add(new BasicNameValuePair("state",syncUser.getState()));  
		nvps.add(new BasicNameValuePair("sn",syncUser.getSn()));  
		nvps.add(new BasicNameValuePair("jobNumber",syncUser.getJobNumber()));  
		nvps.add(new BasicNameValuePair("sex",syncUser.getSex()));  
		nvps.add(new BasicNameValuePair("idNumber",syncUser.getIdNumber()));  
		nvps.add(new BasicNameValuePair("nativePlace",syncUser.getNativePlace()));  
		nvps.add(new BasicNameValuePair("education",syncUser.getEducation()));
		nvps.add(new BasicNameValuePair("school",syncUser.getSchool()));
		nvps.add(new BasicNameValuePair("birthday",syncUser.getBirthday()));
		nvps.add(new BasicNameValuePair("contactAddress",syncUser.getContactAddress()));
		nvps.add(new BasicNameValuePair("photo",syncUser.getPhoto()));
		nvps.add(new BasicNameValuePair("ophOne",syncUser.getOphOne()));
		nvps.add(new BasicNameValuePair("ophTwo",syncUser.getOphTwo()));
		nvps.add(new BasicNameValuePair("mobileOne",syncUser.getMobileOne()));
		nvps.add(new BasicNameValuePair("mobileTwo",syncUser.getMobileTwo()));
		nvps.add(new BasicNameValuePair("fax",syncUser.getFax()));
		nvps.add(new BasicNameValuePair("personalEmail",syncUser.getPersonalEmail()));
		nvps.add(new BasicNameValuePair("enterpriseEmail",syncUser.getEnterpriseEmail()));
		nvps.add(new BasicNameValuePair("dutyType",syncUser.getDutyType()));
		nvps.add(new BasicNameValuePair("duty",syncUser.getDuty()));
		nvps.add(new BasicNameValuePair("administrativeLevel",syncUser.getAdministrativeLevel()));
		
		 int result=0;
		/*String resultStr=HttpClientUtil.httpClientByPost(userSyncUrl,nvps);  
		
		if(null!=resultStr){
		   result = saveUserSyncLog(syncUser,sysCode,resultStr,isSaveLog);
		}*/
		return result;
	}



	public int syncOrg(SyncOrganization org,boolean isSaveLog) {
		   int result=0;
		   List<String> syncSysCodeList=DataSyncUrl.getSyncSysCodeList();
		   if(null!=syncSysCodeList&&syncSysCodeList.size()>0){
			   for(String sysCode:syncSysCodeList){
				   
			    result = httpSyncOrg(org, sysCode,isSaveLog);
				   
			   }
		   }
		
		return result;
	}



	public int httpSyncOrg(SyncOrganization org,String sysCode, boolean isSaveLog) {
		Map<String,String> syncSysUrlMap=DataSyncUrl.getSyncSysUrlMap();  
		String userSyncKey=sysCode+".org";
		String userSyncUrl=syncSysUrlMap.get(userSyncKey);
		JsonConfig config = MyJsonConfig.getJsonConfig();
		JSONObject json = JSONObject.fromObject(org,config);   
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("id",org.getId()));   
		nvps.add(new BasicNameValuePair("name",org.getName()));   
		nvps.add(new BasicNameValuePair("shortName",org.getShortName()));   
		nvps.add(new BasicNameValuePair("parentId",org.getParentId()));   
		nvps.add(new BasicNameValuePair("code",org.getCode()));   
		nvps.add(new BasicNameValuePair("type",org.getType()));   
		nvps.add(new BasicNameValuePair("state",org.getState()));   
		nvps.add(new BasicNameValuePair("seq",org.getSeq()+"")); 
		nvps.add(new BasicNameValuePair("dutyId",org.getDutyId())); 
		nvps.add(new BasicNameValuePair("compId",org.getCompId())); 
		
		 int result=0;
		
	/*	String resultStr=HttpClientUtil.httpClientByPost(userSyncUrl,nvps);  
		 if(null!=resultStr){
			  result = saveOrgSyncLog(org,sysCode, resultStr,isSaveLog);
		 }*/
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
