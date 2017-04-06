package com.qs.webside.sms;

public class CallTest extends UegateSoap {

	/**
	 * 调用类的方法说明：
	 * Submit(spID,	password, accessCode, content, mobileString)： 
	 * 		发送短信，5个参数，必须按照顺序调用
	 * QueryMo(spID, password) 余额以人民币厘单位计算，
	 * 		除以1000为剩余人民币元单位，除以按厘计算的短信价
	 * 		格（比如80）为剩余短信条数。
	 * QueryReport(spID, password)：
	 * 		状态报告查询一次后不再显示。
	 * RetrieveAll(spID, password)：
	 * 		短信回复调用一次后不再显示。
	 */
	
	public static void main(String[] args) {
	//以下信息，根据自己的账户信息修改
	String spID = "000337";
	String password = "2ZwJxd";
	String accessCode = "1069032239089325";
	//发送的短信内容和目标手机号码,多个手机号码以英文逗号隔开。
	String content = "【乐玩互娱】短信验证："+(int)((Math.random()*9+1)*100000)+"（请勿将验证码提供给他人），请在2分钟能完成验证。";
	String mobileString = "13510508616";
	//短信提交
	UegateSoap uegatesoap = new  UegateSoap();
	String submitresult=uegatesoap.Submit(spID,
			password, accessCode, content, mobileString);
	System.out.println(submitresult);
	
	//查询余额
	//String querymoresult=uegatesoap.QueryMo(spID, password);
	//System.out.println(querymoresult);
	
	//状态报告
	//String reportresult=uegatesoap.QueryReport(spID, password);
	//System.out.println(reportresult);
	
	//短信回复
	//String receiveresult=uegatesoap.RetrieveAll(spID, password);
	//System.out.println(receiveresult);
	}

}
