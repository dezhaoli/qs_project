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
		public static final String OPEN_SESSION_KEY = "ROPEN|";//代开房
		public static final String TMGMCOM = "TMGMCOM";  //金币信息
		public static final String TMFIELD = "TMFIELD"; //用户信息
		
		public static final String CLUBS = "CLUBS|"; //添加俱乐部
		public static final String CLUBSAUTH = "CLUBSAUTH|";//俱乐部代开房
		public static final String CLUBLISTS ="CLUBLISTS|"; //俱乐部当前最新十条记录
	
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
		
		public static final String APP_VERSION = "version:";
		
		
		public static final String GAME_STOP_NOTICE_CACHE = "gameStopNotice:";
		//白名单设备列表
		public static final String MEMBER_WHITE_DEVICE_CACHE = "memberWhite:devicelist";
		//白名单禁用列表
		public static final String MEMBER_WHITE_DISABLE_LIST_CACHE = "memberWhite:disableList";

		//分享链接进入房间信息
		public static final String SHARE_LINK_JOIN_ROOM_INFO_CACHE = "shareLinkInfo:";

		//分享链接进入房间信息刷新token
		public static final String SHARE_LINK_REFRESH_TOKEN = "shareListRefreshToken:";
	}
	
	public static class NotifyMsg{
		public static final String BODY_ISNULL ="body为空";
		public static final String PARAM_ISNULL ="参数为空";
		public static final String IOS ="2";
	}
	
	/**
	 * 苹果支付地址
	 * @author moys
	 *
	 */
	public static class ApplePay{
		public static final String BUY ="https://buy.itunes.apple.com/verifyReceipt";
		//沙箱
		public static final String SANDBOX ="https://sandbox.itunes.apple.com/verifyReceipt";
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
		public static final int  FAILURE_1010=1010;
		public static final int  FAILURE_1011=1011;
		public static final int  FAILURE_1012=1012;
		public static final int  FAILURE_1013=1013;
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
		public static final String TITME_IS_OVER ="请检查手机时间";
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
		public static final int  BUIN_ADD_GOLD=13;//商务金币添加来源
		public static final int  GACKGROUND_ADD_GOLD=7;//后台添加金币
		public static final int  ACTIVITY_SHARE_ADD_GOLD=20;//活动中心分享送房卡
		public static final int  ACTIVITY_COMMENT_ADD_GOLD=21;//活动中心评论送房卡
		public static final int INTEGRAL_SEND_GOLD = 22;//活动积分兑换
		public static final int ACTIVITY_LOGIN_ADD_GOLD = 23;//活动中心登录送房卡
		public static final int  BIG_TURNTABLE_LOTTERY_ADD_GOLD = 24;//活动中心大转盘送房卡房卡
	}

	public static class ActivityCenter{
		public static final int  ACTIVITY_SHARE_SEND_GOLD=1;//活动中心分享送房卡数量
		public static final int  ACTIVITY_COMMENT_SEND_GOLD=3;//活动中心评论送房卡数量
	}

	public static class ActivityType{//活动类型常量
		public static final int SHARE_LINK_SEND_GOLD_TYPE = 1;//分享链接送金币类型为1
		public static final int COMMENT_SEND_GOLD_TYPE = 2;//好评有奖
		public static final int INTEGRAL_ACTIVITY_TYPE = 5;//积分活动
		public static final int FATHER_DAY_ACTIVITY_TYPE = 7;//父亲节活动
		public static final int SHARE_POINTS_ACTIVITY_TYPE = 8;//分享送积分活动
		public static final int DOUBLE_INTEGRAL_ACTIVITY_TYPE = 9;//双倍积分
		public static final int INTEGRAL_WELFARE_ACTIVITY_TYPE = 10;//积分福利
		public static final int RECHARGE_PRIVILEGE_ACTIVITY_TYPE = 11;//积分福利
		public static final int LOG_IN_TO_ROOM_CARD_TYPE = 12;//登录送房卡
		public static final int ELEVEN_BIG_TURNTABLE_ACTIVITY_TYPE = 13;//十一大转盘

	}


	/**
	 * 支付类型
	 * @author moys
	 *
	 */
	public static class PayType{
		public static final String ALIPAY = "1";
		public static final String APPLEPAY = "4";
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
		//安卓审核版本号
		public static final String  ANDROID_UPD_VERSION_CODE = "android_upd_version";
		//ios审核版本号
		public static final String  IOS_UPD_VERSION_CODE = "ios_upd_version";
		//配置文件
		public static final String  CONFIG_VERSION_CODE = "config_version"; 
	   //绑定送金币
	   public static final String   BINDSEND_GOLD_CODE = "bindsend_gold_code"; 
	   //注册送金币
	   public static final String   REGISTERSEND_GOLD_CODE = "registersend_gold_code"; 
	   
	   //是否打开苹果商城1打开 0关闭 
	   public static final String   IS_OPEN_APPLE_STORE = "is_open_apple_store";  
	   
	   //取memcached_ip
	   public static final String   MEMCACHED_IP = "memcached_ip";

		//活动中心客户端跳转url
		public static final String   ACTIVITY_CENTER_CLIENT_URL = "activity_center_client_url";

		//机器人app url
		public static final String   ROBOT_APP_URL = "robot_app_url";

		//活动中心调用app应用发送金币url接口
		public static final String   APP_SEND_GOLD_INTERFACE_URL = "app_send_gold_url";

		//分享链接进入房间回调url地址
		public static final String   SHARE_LINK_JOIN_ROOM_REDIRECT_URL = "share_link_join_room_redirect_url";

		//分享链接进入房间appid
		public static final String   SHARE_LINK_JOIN_ROOM_APP_ID = "share_link_join_room_appid";

		//分享链接进入房间appSecret
		public static final String   SHARE_LINK_JOIN_ROOM_APP_SECRET = "share_link_join_room_app_secret";
		
		//android apk下载地址
		public static final String ANDROID_APK_DOWNLOAD_URL="android_apk_download_url";

	}
	
	
	public static class  IpType{
		//非某省
		public static final String  N1 = "N1"; 
		public static final String  N2 = "N2"; 
		public static final String  N3 = "N3"; 
		//级别1
		public static final String  L1 = "L1"; 
		public static final String  L2 = "L2"; 
		public static final String  L3 = "L3"; 
		public static final String  L4 = "L4"; 
		public static final String  L5 = "L5"; 
		public static final String  L6 = "L6"; 
		public static final String  L7 = "L7"; 
		public static final String  L8 = "L8"; 
		public static final String  L9 = "L9"; 
		public static final String  L10 = "L10"; 
		public static final String  L11 = "L11"; 
		public static final String  L12 = "L12"; 
	
	}
	/**
	 * 基本参数登录、支付配置
	 * @author moys
	 *
	 */
	public static class  BaseParamConfig{
		//appId
		public static final String  weixin_appId= "weixin_appId_"; 
       //appSecret
		public static final String  weixin_appSecret= "weixin_appSecret_"; 
       //appKey
		public static final String  weixin_appKey = "weixin_appKey_"; 
	   //weixin_mchId
	   public static final String  weixin_mchId = "weixin_mchId_"; 
	   //微信支付回调地址
	   public static final String  weixin_pay = "weixin_pay_"; 
			
		


	
	}
	/**
	 * 短信redis 缓存key值
	 * @author zyy
	 *
	 */
	public static class RedisKeySMS{
		//短信key
		public static final String REFRESH_SMS_TOKEN = "refreshsmstoken:";
	}
}
