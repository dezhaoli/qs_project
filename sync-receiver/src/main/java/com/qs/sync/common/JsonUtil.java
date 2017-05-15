package com.qs.sync.common;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qs.sync.service.impl.LogSuccessServiceImpl;

/**
 * @ClassName: JsonUtil
 * @Description: JsonUtil工具类
 * @author 
 * @date 2016年6月3日 下午10:27:33
 */
public class JsonUtil
{
	
    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    	
	/**
	 * 
	 * @Title: getJsonString
	 * @Description: (获取Json字符串)
	 * @param @param obj
	 * @param @return
	 * @return String
	 * @user moyousheng
	 * @throws
	 */
	public static String getJsonString(Object obj)
	{
		String json=null;
		
		if (null != obj)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				json = mapper.writeValueAsString(obj);
				
				if (log.isDebugEnabled())
				{
					log.debug(json);
				}
				return json;
			} catch (JsonProcessingException e)
			{
				e.printStackTrace();
				log.error("getJsonString==="+json);
			}
			
		}
		return null;
	}
	
	public static <T> T fromJson(String jsonString, Class<T> clazz)
	{
		if (StringUtils.isEmpty(jsonString))
		{
			return null;
		}
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e)
		{   
			log.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 字符串json数据转对象
	 * @param jsonString
	 * @param clazz
	 * @user moyousheng
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJsonObject(String jsonString, Class<T> clazz)
	{
		if (StringUtils.isEmpty(jsonString))
		{
			return null;
		}
		
		try
		{
			JSONObject json = JSONObject.fromObject(jsonString);
			return (T)JSONObject.toBean(json, clazz);
		} catch (Exception e)
		{   
			log.error(e.getMessage());
			return null;
		}
	}
	
	/***
	 * 
	 * @方法名称: fromJson 
	 * @描述: 
	 *    转换为map
	 *
	 * @参数:
	 *    @param jsonString
	 *    @return
	 *
	 * @返回类型: Map<String,String>
	 * @作者:moyousheng
	 * @可能抛出异常:
	 */
	public static Map<String, String> fromJson(String jsonString)
	{
		if (StringUtils.isEmpty(jsonString))
		{
			return null;
		}
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			return mapper.readValue(jsonString, Map.class);
		} catch (IOException e)
		{
			return null;
		}
	}
	
	public static JSONObject jsonNoNull(JSONObject js)
	{
	
		Set<Object> set = js.entrySet();
		
		Iterator it = set.iterator();
		while (it.hasNext()){
			Map.Entry element = (Map.Entry) it.next();
			
			if(js.get(element.getKey()).toString()==null || "null".equals(js.get(element.getKey()).toString())){
				
				js.element(element.getKey().toString(), "");
				
			}
		}
		
		return js;
	}
	
}
