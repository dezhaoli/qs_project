package  com.qs.pub.sync.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.pub.sync.common.SyncDataConversion;
import com.qs.pub.sync.service.DataSyncService;
import com.qs.sync.model.LogError;
import com.qs.sync.model.SyncOrganization;
import com.qs.sync.model.SyncUser;

/**
 * @ClassName: ErrorDataHandleTask
 * @Description: 遗漏数据同步
 * @author moyousheng
 * @date 2016年6月3日 上午11:02:16
 */
@Service
public class MissDataHandleTask {
	
    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(MissDataHandleTask.class);

/*	
	
    @Autowired
	private DataSyncService dataSyncService;
	@Autowired
	private OrgUserMapper userMapper;
	@Autowired
	private OrgUnitMapper orgUnitMapper;
	
	public void missDataSend() {
		
		logger.debug("missDataSend====================");

		List<OrgUnit> orgList=orgUnitMapper.findMissSyncOrgUnit();
		if(null!=orgList&&orgList.size()>0){
			for(OrgUnit org:orgList){
				this.syncOrganization(SyncDataConversion.getSyncOrg(org));
				OrgUnit saveOrg=new OrgUnit();
				saveOrg.setId(org.getId());
				saveOrg.setSync("1");
				orgUnitMapper.update(saveOrg);
			}
		}
		
		List<User> userList=userMapper.findMissSyncUser();
		if(null!=userList&&userList.size()>0){
			for(User user:userList){
				this.syncUser(SyncDataConversion.getSyncUser(user));
				User saveUser=new User();
				saveUser.setId(user.getId());
				saveUser.setSync("1");
				userMapper.updateUser(saveUser);
			}
		}
		
		
	}
	
	public void syncUser(SyncUser syncUser) {
		syncUser.setFromSysCode(FromSystem.CODE);
		syncUser.setFromSysName(FromSystem.NAME);
		syncUser.setFromSysMethod(getCallerMethod());
		dataSyncService.syncUser(syncUser,SyncContans.SaveLogFlag.YES);
	}
	
	public void syncOrganization(SyncOrganization syncOrg) {
		syncOrg.setFromSysCode(FromSystem.CODE);
		syncOrg.setFromSysName(FromSystem.NAME);
		syncOrg.setFromSysMethod(getCallerMethod());
		dataSyncService.syncOrg(syncOrg,SyncContans.SaveLogFlag.YES);
	}*/
	
    
	/**
	 * 获取上层调用方法信息(即调用数据同步的方法,便于错误定位)
	 * @return
	 */
	protected static String getCallerMethod(){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
		return ste.getClassName() + "." + ste.getMethodName() + "(line" + ste.getLineNumber() + ")";
	}
}
