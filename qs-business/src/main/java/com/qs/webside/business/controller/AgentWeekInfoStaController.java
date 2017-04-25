package com.qs.webside.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.service.ITaxesDirectlyWeekService;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.service.IAppGameService;
import com.qs.webside.util.BusinessDataSourceUtil;
import com.qs.webside.util.BusinessDateUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 代理商周信息统计控制器
 * <p>
 * Created by zun.wei on 2017/3/22.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/business/")
public class AgentWeekInfoStaController extends BaseController {

    // /business/agentWeekInfoStaUi.html
    @Resource
    private ITaxesDirectlyWeekService taxesDirectlyWeekService;
    @Resource
    private BusinessDataSourceUtil businessDataSourceUtil;
    @Resource
    private IAppGameService appGameService;

    /**
     * 代理商周信息统计入口
     */
    @RequestMapping(value = "agentWeekInfoStaUi.html", method = RequestMethod.GET)
    public String goldOriginUi(Model model, HttpServletRequest request) {
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
            return "/WEB-INF/view/web/business/agent_weekStaInfo_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }


    @RequestMapping("agentWeekInfoSta.html")
    @ResponseBody
    public Object goldOrigin(String gridPager) throws Exception {
        businessDataSourceUtil.setReadAllDataSourceType();//切换数据源
        MemberBusiness memberBusiness = (MemberBusiness)SecurityUtils.getSubject().getPrincipal();
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
        String gameCode = appGameService.getGameCode(businessDataSourceUtil.getGameType());
        parameters.put("dbTable", gameCode + ".memberagents");
        parameters.put("belongid", nowUserId);
        if (parameters.get("date") == null || StringUtils.isBlank(parameters.get("date") + "")) {
            parameters.put("date", BusinessDateUtil.getLastWeekSunday());
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = taxesDirectlyWeekService.queryListPageByAgentBelongIdAndSunDayDate(parameters);
        return getReturnPage(pager, page, list);
    }

}
