package com.qs.log.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.service.ITaxesDirectlyWeekService;
import com.qs.log.game.util.BusinessDateUtil;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(value = "/majiang/")
public class MajiangController extends BaseController {

    @Resource
    private ITaxesDirectlyWeekService taxesDirectlyWeekService;

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
            model.addAttribute("gameType", "majiang");
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

    @ResponseBody
    @RequestMapping(value = "confirmPay.html")
    public Object confirmPay(String openid, Date date, Integer mid) {
        System.out.println("openid = " + openid);
        System.out.println("date = " + date);
        System.out.println("mid = " + mid);
        System.out.println("\"majiang\" = " + "majiang");
        return null;
    }

}
