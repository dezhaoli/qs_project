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
import com.qs.webside.sys.service.member.service.IMemberWhiteListService;
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
 * 活动测试白名单控制器
 * Created by zun.wei on 2017/2/24.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/game/activityTest/")
public class MemberActivityWhiteListController extends BaseController{

    @Resource
    private IMemberWhiteListService memberWhiteListService;

    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            return "/WEB-INF/view/web/member/member_act_list";
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
        List<MemberWhiteList> list = memberWhiteListService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    @RequestMapping("edit.html")
    @ResponseBody
    public Object update(MemberWhiteList record) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = memberWhiteListService.updateByIdSelective(record);
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
            int result = memberWhiteListService.updateTakeEffectByAct(id);
            if (result > 0) {
                map.put(CommonContants.SUCCESS, Boolean.TRUE);
                map.put(CommonContants.DATA, null);
                map.put(CommonContants.MESSAGE, CommonContants.OPERATE_SUCCESS);
            } else {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put("data", null);
                map.put(CommonContants.MESSAGE, CommonContants.OPERATE_FAILURE);
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }

}
