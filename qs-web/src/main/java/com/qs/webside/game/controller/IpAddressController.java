package com.qs.webside.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.game.model.Ipaddress;
import com.qs.webside.sys.service.game.service.IIpAddressLogService;
import com.qs.webside.sys.service.game.service.IIpAddressService;
import com.qs.webside.sys.service.game.service.IIpAddressUserLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/23.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/game/ip/")
public class IpAddressController extends BaseController{

    @Resource
    private IIpAddressService ipAddressService;
    @Resource
    private IIpAddressUserLogService ipAddressUserLogService;
    @Resource
    private IIpAddressLogService ipAddressLogService;


    @RequestMapping(value = "ipListUi.html")
    public String ipListUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = new PageUtil();
            if (request.getParameterMap().containsKey("page")) {
                page.setPageSize(Integer.valueOf(request.getParameter("rows")));
                page.setPageNum(Integer.valueOf(request.getParameter("page")));
                page.setOrderByColumn(request.getParameter("sidx"));
                page.setOrderByType(request.getParameter("sord"));
            }
            model.addAttribute("page", page);
            return Common.BACKGROUND_PATH + "/web/ip/ip_list";
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
        if (parameters.size() < 0) {
            parameters.put("name", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());

        List<Ipaddress> list = ipAddressService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }


    @RequestMapping("addUI.html")
    public String addUI(Model model, HttpServletRequest request, String id) {
        try {
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            return Common.BACKGROUND_PATH + "/web/ip/ip_form";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("editUI.html")
    public String editUI(Model model, HttpServletRequest request, Integer id) {
        try {
            Ipaddress record = ipAddressService.findById(id);
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            model.addAttribute("record", record);
            return Common.BACKGROUND_PATH + "/web/ip/ip_form";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("add.html")
    @ResponseBody
    public Object add(Ipaddress record) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = ipAddressService.add(record);
           executeRequestResult(result,map);
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


    @RequestMapping("edit.html")
    @ResponseBody
    public Object update(Ipaddress record) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = ipAddressService.update(record);
            executeRequestResult(result,map);
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


    @RequestMapping("deleteById.html")
    @ResponseBody
    public Object deleteById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = ipAddressService.deleteById(id);
            executeRequestResult(result,map);
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }

}
