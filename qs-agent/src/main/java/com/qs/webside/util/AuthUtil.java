package com.qs.webside.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;



public class AuthUtil {

	public static final String APPID="";
	public static final String APPSECRET="";//授权秘钥
	
	/**
	 * 根据apache 发送hettp请求得到get后entity
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException{
		JSONObject jsonObject=null;
		DefaultHttpClient client=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url);
		HttpResponse  response=client.execute(httpGet) ;
		HttpEntity entity=response.getEntity();
		if (entity !=null){
			String reustl=EntityUtils.toString(entity,"UTF-8");
			jsonObject=(JSONObject) JSONObject.parse(reustl);
		}
		httpGet.releaseConnection();//释放连接	
		return jsonObject;
	}
}
