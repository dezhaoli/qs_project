package  com.qs.sync.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.sync.common.JsonUtil;
import com.qs.sync.constant.SyncContans;
import com.qs.sync.mapper.LogErrorMapper;
import com.qs.sync.model.LogError;
import com.qs.sync.model.SyncOrganization;
import com.qs.sync.model.SyncUser;
import com.qs.sync.receive.mq.ReceiveDataImpl;
import com.qs.sync.service.DataSyncService;
import com.qs.sync.service.LogErrorService;
import com.qs.sync.service.impl.DataSyncServiceImpl;

/**
 * @ClassName: ErrorDataHandleTask
 * @Description: 错误日志定时同步
 * @author moyousheng
 * @date 2016年6月3日 上午11:02:16
 */
@Service
public class ErrorDataHandleTask {
    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(ErrorDataHandleTask.class);

	
	 @Autowired
	 private LogErrorMapper logErrorMapper;
     @Autowired
	private DataSyncService dataSyncService;
	 
	public void dataHandleTask() {
		
		logger.debug("dataHandleTask====================");
		
		List<LogError> logErrorList=logErrorMapper.findLogErrorList();
		
		if(null!=logErrorList&&logErrorList.size()>0){
			
			for(LogError errorLog:logErrorList){
				//json字符串
				String content = errorLog.getContent();
				//模块
				String modelCode = errorLog.getModuleCode();
				 if(SyncContans.FromTableCode.USER.equals(modelCode)){
                      this.userErrorLogHandle(errorLog);
				 }else{
					   this.orgErrorLogHandle(errorLog);
				 }
			}
			
		}else{
			logger.debug("没有错误日志");
		}
		
	}
	
	/**
	 * 用户错误日志
	 */
	public void userErrorLogHandle(LogError errorLog) {
		logger.debug("userErrorLogHandle====================");
		SyncUser	syncUser = JsonUtil.fromJsonObject(errorLog.getContent(), SyncUser.class);
		int result=0;
		if(StringUtils.isBlank(errorLog.getReceiveCode())){
			//mq异常,所有系统同步一遍
			result=dataSyncService.syncUser(syncUser,SyncContans.SaveLogFlag.NO);
		}else{
			result=dataSyncService.httpSyncUser(syncUser,errorLog.getReceiveCode(),SyncContans.SaveLogFlag.NO);
		}
		
		int sendNum=Integer.parseInt(errorLog.getSendNum());
		sendNum+=1;
		errorLog.setSendNum(sendNum+"");
		
		if(result==1){
			//同步成功
			errorLog.setStatus("1");
			errorLog.setSendNum(sendNum+"");
			errorLog.setModifyTime(new Date());
			logErrorMapper.update(errorLog);
			
		}else{
			errorLog.setStatus("0");
			errorLog.setSendNum(sendNum+"");
			errorLog.setModifyTime(new Date());
			logErrorMapper.update(errorLog);
		}
		
		
	
	}
	/**
	 * 组织机构错误日志
	 */
	public void orgErrorLogHandle(LogError errorLog) {
		
		SyncOrganization syncOrg = JsonUtil.fromJsonObject(errorLog.getContent(), SyncOrganization.class);
		int result=0;
		if(StringUtils.isBlank(errorLog.getReceiveCode())){
			 //mq异常
			result=dataSyncService.syncOrg(syncOrg,SyncContans.SaveLogFlag.NO);
		}else{
			result=dataSyncService.httpSyncOrg(syncOrg,errorLog.getReceiveCode(),SyncContans.SaveLogFlag.NO);
		}
		int sendNum=Integer.parseInt(errorLog.getSendNum());
		sendNum+=1;
		errorLog.setSendNum(sendNum+"");
		
		if(result==1){
			//同步成功
			errorLog.setStatus("1");
			errorLog.setSendNum(sendNum+"");
			errorLog.setModifyTime(new Date());
			logErrorMapper.update(errorLog);
			
		}else{
			errorLog.setStatus("0");
			errorLog.setSendNum(sendNum+"");
			errorLog.setModifyTime(new Date());
			logErrorMapper.update(errorLog);
		}
		
	}
	
    
	
}
