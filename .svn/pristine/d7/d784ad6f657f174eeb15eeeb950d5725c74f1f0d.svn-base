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
			}
			
		}else{
			logger.debug("没有错误日志");
		}
		
	}
	
	
    
	
}
