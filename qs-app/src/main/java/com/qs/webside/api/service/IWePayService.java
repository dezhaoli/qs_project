package com.qs.webside.api.service;

import me.hao0.wepay.model.pay.AppPayResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * Created by zun.wei on 2017/2/22.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
public interface IWePayService {

    /**
     * 微信统一下单
     * @param request HttpServletRequest
     * @return app下单的订单号等
     */
    AppPayResponse unifiedOrder(HttpServletRequest request);

    /**
     * 校验签名
     * @param params 参数(包含sign)
     * @return 校验成功返回true，反之false
     */
    Boolean verifySign(Map<String, ?> params);

    /**
     * 通知成功
     */
    String notifyOk();

    /**
     * 通知不成功
     * @param errMsg 错误消息
     */
    String notifyNotOk(String errMsg);

    String getPostRequestBody(HttpServletRequest req);

}
