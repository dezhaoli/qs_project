package com.qs.webside.api.service;

import me.hao0.alipay.model.pay.AppPayDetail;
import me.hao0.alipay.model.pay.WapPayDetail;
import me.hao0.alipay.model.pay.WebPayDetail;
import me.hao0.alipay.model.refund.RefundDetail;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/21.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IAlipayService {

    /**
     *web支付
     * @param detail
     * @return
     */
    String webPay(WebPayDetail detail);

    /**
     *  wap支付
     * @param detail
     * @return WAP支付参数
     */
    public String wapPay(WapPayDetail detail);

    /**
     * MD5验证
     * @param params
     * @return
     */
    Boolean notifyVerifyMd5(Map<String, String> params);

    /**
     * 退款申请
     * @param detail
     * @return
     */
    Boolean refund(RefundDetail detail);

    /**
     * App支付
     * @param appPayDetail
     * @return RSA签名后的支付字符串
     */
    String appPay(AppPayDetail appPayDetail);

    /**
     * 支付宝服务器异步通知
     * @param request
     * @return
     */
    String asynchronizationNotify(HttpServletRequest request);

}
