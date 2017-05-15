package com.qs.sync.receive.mq;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qs.sync.constant.SyncContans;
import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncOrganization;
import com.qs.sync.model.SyncUser;
import com.qs.sync.service.DataSyncService;

/**
 * 
 * @ClassName: ReceiveDataImpl 
 * @描述: 处理从ActiveMQ接收到的消息
 * @author Administrator
 * @date 2015年7月6日 上午11:02:52
 */
public class ReceiveDataImpl implements IReceiveData {
	
	private static final Log log = LogFactory.getLog(ReceiveDataImpl.class);
	@Autowired
    private DataSyncService dataSyncService;
	
	/**
	 * 消息接收处理
	 */
	public void handleMessage(ActiveMQObjectMessage message,Session session) {
		Object myMessage = null;
		try {
			myMessage = (Object) message.getObject();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		SyncObject so=(SyncObject)myMessage;
		log.debug("fromSystemCode===========::"+so.getFromSysCode()+"=========fromSysMethod=======::"+so.getFromSysMethod());
		
		if(myMessage instanceof SyncUser){
			this.syncUser(myMessage);
			
		}else if(myMessage instanceof SyncOrganization){
			this.syncOrg(myMessage);
		}
		
	     
	}
	


	/**
	 * 同步用户信息
	 * @param myMessage
	 */
	public void syncUser(Object myMessage){
		SyncUser syncUser=(SyncUser)myMessage;
		dataSyncService.syncUser(syncUser,SyncContans.SaveLogFlag.YES);
		
	}

	/**
	 * 同步组织信息
	 * @param myMessage
	 */
	public void syncOrg(Object myMessage){
		SyncOrganization org=(SyncOrganization)myMessage;
		dataSyncService.syncOrg(org,SyncContans.SaveLogFlag.YES);
		
	}
	
	

}
