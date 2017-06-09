package com.qs.common.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;


/**
 * 简单封装memcached工具类
 * @author zyy
 *
 */
public class MemcachedUtil {
	
	Logger log = Logger.getLogger(MemcachedUtil.class);  
	/**
	 * 设置value
	 * @param url
	 * @param key
	 * @param value
	 * @param time
	 * @throws IOException
	 * @author:zyy
	 * @time:2017年6月6日
	 */
	public static void setMemcached(String url,String key ,Object value,int time) throws IOException{
		 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(url));  
		 builder.setCommandFactory(new BinaryCommandFactory());
		 MemcachedClient memcachedClient = builder.build();  	
		        try {
					memcachedClient.set(key, time, value);
					//String value = memcachedClient.get("key");  
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}  
		   
	}
	
	/**
	 * 获取key对应value
	 * @param url
	 * @param key
	 * @return
	 * @throws IOException
	 * @author:zyy
	 * @time:2017年6月6日
	 */
	public static Object getMemcached(String url,String key ) throws IOException{
		 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(url));  
		 builder.setCommandFactory(new BinaryCommandFactory()); 
		 MemcachedClient memcachedClient = builder.build();  
		 
			System.out.println("url::"+url); 
			System.out.println("key::"+key); 
		 Object value=null;
		        try {
					value = memcachedClient.get(key);  
					System.out.println("memcachedClient.get(key)========::"+value); 
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}
				return value;  
		   
	}
	
	
	/**
	 * 判断存在key 否
	 * @param url
	 * @param key
	 * @return 为空 true 否false
	 * @throws IOException
	 * @author:zyy
	 * @time:2017年6月6日
	 */
	public static boolean isBlankMemcached(String url,String key ) throws IOException{
		 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(url));  
		 builder.setCommandFactory(new BinaryCommandFactory());
		 MemcachedClient memcachedClient = builder.build();  
		 Object value=null;
		        try {
					value = memcachedClient.get(key);  
					System.out.println("memcachedClient.get(key)========::"+value);
					
					if (value ==null) {
						return false;
					}
					if (StringUtils.isBlank(value.toString())){
						return true;
					}
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}
				return false;  
		   
	}
	
	/**
	 * 删除缓存
	 * @param url
	 * @param key
	 * @return 为空 true 否false
	 * @throws IOException
	 * @author:zyy
	 * @time:2017年6月6日
	 */
	public static boolean deleteMemcached(String url,String key ) throws IOException{
		 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(url));  
		 MemcachedClient memcachedClient = builder.build();  
		 boolean value=false;
		        try {
					 value = memcachedClient.delete(key);  
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (MemcachedException e) {
					e.printStackTrace();
				}
				return value;  
		   
	}
}
