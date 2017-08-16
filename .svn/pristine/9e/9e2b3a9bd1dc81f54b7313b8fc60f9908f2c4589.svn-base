package com.qs.pub.sync.receive.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.aliyun.drc.clusterclient.ClusterClient;
import com.aliyun.drc.clusterclient.ClusterListener;
import com.aliyun.drc.clusterclient.DefaultClusterClient;
import com.aliyun.drc.clusterclient.RegionContext;
/**
 * 主要处理初始化SDK和消费者，实现数据的消费，请求订阅通道
 * @author zhengshengfei
 *
 */
@Component
public class InitParam  implements ApplicationListener<ContextRefreshedEvent>{
	 // 用户需要替换自己使用的accessKey, accessSecret, subscribeInstanceID
    /*
     * accessKey、accessSecret 为订阅实例所属阿里云账号的
     * AccessKey及AccessSecret,AccessKey/AccessSecret的获取方式可以参考本文最后一节。
     *  subscribeInstanceID 为需要订阅数据的数据订阅实例ID,需要到DTS控制台中获取。
     */
    private static String accessKey = "LTAI9OMHB9mwmC3i";
    private static String accessSecret = "dRV7v5tXe8s5hkLqVD5C4p8UCyucQH";
    private static String subscribeInstanceID = "dtsvb9m1s8d7514";
    
    //监听者，主要负责监听订阅通道中的增量数据，然后回掉notify方法中消费消息。
    @Autowired
    private ClusterListener listener ;
   
	public static String getAccessKey() {
		return accessKey;
	}

	public static void setAccessKey(String accessKey) {
		InitParam.accessKey = accessKey;
	}

	public static String getAccessSecret() {
		return accessSecret;
	}

	public static void setAccessSecret(String accessSecret) {
		InitParam.accessSecret = accessSecret;
	}

	public static String getSubscribeInstanceID() {
		return subscribeInstanceID;
	}

	public static void setSubscribeInstanceID(String subscribeInstanceID) {
		InitParam.subscribeInstanceID = subscribeInstanceID;
	}


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			 //创建一个context 
			   RegionContext context = new RegionContext();
			   context.setUsePublicIp(true);
			   context.setAccessKey(accessKey);
			   context.setSecret(accessSecret);
	
			   //创建集群消费client 
			   final ClusterClient client = new DefaultClusterClient(context);
			   try {
				 //设置监听者 
				   client.addConcurrentListener(listener);
				   // 设置请求的guid 
				   client.askForGUID(subscribeInstanceID);
				   // 启动后台线程 
				   client.start();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}	
	}


}
