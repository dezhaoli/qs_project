package com.qs.webside.query.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.webside.agent.service.IMemberPayMentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * //@Author:zun.wei, @Date:2017/4/17 11:13
 *  充值与金币查询
 * Created by zun.wei on 2017/4/17.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/query/")
public class ChangeGoldController extends BaseController {

    //   /query/changeAndGoldUi.html
    @Resource
    private IMemberPayMentService memberPayMentService;

    /**
     * //@Author:zun.wei, @Date:2017/4/17 19:14
     *  充值查询入口
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "changeUi.html", method = RequestMethod.GET)
    public String changeUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        model.addAttribute("queryType", "change");
        return "WEB-INF/view/web/query/changeQuery_list";
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/17 19:14
     * 充值查询
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("change/changeAndGold.html")
    @ResponseBody
    public Object change(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("name", null);
        }
        if (parameters.get("searchDate") == null || StringUtils.isBlank(parameters.get("searchDate") + ""))
            parameters.put("searchDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = memberPayMentService.queryChangeByDate(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/17 19:14
     *  金币查询入口
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "goldUi.html", method = RequestMethod.GET)
    public String goldUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        model.addAttribute("queryType", "gold");
        return "WEB-INF/view/web/query/changeQuery_list";
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/17 19:14
     * 金币查询
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("gold/changeAndGold.html")
    @ResponseBody
    public Object gold(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("name", null);
        }
        if (parameters.get("searchDate") == null || StringUtils.isBlank(parameters.get("searchDate") + ""))
            parameters.put("searchDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = memberPayMentService.queryChangeByDate(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/17 20:01
     * 充值查询用户信息
     * @param mid
     * @return
     */
    @RequestMapping(value = "change/showUserInfoUi.html",method = RequestMethod.GET)
    public String changeShowUserInfo(Integer mid) {
        System.out.println("mid = " + mid);
        return null;
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/17 20:01
     * 金币查询用户信息
     * @param mid
     * @return
     */
    @RequestMapping(value = "gold/showUserInfoUi.html",method = RequestMethod.GET)
    public String goldShowUserInfo(Integer mid) {
        System.out.println("mid = " + mid);
        return null;
    }

}
