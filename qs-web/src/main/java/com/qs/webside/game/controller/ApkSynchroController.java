package com.qs.webside.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.common.Common;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.game.model.ApkSynchroWithBLOBs;
import com.qs.webside.game.service.IApkSynchroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/24.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/game/apkSynchro/")
public class ApkSynchroController extends BaseController{

    @Resource
    private IApkSynchroService apkSynchroService;

    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            return "/WEB-INF/view/web/mobile/apk_synchro_list";
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
            //parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<ApkSynchroWithBLOBs> list = apkSynchroService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }


    @RequestMapping("addUI.html")
    public String addUI(Model model, HttpServletRequest request, String id) {
        try {
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            return Common.BACKGROUND_PATH + "/web/mobile/apk_synchro_form";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("editUI.html")
    public String editUI(Model model, HttpServletRequest request, Integer id) {
        try {
            ApkSynchroWithBLOBs record = apkSynchroService.selectById(id);
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            model.addAttribute("record", record);
            return Common.BACKGROUND_PATH + "/web/mobile/apk_synchro_form";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("add.html")
    @ResponseBody
    public Object add(ApkSynchroWithBLOBs record) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = apkSynchroService.addSelective(record);
            super.executeRequestResult(result,map);
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


    @RequestMapping("edit.html")
    @ResponseBody
    public Object update(ApkSynchroWithBLOBs record) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = apkSynchroService.updateByIdSelective(record);
            super.executeRequestResult(result,map);
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
            int result = apkSynchroService.deleteById(id);
            super.executeRequestResult(result,map);
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }

}
