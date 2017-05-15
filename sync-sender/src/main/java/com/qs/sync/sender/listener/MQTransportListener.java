package com.qs.sync.sender.listener;

import java.io.IOException;     

import org.apache.activemq.transport.TransportListener;     
import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;

/**
 * 监控mq的状态
 * @author moyousheng
 *
 */
public class MQTransportListener implements TransportListener{
	 protected final Logger logger = LoggerFactory.getLogger(MQTransportListener.class);    
	 private static boolean MQISRUNNING =true;//mq是否处于运行状态
	 
	 
	public static boolean isMQISRUNNING() {
		return MQISRUNNING;
	}

	public static void setMQISRUNNING(boolean mQISRUNNING) {
		MQISRUNNING = mQISRUNNING;
	}

	/**     
	 * 对消息传输命令进行监控     
	 * @param command     
	 */     
	public void onCommand(Object o) {		
		logger.info("onCommand检测到服务端命令:{}", o);  
	}     
     
	/**     
	 * 对监控到的异常进行触发     
	 * @param error     
	 */     
	public void onException(IOException error) {		     
		logger.error("onException -> 消息服务器连接错误......");			     
	}     
     
	/**     
	 * 当failover时触发     
	 */     
	public void transportInterupted() {     
		logger.error("transportInterupted -> 消息服务器连接发生中断......");     
		MQISRUNNING=false;
	}     
     
	/**     
	 * 监控到failover恢复后进行触发     
	 */     
	public void transportResumed() {     
		logger.info("transportResumed -> 消息服务器连接已恢复......");     
		MQISRUNNING=true;		     
	}     
}
