package com.qs.webside.activity.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.webside.activity.model.ActiAwardAddress;
import com.qs.webside.activity.model.ActiIntegral;
import com.qs.webside.activity.service.IActiAwardAddressService;
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
 * Created by zun.wei on 2017/6/9 15:50.
 * Description:活动中心奖品发放地址
 */
@Controller
@RequestMapping(value = "/actiAwardAddress/")
public class ActiAwaAddressController extends BaseController {

    @Resource
    private IActiAwardAddressService actiAwardAddressService;

    /**
     * @Author:zun.wei , @Date:2017/6/9 15:53
     * @Description:活动中心兑换地址列表入口
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        return "/WEB-INF/view/web/activity/acti_address_list";
    }


    /**
     * @Author:zun.wei , @Date:2017/6/9 16:00
     * @Description:拉取活动中心兑换地址列表数据
     * @param gridPager
     * @return
     */
    @ResponseBody
    @RequestMapping("list.html")
    public Object getActiIntegralRankingList(String gridPager) {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<ActiAwardAddress> list = actiAwardAddressService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * @param model
     * @param mid
     * @return
     * @Author:zun.wei , @Date:2017/6/9 16:01
     * @Description:根据mid查询显示用户兑换奖品地址
     */
    @RequestMapping(value = "showAddressByMidUi.html", method = RequestMethod.GET)
    public String showAddressByMidUi(Model model, Integer mid) {
        ActiAwardAddress actiAwardAddress = actiAwardAddressService.selectByMid(mid);
        model.addAttribute("actiAwardAddress", actiAwardAddress);
        return "/WEB-INF/view/web/activity/acti_address_show";
    }

}
