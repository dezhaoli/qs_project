package com.qs.pub.sync.constant;

/**
 * 数据同步常量
 * @author moyousheng
 *
 */
public class SyncContans {
	

	public static class ExceptContent{
		public static final String SQL= "sql异常"; //sql异常
		public static final String MQ= "mq异常";//mq异常
		public static final String HTTPCLIENT= "网络异常";//网络异常
	}
	public static class ExceptCode{
		public static final String SQL= "0"; //sql异常
		public static final String MQ= "1";//mq异常
		public static final String HTTPCLIENT= "2";//网络异常
	}
	
	public static class FromSystem{
		public static final String CODE= "ausp"; //统一平台code
		public static final String NAME= "统一平台";//统一平台name
	}
	
	public static class FromTableCode{
		public static final String PLAYING= "SyncPlaying"; //在玩
		public static final String ROOM= "SyncCreateRoom";//创建房间
		public static final String USER_LOGIN= "SyncUserLogin";//创建房间
	}
	
	public static class SaveLogFlag{
		public static final boolean YES= true; //同步
		public static final boolean NO=false;//不同步
	}
	
	
	
	

}

