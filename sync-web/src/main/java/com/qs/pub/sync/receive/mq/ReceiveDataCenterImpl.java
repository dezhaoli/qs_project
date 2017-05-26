package com.qs.pub.sync.receive.mq;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qs.pub.sync.service.DataCenterSyncService;
import com.qs.sync.model.SyncCreateRoom;
import com.qs.sync.model.SyncObject;
import com.qs.sync.model.SyncPlaying;

/**
 * 
 * @ClassName: ReceiveDataImpl 
 * @描述: 处理从ActiveMQ接收到的消息
 * @author Administrator
 * @date 2015年7月6日 上午11:02:52
 */
public class ReceiveDataCenterImpl implements IReceiveDataCenter {
	
	private static final Log log = LogFactory.getLog(ReceiveDataCenterImpl.class);
	@Autowired
    private DataCenterSyncService dataSyncService;
	
	
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
		
		if(myMessage instanceof SyncPlaying){
			this.syncPlaying(myMessage);
			
		}else if(myMessage instanceof SyncCreateRoom){
			this.syncCreateRoom(myMessage);
		}
		
	     
	}
	


	/**
	 * 同步在玩用户数据
	 * @param myMessage
	 */
	public void syncPlaying(Object myMessage){
		SyncPlaying syncPlaying=(SyncPlaying)myMessage;
		dataSyncService.syncPlaying(syncPlaying);
		
	}

	/**
	 * 同步创建房间数据
	 * @param myMessage
	 */
	public void syncCreateRoom(Object myMessage){
		SyncCreateRoom syncCreateRoom=(SyncCreateRoom)myMessage;
		dataSyncService.syncCreateRoom(syncCreateRoom);
		
	}
	
	

}
