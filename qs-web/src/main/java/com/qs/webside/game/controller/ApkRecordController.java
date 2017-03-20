package com.qs.webside.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.game.model.ApkRecord;
import com.qs.webside.sys.service.game.service.IApkRecordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/2/28.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/game/record/")
public class ApkRecordController extends BaseController{

    @Resource
    private IApkRecordService apkRecordService;

    @Value("${uploadServer.apk}")
    private String apkUploadUrl;

    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model,String id, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", id);
            return "/WEB-INF/view/web/mobile/apk_record_list";
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
            parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<ApkRecord> list = apkRecordService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    @RequestMapping(value = "upload.html", method = RequestMethod.GET)
    public String uploadApk(Model model,String id, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", id);
            model.addAttribute("apkUploadUrl", apkUploadUrl);
            return "/WEB-INF/view/web/mobile/apk_upload";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "saveApkInfo",method = RequestMethod.POST)
    public Object saveApkInfo(Integer apkId,Integer apkSize,String apkOrName
            ,String apkVersion,String apkPath) {
        ApkRecord apkRecord = new ApkRecord();
        Map<String, Object> map=new HashMap<String, Object>();
        
        apkRecord.setName(apkOrName);
        apkRecord.setApkid(apkId);
        apkRecord.setDate(new Date(System.currentTimeMillis()));
        apkRecord.setSize(apkSize);
        apkRecord.setVersion(apkVersion);
        apkRecord.setFilepath(apkPath);
        int result = apkRecordService.addAppVersion(apkRecord);
        if (result > 0) {
        	map.put(CommonContants.SUCCESS, Boolean.TRUE);
        } else {
        	map.put(CommonContants.SUCCESS, Boolean.FALSE);
        }
        return map;
    }

}
