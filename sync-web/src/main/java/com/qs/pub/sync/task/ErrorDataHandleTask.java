package  com.qs.pub.sync.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.pub.sync.common.JsonUtil;
import com.qs.pub.sync.constant.SyncContans;
import com.qs.pub.sync.mapper.LogErrorMapper;
import com.qs.pub.sync.service.SyncService;
import com.qs.sync.model.LogError;
import com.qs.sync.model.SyncCreateRoom;
import com.qs.sync.model.SyncOrganization;
import com.qs.sync.model.SyncPlaying;
import com.qs.sync.model.SyncUser;
import com.qs.sync.model.SyncUserLoginLog;

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
	private SyncService dataSyncService;
	 
	public void dataHandleTask() {
		
		logger.debug("dataHandleTask====================");
		
		List<LogError> logErrorList=logErrorMapper.findLogErrorList();
		
		if(null!=logErrorList&&logErrorList.size()>0){
			
			for(LogError errorLog:logErrorList){
				//json字符串
				String content = errorLog.getContent();
				//模块
				String modelCode = errorLog.getModuleCode();
				if (SyncContans.FromTableCode.PLAYING.equals(modelCode))
				{
					this.playingErrorLogHandle(errorLog);
				} else if(SyncContans.FromTableCode.ROOM.equals(modelCode))
				{
					this.roomErrorLogHandle(errorLog);
				}else if(SyncContans.FromTableCode.USER_LOGIN.equals(modelCode)){
					this.userLoginErrorLogHandel(errorLog);
				}
			}
			
		}else{
			logger.debug("没有错误日志");
		}
		
	}
	
	private void userLoginErrorLogHandel(LogError errorLog)
	{

		logger.debug("playingErrorLogHandle====================");
		SyncUserLoginLog	syncUserLoginLog = JsonUtil.fromJsonObject(errorLog.getContent(), SyncUserLoginLog.class);
		int result=0;
		//异常,所有系统同步一遍
		result=dataSyncService.syncUserLoginLogError(syncUserLoginLog);
		
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
	 * 在玩错误日志
	 */
	public void playingErrorLogHandle(LogError errorLog) {
		logger.debug("playingErrorLogHandle====================");
		SyncPlaying	syncPlaying = JsonUtil.fromJsonObject(errorLog.getContent(), SyncPlaying.class);
		int result=0;
		//异常,所有系统同步一遍
		result=dataSyncService.syncPlayingLog(syncPlaying);
		
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
	 * 创建房间错误日志
	 */
	public void roomErrorLogHandle(LogError errorLog) {
		
		SyncCreateRoom syncCreateRoom = JsonUtil.fromJsonObject(errorLog.getContent(), SyncCreateRoom.class);
		int result=0;
		//异常
		result=dataSyncService.syncCreateRoomLog(syncCreateRoom);
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
