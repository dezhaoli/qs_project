package com.qs.pub.sync.receive.mq;

import com.qs.pub.sync.service.SyncService;
import com.qs.sync.model.*;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Session;

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
    private SyncService dataSyncService;
	
	
	/**
	 * 消息接收处理
	 */
	public void handleMessage(ActiveMQObjectMessage message, Session session)
	{
		Object myMessage = null;
		try
		{
			myMessage = (Object) message.getObject();
			
			SyncObject so = (SyncObject) myMessage;
			log.debug("fromSystemCode===========::" + so.getFromSysCode()
					+ "=========fromSysMethod=======::"
					+ so.getFromSysMethod());
			
		    if (myMessage instanceof SyncMemberfides)
			{
				this.syncMemberFides(myMessage);
			} else if (myMessage instanceof SyncMemberagents)
			{
				this.syncMemberagents(myMessage);
			} else if (myMessage instanceof SyncMemberbusiness)
			{
				this.syncMemberbusiness(myMessage);
			} else if (myMessage instanceof GameRecord)
			{
				dataSyncService.addGameRecord((GameRecord) myMessage);
				
			} else if (myMessage instanceof GameRecordSub)
			{
				dataSyncService.addGameRecordSub((GameRecordSub) myMessage);
			} else if (myMessage instanceof GoldLog)
			{
				dataSyncService.addGoldLog((GoldLog) myMessage);
			} else if (myMessage instanceof PlayerRecord)
			{
				dataSyncService.addPlayerRecord((PlayerRecord) myMessage);
			} else if (myMessage instanceof RoomRecord)
			{
				dataSyncService.addRoomRecord((RoomRecord) myMessage);
			} else if (myMessage instanceof DouniuGameRecord)
			{
				dataSyncService
						.addDouniuGameRecord((DouniuGameRecord) myMessage);
			} else if (myMessage instanceof DouniuGameRecordSub)
			{
				dataSyncService.addDouniuGameRecordSub(
						(DouniuGameRecordSub) myMessage);
			} else if (myMessage instanceof MajiangGameRecord)
			{
				dataSyncService
						.addMajiangGameRecord((MajiangGameRecord) myMessage);
			} else if (myMessage instanceof MajiangGameRecordSub)
			{
				dataSyncService.addMajiangGameRecordSub(
						(MajiangGameRecordSub) myMessage);
			}
			
		} catch (JMSException e)
		{
			e.printStackTrace();
		}
		
	}
	


	private void syncMemberbusiness(Object myMessage)
	{
		dataSyncService.addMemberbusiness((SyncMemberbusiness) myMessage);
	}



	private void syncMemberagents(Object myMessage)
	{
		dataSyncService.addMemberagents((SyncMemberagents) myMessage);
	}



	private void syncMemberFides(Object myMessage)
	{
		dataSyncService.addMemberFides((SyncMemberfides) myMessage);
	}
}
