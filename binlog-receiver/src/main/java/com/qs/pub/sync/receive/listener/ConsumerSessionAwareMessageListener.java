package com.qs.pub.sync.receive.listener;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import com.qs.pub.sync.receive.mq.IReceiveData;


/**
 * 
 * @描述: 消息监听器 
 * @作者:  moyousheng
 * @创建时间: 2016-5-27,下午16:36:27 .
 */
@Component
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<ActiveMQObjectMessage> {

	private static final Log log = LogFactory.getLog(ConsumerSessionAwareMessageListener.class);

	
	private IReceiveData receiveData;
	public void setReceiveData(IReceiveData receiveData){
		this.receiveData = receiveData;
	}

	public synchronized void onMessage(ActiveMQObjectMessage message,Session session) {
		
		receiveData.handleMessage(message,session);
	}
}
