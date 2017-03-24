package com.qs.webside.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.service.ITaxesInviteService;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.webside.agent.service.IMemberPayMentService;
import com.qs.webside.member.model.MemberPayMent;
import com.qs.webside.shiro.ShiroAuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *  代理商团队充值统计
 * Created by zun.wei on 2017/3/24.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/business/")
public class AgentTeamCharStaController extends BaseController {

    @Resource
    private ITaxesInviteService taxesInviteService;
    @Resource
    private IMemberPayMentService memberPayMentService;

    @RequestMapping(value = "agentTeamRechargeStatisticsUi.html",method = RequestMethod.GET)
    public String agentTeamRechargeStatisticsUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            return "/WEB-INF/view/web/business/agent_teamCharSta_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }



    @RequestMapping("agentTeamRechargeStatistics.html")
    @ResponseBody
    public Object agentTeamRechargeStatistics(String gridPager) throws Exception {
        MemberBusiness memberBusiness = ShiroAuthenticationManager.getBusiness();
        Integer nowUserId = null;
        if (memberBusiness != null) {
            nowUserId = memberBusiness.getId();
        }
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("mid", null);
            return null;//如果没有mid传过来则不执行查询。
        }
        parameters.put("belongid", nowUserId);// TODO 这里因为没有数据了，默认一个人。
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String, Object>> list = taxesInviteService.agentTeamRechargeStatistics(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * 充值明细入口
     * @param model
     * @return
     */
    @RequestMapping(value = "user/payDetailUi.html", method = RequestMethod.GET)
    public String payDetailUi(Model model, Integer mid,String startTime,String endTime,HttpServletRequest request) {
        model.addAttribute("mid", mid);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        PageUtil page = new PageUtil(request);
        model.addAttribute("page", page);
        return "WEB-INF/view/web/business/agent_pay_detail_list";
    }

    @ResponseBody
    @RequestMapping(value = "user/payDetail.html", method = RequestMethod.POST)
    public Object payDetail(String gridPager) {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("mid", null);
            return null;//如果没有mid传过来则不执行查询。
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<MemberPayMent> list = memberPayMentService.queryListByMidDate(parameters);
        return getReturnPage(pager, page, list);
    }

}
