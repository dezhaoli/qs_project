package com.qs.webside.api.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.common.constant.AppConstants;
import com.qs.common.constant.AppConstants.NotifyMsg;
import com.qs.webside.api.service.IAlipayService;
import com.qs.webside.api.service.IWePayService;
import com.qs.webside.member.model.Memberpayment;
import com.qs.webside.member.service.MemberService;
import com.qs.webside.member.service.PaymentService;

import me.hao0.alipay.model.enums.AlipayField;
import me.hao0.alipay.model.enums.TradeStatus;
import me.hao0.wepay.util.Maps;


@Controller
@RequestMapping(value = "/api/pay/")
public class PayNotifyController {


    @Resource
    private IWePayService wePayService;
    @Resource
    private IAlipayService alipayService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PaymentService paymentService;
	
	
	
    private static final Logger logger = LoggerFactory.getLogger(PayNotifyController.class);



    /**
     * 支付成功通知
     * @param request 请求对象
     * @return 处理结果
     */
    @RequestMapping("/wxNotify.do")
    public String wxNotify(HttpServletRequest request){
    	logger.debug("wxNotify.do===:"+request.toString());
        String notifyXml = wePayService.getPostRequestBody(request);
        boolean resultFlag=true;
        String msg="";
        if (notifyXml.isEmpty()){
            return wePayService.notifyNotOk(AppConstants.NotifyMsg.BODY_ISNULL);
        }
        Map<String, Object> notifyParams = Maps.toMap(notifyXml);
        if(null==notifyParams){
        	 return wePayService.notifyNotOk(AppConstants.NotifyMsg.PARAM_ISNULL);
        }
         //发货流程：只有根据商户订单号找到该条信息
       
         //金额不对
        
        if (wePayService.verifySign(notifyParams)){
            logger.info("verify sign success: {}", notifyParams);
            String pidStr=(String)notifyParams.get("out_trade_no");
            int pid=0;
            if(!StringUtils.isBlank(pidStr)){
            	pid=Integer.parseInt(pidStr);
            }
            String totalFeeStr=(String)notifyParams.get("total_fee");
            int totalFee=0;
            if(!StringUtils.isBlank(totalFeeStr)){
            	totalFee=Integer.parseInt(totalFeeStr);
            }
            //重复发货
            Memberpayment orderPay=paymentService.findMemberpaymentById(pid);
            if(null==orderPay){
            	logger.error("wxNotify.do===订单不存在");
            	return wePayService.notifyNotOk("订单不存在");
            }
            if(orderPay.getPstatus()==2){
            	 //重复发货
            	 return wePayService.notifyOk();
            }
            
            if(null!=orderPay.getPamount()&&orderPay.getPamount().intValue()!=totalFee){
            	logger.error("wxNotify.do===金额不对");
           }
            
            byte pstatus=2;
            String ptransno=(String)notifyParams.get("transaction_id");
            int c=paymentService.updateFinishMemberpayment(pid, pstatus, ptransno);
            if(c>0){
            	return wePayService.notifyOk();
            }else{
            	logger.error("wxNotify.do===支付失败::"+c);
            }
            
            return wePayService.notifyOk();
        } else {
            logger.error("verify sign failed: {}", notifyParams);
            return wePayService.notifyNotOk("签名失败");
        }
    }
    
    /**
     *  支付结果异步通知
     *  此Url是在alipay.properties中配置的
     * @param request 支付宝支付结果异步通知post请求
     * @return  按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，
     *      校验成功后在response中返回success，校验失败返回failure
     */
    @ResponseBody
    @RequestMapping(value = "/aliNotify.do",method = RequestMethod.POST)
    public String aliPayNotify(HttpServletRequest request) {
    	logger.debug("aliNotify.do===:"+request.toString());
        Map<String, String> notifyParams = new HashMap<>();

        Enumeration pNames=request.getParameterNames();
        while(pNames.hasMoreElements()){
            String name=(String)pNames.nextElement();
            String value=request.getParameter(name);
            logger.debug(name + "=" + value);
            notifyParams.put(name,value);
        }
        logger.info("backend notify params: {}", notifyParams);
        if (!alipayService.notifyVerifyMd5(notifyParams)){
            logger.error("backend sign verify failed");
            return "FAIL";
        }

        String tradeStatus = notifyParams.get(AlipayField.TRADE_STATUS.field());
        if (TradeStatus.TRADE_FINISHED.value().equals(tradeStatus)
                || TradeStatus.TRADE_SUCCESS.value().equals(tradeStatus)){
               //交易成功
        	   String out_trade_no=(String)notifyParams.get("out_trade_no");
        	   int pid=Integer.parseInt(out_trade_no);
        	   byte pstatus=2;
               String ptransno=(String)notifyParams.get("trade_no");
               int c=paymentService.updateFinishMemberpayment(pid, pstatus, ptransno);
        }

        logger.info("backend notify success");
        return "SUCCESS";
        
    }



}
