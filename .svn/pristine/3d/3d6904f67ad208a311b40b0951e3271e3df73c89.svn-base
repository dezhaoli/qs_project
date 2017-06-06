package com.qs.webside.api.service.impl;

import com.qs.common.constant.AppConstants;
import com.qs.webside.api.service.IWePayService;
import com.qs.webside.api.service.IWePayService2;
import com.qs.webside.game.service.GameService;

import me.hao0.common.date.Dates;
import me.hao0.wepay.core.Wepay;
import me.hao0.wepay.core.WepayBuilder;
import me.hao0.wepay.model.pay.AppPayResponse;
import me.hao0.wepay.model.pay.PayRequest;
import me.hao0.wepay.model.refund.RefundApplyRequest;
import me.hao0.wepay.model.refund.RefundApplyResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;


@Service
public class WePayServcieImpl2 implements IWePayService2 {


    private Wepay wepay;
    
    @Autowired
	private GameService gameService;

    @PostConstruct
    public void initWepay() {
    	int gp=103;
    	String appId="";
		String appKey="";
		String mchId;
		appId=gameService.getBaseParamValueByCode(AppConstants.BaseParamConfig.weixin_appId+gp);
	    appKey=gameService.getBaseParamValueByCode(AppConstants.BaseParamConfig.weixin_appKey+gp);
		mchId=gameService.getBaseParamValueByCode(AppConstants.BaseParamConfig.weixin_mchId+gp);
		
        wepay = WepayBuilder.newBuilder(appId, appKey, mchId)
                .certPasswd(mchId)
                .build();

        /*try(InputStream in = this.getClass().getClassLoader().getResourceAsStream("cert.p12")) {
            // 加载证书文件
            byte[] certs = ByteStreams.toByteArray(in);
            wepay = WepayBuilder.newBuilder(appId, appKey, mchId)
                    .certPasswd(mchId)
                    .certs(certs)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public AppPayResponse unifiedOrder(HttpServletRequest request) {
        PayRequest payRequest = new PayRequest();
        payRequest.setBody("测试订单");
        payRequest.setClientId("127.0.0.1");
        payRequest.setTotalFee(1);
        payRequest.setNotifyUrl("http://www.xxx.com/notify");
        payRequest.setOutTradeNo("TEST12345678app");
        payRequest.setTimeStart(Dates.now("yyyyMMddHHmmss"));
        AppPayResponse resp = wepay.pay().appPay(payRequest);
        return resp;
    }

    /**
     * 校验签名
     * @param params 参数(包含sign)
     * @return 校验成功返回true，反之false
     */
    @Override
    public Boolean verifySign(Map<String, ?> params){
        return wepay.notifies().verifySign(params);
    }

    /**
     * 通知成功
     */
    @Override
    public String notifyOk(){
        return wepay.notifies().ok();
    }

    /**
     * 通知不成功
     * @param errMsg 错误消息
     */
    @Override
    public String notifyNotOk(String errMsg){
        return wepay.notifies().notOk(errMsg);
    }

    public RefundApplyResponse refundApply(String orderNumber){
        RefundApplyRequest req = new RefundApplyRequest();
        req.setOutTradeNo(orderNumber);
        req.setOutRefundNo(orderNumber);
        req.setTotalFee(1);
        req.setRefundFee(1);
        req.setOpUserId(wepay.getMchId());
        return wepay.refund().apply(req);
    }

    @Override
    public String getPostRequestBody(HttpServletRequest req) {
        if (req.getMethod().equals("POST")) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = req.getReader()) {
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = br.read(charBuffer)) != -1) {
                    sb.append(charBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                //logger.warn("failed to read request body, cause: {}", e.getMessage());
            }
            return sb.toString();
        }
        return "";
    }

}
