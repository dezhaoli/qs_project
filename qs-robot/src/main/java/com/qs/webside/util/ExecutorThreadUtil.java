package com.qs.webside.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	  public static void httpClientSyncErrorByThread(final Object obj){
			
			execThread.execute(new Runnable(){
				public void run() {
					try {
						httpClientSaveErrorJson(obj);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		private static void httpClientSaveErrorJson(Object obj) {
				String res = HttpClientUtil.httpClientByPost("http://datacenter.longzupoker.com:8888/datacenter/dataCenter/dataCenterLogDispatch.html",obj);
				//System.out.println(res);
		}
		
		
		public static void main(String[] args)
		{  
			Map<String, Object> loginLogs = new HashMap<String, Object>();
			loginLogs.put("logType", "3");
			loginLogs.put("mid","1");
			loginLogs.put("appVersion", "");
			loginLogs.put("appId", 9);
			loginLogs.put("terminalType", "");
			loginLogs.put("ip", "");
			loginLogs.put("channelId", "");
			loginLogs.put("loginTime", new Date());
			loginLogs.put("logoutTime", new Date());
			httpClientSyncErrorByThread(loginLogs);
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
