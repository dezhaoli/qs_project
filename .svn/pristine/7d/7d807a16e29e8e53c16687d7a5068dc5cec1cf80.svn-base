package com.qs.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 读取配置文件类
 * @author Administrator
 *
 */
public class ResourceUtil{
	/**
	 * 
	 * @标题: getValueByKey 
	 * @描述: (这里用一句话描述这个方法的作用) 
	 *
	 * @参数信息
	 *    @param file
	 *    @param key
	 *    @return
	 *
	 * @返回类型 String
	 * @开发者 moyousheng
	 * @可能抛出异常
	 */
	public static String getValueByKey(File file, String key){
		try{
			Properties properties = new Properties();
			properties.load(new FileInputStream(file));
			return properties.getProperty(key);
		} catch (Throwable e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: getValueByKey
	 * @Description: (通过key获取value)
	 * @param @param filePath
	 * @param @param key
	 * @param @return
	 * @return String
	 * @user moyousheng
	 * @throws
	 */
	public static String getValueByKey(String filePath, String key){
		
		Enumeration<URL> resouces;
		try{
			resouces = ResourceUtil.class.getClassLoader().getResources(filePath);
			if (null != resouces){
				for (Enumeration<URL> tmp = resouces; tmp.hasMoreElements();){
					URL url = tmp.nextElement();
					Properties properties = new Properties();
					properties.load(url.openStream());
					
					Object reulst = properties.get(key);
					if (null != reulst){
						return reulst.toString();
					}
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
