package com.qs.pub.sync.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncOrganization;
import com.qs.sync.model.SyncUser;


/**
 * @ClassName: SyncDataUtil 
 * @Description: 同步数据转化类
 * @author moyousheng
 * @date 2016年6月2日 下午6:39:30 
 */
public class SyncDataConversion {
    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(SyncDataConversion.class);

/*	public static SyncUser getSyncUser(User user){
		SyncUser syncUser = new SyncUser();
		try {
			BeanUtils.copyProperties(syncUser, user);
			syncUser.setUserName(user.getUserName());
			syncUser.setEmpName(user.getEmpName());
			Emp emp=user.getEmp();
            if(null!=emp){
        		BeanUtils.copyProperties(syncUser,emp);
            }
            
			BeanUtils.copyProperties(syncUser, user);
			syncUser.setId(user.getId());
			syncUser.setState(user.getState());
			syncUser.setUserName(user.getUserName());
			syncUser.setEmpName(user.getEmpName());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
    return syncUser;		
	}
	
	public static SyncOrganization getSyncOrg( OrgUnit org){
		
		SyncOrganization syncOrg = new SyncOrganization();
		try {
			BeanUtils.copyProperties(syncOrg,org);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
      return syncOrg;		
	}
*/
}
