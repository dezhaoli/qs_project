package com.qs.sync.sender;

import java.util.Date;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qs.sync.model.SyncObject;
import com.qs.sync.sender.mq.JmsSenderSpring;
import com.qs.sync.sender.util.DataUtil;

/** 
 * @ClassName: SendDataFacade 
 * @描述: 发送消息
 * @author moyousheng
 * @date 2016-5-27下午15:40:11
 */
public class SendDataFacade{
	

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(SendDataFacade.class);
    
    
	private JmsSenderSpring jmsSenderSpring;
	public void setJmsSenderSpring(JmsSenderSpring jmsSenderSpring){
		this.jmsSenderSpring = jmsSenderSpring;
	}

	/**
	 * 
	 * @标题: sendByJms 
	 * @描述: 通过JMS发送数据
	 *
	 * @参数信息
	 *    @param model 发送数据的实体类必须继承自@see SyncObject
	 *    @return true正常、false失败 
	 */
	public boolean sendByJms(final SyncObject model){
		return sendByJms(model,true);
	}
	
	
	/**
	 * @标题: sendByJms 
	 * @描述: 通过JMS发送数据
	 *
	 * @参数信息
	 *    @param model 发送数据的实体类
	 *    @param isSendAsync 如果是true则为异步，false为同步
	 */
	public boolean sendByJms(final SyncObject model,boolean isSendAsync){
		//入参检查
		if(! DataUtil.checkObjectParam(model)){
			return false;
		}
		//根据model类型返回目的地
		Destination destination = DataUtil.getJmsDestination(model);
		if(null != destination){
			//同步时间
			model.setSynDateStamp(new Date());
			jmsSenderSpring.setSendAsync(isSendAsync);
			jmsSenderSpring.sendSingle(model, destination);
		}else{
			
		  if (logger.isInfoEnabled()) {
	            logger.info("通过JMS发送数据,未在ActiveMQ中定义对应的主题{}", model);
	      }
			return false;
		}
		
		return true;

	}
	
}
