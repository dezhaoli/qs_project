package com.qs.webside.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.NameValuePair;
import com.qs.common.util.HttpClientUtil;

/**
 * 
 * @ClassName: ExecutorThreadUtil 
 * @描述: 多线程工具
 * @author moyousheng
 * @date 2017年5月18日 下午8:10:26
 */
public class ExecutorThreadUtil {
    /**
     * 日志对象
     */
    private static  ExecutorService execThread=Executors.newFixedThreadPool(10);  	

	private static void sendLog(String a) {
		httpClientSyncErrorByThread(a);	
	}


		/**
		 * 多线程
		 * @param record
		 */
	  private static void httpClientSyncErrorByThread(final String a){
			
			execThread.execute(new Runnable(){
				public void run() {
					try {
						httpClientSaveErrorJson(a);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		private static void httpClientSaveErrorJson(String a) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
			/* JSONObject jsonObject = getJsonObject(record);
			 nvps.add(new BasicNameValuePair("id",ID.generateUUID()));  
			 nvps.add(new BasicNameValuePair("sourceCode",FromSystem.CODE));
			 nvps.add(new BasicNameValuePair("moduleCode",simpleName));
			 nvps.add(new BasicNameValuePair("content",jsonObject.toString()));
			//失败标示
			 nvps.add(new BasicNameValuePair("status","0")); 
			 nvps.add(new BasicNameValuePair("exception",SyncContans.ExceptContent.MQ));
			 //mq异常
			 nvps.add(new BasicNameValuePair("excType",SyncContans.ExceptCode.MQ));
			 */
			 //HttpClient请求 
			 HttpClientUtil.httpClientByPost("",nvps);
		}
		

		
	/*	private static JSONObject getJsonObject(SyncObject record) {
			  JSONObject jsonObject =null;
				if(record instanceof SyncUser){
					SyncUser user = (SyncUser)record;
					JsonConfig config = MyJsonConfig.getJsonConfig();
					jsonObject= JSONObject.fromObject(user,config);  
				}else if(record instanceof SyncOrganization){
					SyncOrganization org = (SyncOrganization)record;
					JsonConfig config = MyJsonConfig.getJsonConfig();
					jsonObject= JSONObject.fromObject(org,config);  
				}
			return jsonObject;
		}*/
}
