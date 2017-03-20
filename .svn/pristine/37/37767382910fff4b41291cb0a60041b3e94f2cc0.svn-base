package com.qs.webside.api.service.impl;

import com.qs.webside.api.service.IAlipayService;
import me.hao0.alipay.core.Alipay;
import me.hao0.alipay.core.AlipayBuilder;
import me.hao0.alipay.model.enums.AlipayField;
import me.hao0.alipay.model.enums.TradeStatus;
import me.hao0.alipay.model.pay.AppPayDetail;
import me.hao0.alipay.model.pay.WapPayDetail;
import me.hao0.alipay.model.pay.WebPayDetail;
import me.hao0.alipay.model.refund.RefundDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝业务层
 *
 * Created by zun.wei on 2017/2/21.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Service
public class AlipayServiceImpl implements IAlipayService{

    private static final Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Value("${alipay.merchantId}")
    private String merchantId;

    @Value("${alipay.secret}")
    private String secret;

    @Value("${alipay.payNotifyUrl}")
    private String payNotifyUrl;

    @Value("${alipay.refundNotifyUrl}")
    private String refundNotifyUrl;

    @Value("${alipay.webReturnUrl}")
    private String webReturnUrl;

    @Value("${alipay.wapReturnUrl}")
    private String wapReturnUrl;

    @Value("${alipay.appReturnUrl}")
    private String appReturnUrl;

    private Alipay alipay;

    @PostConstruct
    public void initAlipay(){
        alipay = AlipayBuilder
                .newBuilder(merchantId, secret)
                .build();

        System.err.println(alipay);
    }


    @Override
    public String webPay(WebPayDetail detail) {
        detail.setNotifyUrl(payNotifyUrl);
        detail.setReturnUrl(webReturnUrl);
        return alipay.pay().webPay(detail);
    }

    @Override
    public String wapPay(WapPayDetail detail) {
        detail.setNotifyUrl(payNotifyUrl);
        detail.setReturnUrl(wapReturnUrl);
        return alipay.pay().wapPay(detail);
    }

    @Override
    public Boolean notifyVerifyMd5(Map<String, String> params) {
        return alipay.verify().md5(params);
    }

    @Override
    public Boolean refund(RefundDetail detail) {
        detail.setNotifyUrl(refundNotifyUrl);
        return alipay.refund().refund(detail);
    }

    @Override
    public String appPay(AppPayDetail appPayDetail) {
        //TODO  下两行为sdk没有的，自己加的。
        appPayDetail.setNotifyUrl(payNotifyUrl);
        //appPayDetail.setReturnUrl(appReturnUrl);
        return alipay.pay().appPay(appPayDetail);
    }

    @Override
    public String asynchronizationNotify(HttpServletRequest request) {
        Map<String, String> notifyParams = new HashMap<>();

        // TODO 这里还是建议直接从request中获取map参数，兼容支付宝修改或增减参数
        /*for (AlipayField f : AlipayFields.APP_PAY_NOTIFY){
            notifyParams.put(f.field(), request.getParameter(f.field()));
        }*/

        Enumeration pNames=request.getParameterNames();
        while(pNames.hasMoreElements()){
            String name=(String)pNames.nextElement();
            String value=request.getParameter(name);
            logger.debug(name + "=" + value);
            notifyParams.put(name,value);
        }
        logger.info("backend notify params: {}", notifyParams);
        if (!this.notifyVerifyMd5(notifyParams)){
            logger.error("backend sign verify failed");
            return "FAIL";
        }

        String tradeStatus = notifyParams.get(AlipayField.TRADE_STATUS.field());
        if (TradeStatus.TRADE_FINISHED.value().equals(tradeStatus)
                || TradeStatus.TRADE_SUCCESS.value().equals(tradeStatus)){
            // 交易成功
            // TODO business logic
        }

        logger.info("backend notify success");
        return "SUCCESS";
    }


}
