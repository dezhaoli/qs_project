package com.qs.pub.sync.receive.mq;

import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;

/**
 * 
 * @ClassName: IReceiveData 
 * @描述: 消息接收器、处理接收到的JMS消息
 * @author moyousheng
 * @date 2016-5-27下午15:40:11
 */
public interface IReceiveDataCenter {
	
	/**
	 * 消息处理接口
	 * 客户端需要实现这个接口
	 * @param message
	 */
	public void handleMessage(ActiveMQObjectMessage message,Session session);
	
}
