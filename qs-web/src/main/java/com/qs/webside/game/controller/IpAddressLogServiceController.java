package com.qs.webside.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.game.model.IpaddressLog;
import com.qs.webside.game.model.IpaddressUseLog;
import com.qs.webside.game.service.IIpAddressLogService;
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
 * 查看历史修改记录
 * Created by zun.wei on 2017/4/13.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/ipAddressLog/")
public class IpAddressLogServiceController extends BaseController {

    @Resource
    private IIpAddressLogService ipAddressLogService;

    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, String id, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", id);
            return "/WEB-INF/view/web/ip/ip_modifyLog_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("list.html")
    @ResponseBody
    public Object list(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        //System.out.println("parameters = " + parameters.get("apkId"));
        if (parameters.size() < 0) {
            //parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<IpaddressLog> list = ipAddressLogService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

}
