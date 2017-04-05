package com.qs.log.game.controller;

import com.alibaba.fastjson.JSON;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.WeixinPayAccount;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.foxinmy.weixin4j.payment.mch.CorpPayment;
import com.foxinmy.weixin4j.payment.mch.CorpPaymentResult;
import com.foxinmy.weixin4j.type.mch.CorpPaymentCheckNameType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.agent.game.model.MemberAgents;
import com.qs.agent.game.service.IMemberAgentService;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.pay.PayUtil;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.TaxesDirectlyWeek;
import com.qs.log.game.service.ITaxesDirectlyWeekService;
import com.qs.log.game.util.BusinessDateUtil;
import com.qs.webside.pay.controller.PayLogController;
import com.qs.webside.pay.impl.IPayLog;
import com.qs.webside.pay.model.PayLog;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * //@Author:zun.wei, @Date:2017/4/5 11:20
 *  跑得快控制器
 * Created by zun.wei on 2017/4/5.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/runfast/")
public class RunfastController extends BaseController {
	Logger log = Logger.getLogger(RunfastController.class); 
    @Resource
    private ITaxesDirectlyWeekService taxesDirectlyWeekService;
    
    @Resource
    private IMemberAgentService memberAgentService;
    
	@Autowired
	private IPayLog payLog;
    
    
    
    @Value("${runfast.appid}")
    private  String appid;
    
    @Value("${runfast.mchid}")
    private  String mchid;
    
    @Value("${runfast.key}")
    private  String apikey;
    
    @Value("${runfast.certfile}")
    private  String certfile;
    


    /**
     * 代理商周信息统计入口
     */
    @RequestMapping(value = "agentWeekInfoStaUi.html", method = RequestMethod.GET)
    public String agentWeekInfoStaUi(Model model, HttpServletRequest request) {
        try {
            Map<String, List<String>> date = BusinessDateUtil.getAgentInfoDateTime();
            String json = JSON.toJSONString(date);
            List<String> keys = new ArrayList<String>();
            Set<String> keySet = date.keySet();
            Iterator<String> ki = keySet.iterator();
            while (ki.hasNext()) {
                String key = ki.next();
                keys.add(key.substring(1));
            }
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            model.addAttribute("years", keys);
            model.addAttribute("jsonDate", json);
            model.addAttribute("lastMonday", BusinessDateUtil.getLastWeekMonday());
            model.addAttribute("lastSunday", BusinessDateUtil.getLastWeekSunday());
            model.addAttribute("gameType", "runfast");
            return "/WEB-INF/view/web/agent_weekStaInfo_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }


    @RequestMapping("agentWeekInfoSta.html")
    @ResponseBody
    public Object agentWeekInfoSta(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("mid", null);
            return null;//如果没有mid传过来则不执行查询。
        }
        String date = parameters.get("searchDate") + "";
        if ("null".equals(date) || StringUtils.isBlank(date)) {
            parameters.put("searchDate", BusinessDateUtil.getLastWeekSunday());
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = taxesDirectlyWeekService.getWeekPayinfoByDate(parameters);
        return getReturnPage(pager, page, list);
    }
    
    @RequestMapping("pay.html")
    @ResponseBody
    public Object pay(String gridPager) throws Exception {
    	return null;
    }

    @ResponseBody
    @RequestMapping(value = "confirmPay.html")
    public Object confirmPay(String openid, Date date, Integer mid, HttpServletRequest request) throws WeixinException {
    	TaxesDirectlyWeek tdwRecord=new TaxesDirectlyWeek();
    	 Map<String, Object> map=new HashMap<String, Object>();
    	tdwRecord.setMid(mid);
    	tdwRecord.setDate(date);
    	TaxesDirectlyWeek qRecord=taxesDirectlyWeekService.findTaxesDirectlyWeekByCondition(tdwRecord);
    	if(null==qRecord){
    		 this.executeRequestResult(0, map);
    		 return map;
    	}
    	if(qRecord.getIsaward()!=1){
    		 this.executeRequestResult(0, map);
    		 return map;
    	}
    	
    	if(qRecord.getRebatetotal()<1){
   		 this.executeRequestResult(0, map);
   		 return map;
   	   }
    	MemberAgents agent=memberAgentService.findByMid(mid);
    	
    	if(null==agent){
    		 this.executeRequestResult(0, map);
    		 return map;
    	}
    	String ip=PayUtil.getLocalIp(request);
    	if(null!=ip&&ip.contains("0:0:0:0:0:0:0:1")){
    	 ip="127.0.0.1";
    	}
		CorpPayment payment = new CorpPayment(PayUtil.getTransferNo(),openid,
				CorpPaymentCheckNameType.NO_CHECK, "微信支付", qRecord.getRebatetotal(),ip);
		
	     WeixinPayProxy PAY=null;
		 WeixinPayAccount ACCOUNT=null;
		
		 ACCOUNT = new WeixinPayAccount(appid,apikey,mchid,"",certfile);
		 PAY = new WeixinPayProxy(ACCOUNT);

		CorpPaymentResult result = PAY.sendCorpPayment(payment);
		System.out.println("getReturnCode=============::"+result.getReturnCode());
		int c=0;
		if(null!=result&&"SUCCESS".equals(result.getReturnCode())){
			
			TaxesDirectlyWeek updateRecord=new TaxesDirectlyWeek();
			updateRecord.setMid(qRecord.getMid());
			updateRecord.setDate(qRecord.getDate());
			c=taxesDirectlyWeekService.updateIsawardByCondition(updateRecord);
			if(c>0){
				//日志
				PayLog log=new  PayLog();
				log.setMid(qRecord.getMid());
				log.setAddDate(qRecord.getDate());
				log.setGameType(1);
				log.setRebatetotal(qRecord.getRebatetotal());
				log.setStatus(1);
				log.setIp(ip);
				log.setcCreateTime(new Date());
				log.setExtend1(agent.getOpenid());
				int logc=payLog.insert(log);
			}
		}
   
		 this.executeRequestResult(c, map);
		 return map;
    }

}
