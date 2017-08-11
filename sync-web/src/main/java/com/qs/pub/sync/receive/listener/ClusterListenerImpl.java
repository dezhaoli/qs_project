package com.qs.pub.sync.receive.listener;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.aliyun.drc.client.message.ByteString;
import com.aliyun.drc.client.message.DataMessage;
import com.aliyun.drc.client.message.DataMessage.Record;
import com.aliyun.drc.clusterclient.ClusterListener;
import com.aliyun.drc.clusterclient.message.ClusterMessage;
import com.google.common.base.StandardSystemProperty;
import com.qs.pub.sync.model.SyncUser;
import com.qs.pub.sync.service.ISyncUserService;
import com.qs.pub.sync.service.impl.SyncUserServiceImpl;

import net.sf.json.JSONObject;
/**
 * 主要负责监听阿里云的订阅通道
 * @author zhengshengfei
 *
 */
public class ClusterListenerImpl extends ClusterListener {
	
	private ISyncUserService syncUserService = new SyncUserServiceImpl();

	@Override
	public void notify(List<ClusterMessage> messages) {
		try {
			for (ClusterMessage message : messages) {
			    /* 可打印数据 */
			  System.out.print(message.getRecord().getDbname() + ":"
			                        + message.getRecord().getTablename() + ":"
			                        + message.getRecord().getOpt() + ":"
			                        + message.getRecord().getTimestamp() + ":"
			                        + message.getRecord());
				
				StringBuilder insert_string=new StringBuilder();
				Record.Type type=message.getRecord().getOpt();
				DataMessage.Record.Field field;
				SyncUser syncUser=new SyncUser();
				
				if(type.equals(Record.Type.INSERT)){
				       int i=0;
				       Map<String,String> map=new HashMap<String,String>();
				       List<DataMessage.Record.Field> fields = message.getRecord().getFieldList();   
				       for (; i < fields.size(); i++) {
				              field = fields.get(i);                                        	         
				              ByteString str  = field.getValue();
				              String es = new String(str.getBytes(),"utf-8"); 
				           
				            map.put(field.getFieldname(), es);
				             
				        }
				       JSONObject params= JSONObject.fromObject(map);
				       syncUser = JSON.parseObject(params.toString(),SyncUser.class);         

				        syncUserService.insert(syncUser);
				       
				}
			    // ackAsConsumed必须调用
			    message.ackAsConsumed();
			    
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void noException(Exception e) {
		
		
	}
	


}
