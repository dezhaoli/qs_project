package com.qs.log.game.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.qs.agent.game.service.IMemberAgentService;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.service.ITaxesInviteWeekService;
import com.qs.log.game.util.BusinessDateUtil;
import com.qs.log.game.util.ConstantUtil;
import com.qs.pub.pay.service.IPayLogService;

/**
 * 自定义支付
 * @author moys
 *
 */
@Controller
@RequestMapping(value = "/simplePay/")
public class SimplePayController extends PayBaseController {

	Logger log = Logger.getLogger(SimplePayController.class);
    @Resource
	private ITaxesInviteWeekService taxesInviteWeekService;
    
    @Resource
    private IMemberAgentService memberAgentService;
    
	@Autowired
	private IPayLogService payLog;
    
    
    /**
     * 代理商自定义支付列表UI
     */
    @RequestMapping(value = "agentSimplePayUi.html", method = RequestMethod.GET)
    public String agentSinglePayUi(Model model, HttpServletRequest request) {
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
           // model.addAttribute("gameType", ConstantUtil.GameTypeConstant.Run_Fast);
            return "/WEB-INF/view/web/agent_simplePay_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
    
    
 


}
