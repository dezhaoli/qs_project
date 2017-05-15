package com.qs.sync.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataSyncUrl {
	
	 private static Map<String,String> syncSysUrlMap=new HashMap<String,String>();  
	 private static List<String> syncSysCodeList=new ArrayList<String>();  
	 //private static String syscode=ResourceUtil.getValueByKey("init.properties","sync.syscode");
     
     
  
	public static Map<String, String> getSyncSysUrlMap() {
		return syncSysUrlMap;
	}

	public static List<String> getSyncSysCodeList() {
		return syncSysCodeList;
	}

	private static DataSyncUrl instance = null;  
    static {  
    	  instance = new DataSyncUrl();  
    	
    }  
    private DataSyncUrl (){
    /*	
    	String[] sysCodeList=syscode.split(",");
    	if(null!=sysCodeList&&sysCodeList.length>0){
    		 for(String sysCode:sysCodeList){
    		   syncSysCodeList.add(sysCode);
    		   String userSyncKey=sysCode+".user";
    		   String userSyncValue=ResourceUtil.getValueByKey("init.properties",userSyncKey);
    		   syncSysUrlMap.put(userSyncKey,userSyncValue);
    		   String orgSyncKey=sysCode+".org";
    		   String orgSyncValue=ResourceUtil.getValueByKey("init.properties",orgSyncKey);
    		   syncSysUrlMap.put(orgSyncKey,orgSyncValue);
    		   
    		 }
    	}*/

    }
    public static DataSyncUrl getInstance() {  
      return instance;  
    }  
	 
}
