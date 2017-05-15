package com.qs.sync.sender.mq;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.Assert;

/** 
 * @ClassName: JmsSender 
 * @描述: JMS消息发送
 * @author moyousheng
 * @date 2016-5-27下午15:40:11
 */
public class JmsSenderSpring
{
	//通过配置文件注入
    private TaskExecutor taskExecutor;
    public void setTaskExecutor(TaskExecutor taskExecutor){
		this.taskExecutor = taskExecutor;
	}
    
    private static  ExecutorService execThread=Executors.newFixedThreadPool(10);  	

	@Autowired
    private JmsTemplate jmsTemplate;  

	private boolean isSendAsync = true;
  
    /**
     * 
     * @标题: sendSingle 
     * @描述: 单个发送消息
     *
     * @参数信息
     *    @param message
     *    @param destination
     */
    public void sendSingle(Serializable message,Destination destination) {  
        sendMessage(message,destination);  
    }  
  
    /**
     * 
     * @标题: sendBatch 
     * @描述: 批量发送消息
     *
     * @参数信息
     *    @param messages
     *    @param destination
     */
    public void sendBatch(Collection<?> messages,Destination destination) {  
        Assert.notNull(messages, "批量发送消息失败，参数 'messages' can't be null !");  
        Assert.notEmpty(messages, "批量发送消息失败，参数 'message' can't be empty !");  
        for (Object message : messages) {  
            sendSingle(String.valueOf(message),destination);  
        }
    }
  
    private void sendMessage(final Serializable message,Destination destination) {  
        final Destination sendDest = destination ;  
    	execThread.execute(new Runnable(){
			public void run() {
				try {
				     send(message,sendDest);  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }  

    /**
     * 
     * @标题: send 
     * @描述: 发送序列化对象
     *
     * @参数信息
     *    @param message
     *    @param destination
     */
    private void send(final Serializable message,Destination destination) {  
        this.jmsTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
                return session.createObjectMessage(message);  
            }  
  
        });  
    }
    
    public boolean isSendAsync() {  
        return isSendAsync;  
    }  
      
    public void setSendAsync(boolean isSendAsync) {  
        this.isSendAsync = isSendAsync;  
    }
}
