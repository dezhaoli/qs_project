package com.qs.common.constant;

public class AppConstants {
	
	public static  String VISITORNAME = "游客";//名称
	public static  String COINNAME = "金币";//名称
	public static  String SAFECODE = "DKFAJ@#$AM$%#@JA)&&635";
	public static  String SIGN_KEY = "D9%J@#$A$%#@JA&&635";
	
	//
	public static class Platform{
		public static final String ANDROID ="1";
		public static final String IOS ="2";
	
	}
	
	
	public static class MemcacheKeyPrefix{
		public static final String SESSKEY = "SESSKEY";
		public static final String IP = "IP";
	
	}
	
	public static class RedisExpire{
		public static final  int EXPIRE_DATE = 7;
		public static final  int USER_WEIXIN_EXPIRE_DATE = 15;
		public static final  int MEMBER_GAME_EXPIRE_DATE = 15;;
		
	
	}
	
	public static class RedisKeyPrefix{
		public static final String REFRESH_TOKEN = "refreshtoken:";
		public static final String USER_CACHE = "user:";
		//游戏微信关联表
		public static final String USER_WEIXIN_CACHE = "userweixin:";
		
		public static final String MEMBER_GAME_CACHE = "membergame:";
		
	
	}
	
	public static class NotifyMsg{
		public static final String BODY_ISNULL ="body为空";
		public static final String PARAM_ISNULL ="参数为空";
		public static final String IOS ="2";
	}
	
	public static class Result{
		public static final int SUCCESS =1;

		public static final int  FAILURE =1001;
		public static final int  FAILURE_1 =1;
		public static final int  FAILURE_3 =3;
		public static final int  FAILURE_1002=1002;
		public static final int  FAILURE_1003=1003;
		public static final int  FAILURE_1004=1004;
		public static final int  FAILURE_1005=1005;
		public static final int  FAILURE_1006=1006;
		public static final int  FAILURE_1007=1007;
		public static final int  FAILURE_1008=1008;
		public static final int  FAILURE_1009=1009;
		public static final int  FAILURE_1102=1102;
		
	}
	
	public static class ResultMsg{
		public static final String NO_GP ="no gp";
		public static final String NO_OPENID ="no openid";
		public static final String NO_SESSKEY ="no sekey";
		public static final String PARA_SESSKEY_ISNULL ="param sekey is null";
		public static final String NO_MID ="no mid";
		
		public static final String SIGN_IS_NULL="sg001";
		public static final String SIGN_IS_ERROR ="sg002";
		public static final String SIGN_KEY_IS_ERROR ="sg003";
		public static final String SYSTEM_ERROR ="sg004";
		//时间过期
		public static final String TITME_IS_OVER ="tm limt";
		//时间为空
		public static final String TITME_IS_NULL ="tm is null";

		
	}
	
	public static class GoldLogSourceType{
		public static final int BUY =1;  //充值购买
		public static final int  EVERYDAY_LOGIN=2;//每天登录
		public static final int  VIP_LOGIN=3;//VIP每日登陆
		public static final int  CREATE_ROOM=4;//生成房间消耗
		public static final int  BACK_ROOM=5;//退还房间费用
		public static final int  BIND_AWARD=6;//绑定奖励
		public static final int  EMAIL_SEND=16;//邮件赠送
		public static final int  ADD_USER_SEND=8;//初始赠送
		
	}
	
	/**
	 * 支付类型
	 * @author moys
	 *
	 */
	public static class PayType{
		public static final String ALIPAY = "1";
		public static final String WXPAY = "6";
	
	}
	/**
	 * app版本
	 * @author moys
	 *
	 */
	public static class  AppVersionType{
		public static final String  STRONG = "1";
		public static final String  FORMAL = "2";
		public static final String TEST = "3";
	
	}
	
	/**
	 * 参数表
	 * @author moys
	 *
	 */
	public static class  BaseParam{
		//安卓审核版本号
		public static final String  ANDROID_VERSION_CODE = "android_version"; 
		//ios审核版本号
		public static final String  IOS_VERSION_CODE = "ios_version"; 
		//配置文件
		public static final String  CONFIG_VERSION_CODE = "config_version"; 
	   //绑定送金币
	   public static final String   BINDSEND_GOLD_CODE = "bindsend_gold_code"; 
	   //注册送金币
	   public static final String   REGISTERSEND_GOLD_CODE = "registersend_gold_code"; 
	
	}
	
}
