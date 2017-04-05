package com.qs.webside.pay.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.WeixinPayAccount;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.foxinmy.weixin4j.payment.mch.CorpPayment;
import com.foxinmy.weixin4j.payment.mch.CorpPaymentResult;
import com.foxinmy.weixin4j.sign.WeixinPaymentSignature;
import com.foxinmy.weixin4j.type.mch.CorpPaymentCheckNameType;

import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.Transfers;
import weixin.popular.bean.paymch.TransfersResult;

public class Test2 {
	
	protected final static WeixinPayProxy PAY;
	protected final static WeixinPayAccount ACCOUNT;
	
	static {
		ACCOUNT = new WeixinPayAccount(
				"wx61ee2364e54b4b59",
				"Qianshoupaohuzi20160817yidongban",
				"1379437602","","D:/java/myHome/pay-web/src/main/webapp/pay/runfast/apiclient_cert.p12");
		PAY = new WeixinPayProxy(ACCOUNT);
	}

	public static void main(String[] args) {
        
		CorpPayment payment = new CorpPayment("MP002",
				"oJ00owlKur24Ubnp1QfDcL1lziTA",
				CorpPaymentCheckNameType.NO_CHECK, "企业付款测试", 1d, "127.0.0.1");
		try {
			CorpPaymentResult result = PAY.sendCorpPayment(payment);
			
			System.out.println("result=============::"+result);
			System.out.println("getResultCode=============::"+result.getResultCode());
			System.out.println("getReturnMsg=============::"+result.getReturnMsg());
			System.out.println("getReturnCode=============::"+result.getReturnCode());
			
		} catch (WeixinException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
