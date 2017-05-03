package com.qs.webside.query.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.webside.agent.service.IMemberPayMentService;
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
 * //@Author:zun.wei, @Date:2017/4/17 11:14
 *  订单统计
 * Created by zun.wei on 2017/4/17.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/query/")
public class OrderCountController extends BaseController {

    //   /query/orderCountUi.html
    @Resource
    private IMemberPayMentService memberPayMentService;


    @RequestMapping(value = "orderCountUi.html", method = RequestMethod.GET)
    public String orderCountUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        Float nowDayPay = memberPayMentService.queryOrderNowDayPayCount();
        if (nowDayPay == null) nowDayPay = 0f;
        model.addAttribute("nowDayPay", nowDayPay);
        return "WEB-INF/view/web/query/orderCount_query_list";
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/17 19:14
     * 充值查询
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("orderCount.html")
    @ResponseBody
    public Object orderCount(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("name", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = memberPayMentService.queryOrderCountByPage(parameters);
        return getReturnPage(pager, page, list);
    }


}
