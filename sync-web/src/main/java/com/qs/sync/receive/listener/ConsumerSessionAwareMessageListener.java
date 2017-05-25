package com.qs.sync.receive.listener;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import com.qs.sync.receive.mq.IReceiveData;


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
		try {
			receiveData.handleMessage(message,session);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("同步数据发生错误,msg:", e);
		    // 发送异常，重新放回队列
		}
	}
}
