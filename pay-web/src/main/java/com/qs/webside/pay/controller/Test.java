package com.qs.webside.pay.controller;


import com.qs.common.pay.PayUtil;

import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.Transfers;
import weixin.popular.bean.paymch.TransfersResult;

public class Test {

	public static void main(String[] args) {
        
		String key="qianshouhudonglewanguangdong5200";
		Transfers transfers=new Transfers();
		transfers.setMch_appid("wx3bac87168a36bf46");
		transfers.setMchid("1442559102");
		transfers.setNonce_str(PayUtil.getNonceStr());
		//transfers.setSign("");
		transfers.setSign_type("HMAC-SHA256");
		transfers.setPartner_trade_no(PayUtil.getTransferNo());
		transfers.setOpenid("ox9wS1DFgQPkbvQzOCrcaEoCNH7I");
		transfers.setCheck_name("NO_CHECK");
		//transfers.setRe_user_name("莫油生"); //可选
		transfers.setAmount("1");
		transfers.setDesc("转账个人");
		transfers.setSpbill_create_ip("127.0.0.1");
		
		
		
		
		TransfersResult tResult=PayMchAPI.mmpaymkttransfersPromotionTransfers(transfers, key);
		
		if(null!=tResult){
			System.out.println("getResult_code=============::"+tResult.getResult_code());
			System.out.println("getReturn_msg=============::"+tResult.getReturn_msg());
		}
		
		
		
	}

}
