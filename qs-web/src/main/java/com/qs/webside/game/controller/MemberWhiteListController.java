package com.qs.webside.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.AjaxException;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.member.model.MemberWhiteList;
import com.qs.webside.member.service.IMemberWhiteListService;
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
 * 测试服白名单
 * Created by zun.wei on 2017/2/24.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/game/whiteList/")
public class MemberWhiteListController extends BaseController{

    @Resource
    private IMemberWhiteListService memberWhiteListService;

    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            return "WEB-INF/view/web/member/member_testWhiteList_list";
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
            parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<MemberWhiteList> list = memberWhiteListService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }


    @RequestMapping("addUI.html")
    public String addUI(Model model, HttpServletRequest request, String id) {
        try {
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            return "WEB-INF/view/web/member/member_testWhiteList_form";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("editUI.html")
    public String editUI(Model model, HttpServletRequest request, Integer id) {
        try {
            MemberWhiteList record = memberWhiteListService.selectById(id);
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            model.addAttribute("record", record);
            return "WEB-INF/view/web/member/member_testWhiteList_form";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("add.html")
    @ResponseBody
    public Object add(MemberWhiteList record) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {//record = MemberWhiteList{id=null, mid=null, name='null', icon='null', actvityType=null, testType=null}
            int result = 0;
            if (null != record && null != record.getMid() && !"0".equals(record.getMid() + "")) {
                result = memberWhiteListService.addSelective(record);
            }
            if (result > 0) {
            	memberWhiteListService.setMemberWhiteDeviceCache();
                map.put(CommonContants.SUCCESS, Boolean.TRUE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.ADD_SUCCESS);
            } else if (result < -1) {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, "添加失败（原因：已添加）!");
            } else {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.ADD_FAILURE);
            }
            //super.executeRequestResult(result,map);
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


    @RequestMapping("edit.html")
    @ResponseBody
    public Object update(MemberWhiteList record) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = memberWhiteListService.updateByIdSelective(record);
            memberWhiteListService.setMemberWhiteDeviceCache();
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
            int result = memberWhiteListService.deleteById(id);
            memberWhiteListService.setMemberWhiteDeviceCache();
            super.executeRequestResult(result,map);
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/takeEffectById.html",method = RequestMethod.POST)
    public Object takeEffectById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = memberWhiteListService.updateTakeEffectById(id);
            if (result > 0) {
                memberWhiteListService.setMemberWhiteDeviceCache();
                map.put(CommonContants.SUCCESS, Boolean.TRUE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS);
            } else {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE);
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


    /**
     * @Author:zun.wei , @Date:2017/7/7 10:37
     * @Description:生效或失效全部
     * @param type 0表示失效，1表示生效
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateEffectOrUnEffect.html",method = RequestMethod.POST)
    public Object updateEffectOrUnEffect(int type) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = memberWhiteListService.updateTakeEffectAll(type);
        if (result > 0) {
            memberWhiteListService.setMemberWhiteDeviceCache();
            map.put(CommonContants.SUCCESS, Boolean.TRUE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS);
        } else {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE);
        }
        return map;
    }

}
