import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.qs.pub.datacenter.model.UserAddLog;

/*
 * 文件名：HttpClient.java	 
 * 时     间：下午3:21:07
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
/** 
 * @ClassName: HttpClient 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月18日 下午3:21:07 
 */
public class HttpClient
{
	 public static  void main(String arg0[]){
	   CloseableHttpClient httpclient = HttpClients.createDefault();  
	   
	   HttpPost httppost = new HttpPost("http://192.168.1.92:8080/qs-data-center/dataCenter/dataCenterLogDispatch.html");  
	   
	   List nvps = new ArrayList ();
	   nvps.add(new BasicNameValuePair("bankPhone", "18701320082"));
	   nvps.add(new BasicNameValuePair("cardNum", "655879584265456887"));
	   nvps.add(new BasicNameValuePair("idNum", "131081199008041827"));
	   nvps.add(new BasicNameValuePair("openingBank", "中信银行"));
	   nvps.add(new BasicNameValuePair("branchBank", "东湖渠支行"));
	   nvps.add(new BasicNameValuePair("userName", "樊丽丽"));
	   
	   
	   UserAddLog user = new UserAddLog();
	   user.setAppName("长城");
	   user.setAppVersion("1.2.1");
	   String json = JSON.toJSONString(user);
	  
//	    StringEntity stringEntity = new StringEntity(json, "UTF-8");
	//   stringEntity.setContentType("application/x-www-form-urlencoded");
	//   httppost.setEntity(stringEntity);
	   try {  
	    httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
	    CloseableHttpResponse response = httpclient.execute(httppost);  
	         HttpEntity myEntity = response.getEntity();  
	         System.out.println(myEntity.getContentType());  
	         System.out.println(myEntity.getContentLength());  
	        
	         String resString = EntityUtils.toString(myEntity);  
	         String result = JSON.toJSONString(resString);
	         System.out.println("result" + result);
	             // 使用返回的字符串直接构造一个JSONObject       
//	             JSONObject jsonobj = new JSONObject(resString);  
//	            System.out.println(jsonobj.toString());  
//	            // 获取返回对象中"resultSize的值"  
//	              int resutltSize = jsonobj.getInt("resultSize");  
//	             System.out.println("Search Results Size is: "+ resutltSize);   
//	            // 获取"clients"的值,它是一个JSONArray  
//	             JSONArray jsonarray = jsonobj.getJSONArray("clients");  
//	             System.out.println(jsonarray.toString()); 

	 }catch(Exception e){
	  
	 }
	 }}
