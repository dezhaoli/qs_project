package com.qs.log.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.constant.Constants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.Mails;
import com.qs.log.game.service.IMailService;
import com.qs.webside.game.model.ApkRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created by zun.wei on 2017/3/2.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/game/mail/")
public class MailController extends BaseController{

    @Resource
    private IMailService mailService;

    /**
     * 邮件列表入口
     * @param model
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, String id, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", id);
            return "WEB-INF/view/web/mail/mail_list_from";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 查询邮件列表
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("list.html")
    @ResponseBody
    public Object list(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Mails> list = mailService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * 保存邮件
     * @param record
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "save.html",method = RequestMethod.POST)
    public Object saveMail(Mails record) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        int executeResult = mailService.addSelective(record);
        if (executeResult > 0) {
            map.put(CommonContants.SUCCESS, Boolean.TRUE);
        } else {
            map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
        }
        return map;
    }

    /**
     * 查看邮件详情入口
     * @param model
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "showDetail.html", method = RequestMethod.GET)
    public String showDetail(Model model,String id, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", id);
            return "/WEB-INF/view/web/mail/mail_list_detail";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 查看邮件详情
     * @param gridPager 查询参数
     * @return
     * @throws Exception
     */
    @RequestMapping("listDetail.html")
    @ResponseBody
    public Object listDetail(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Mails> list = mailService.queryMailDetail(parameters);
        return getReturnPage(pager, page, list);
    }

}
