package com.qs.webside.business.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.service.IBusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * 商务后台的关于用户的控制器.
 * Created by zun.wei on 2017/3/20.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/business/")
public class BusinessUserController extends BaseController {

    @Resource
    private IBusinessService businessService;

    @RequestMapping(value = "showUserInfoUi.html",method = RequestMethod.GET)
    public String showAgentDetailPageUI(Model model, HttpServletRequest request, Integer id) {

        try {
            Map<String, Object> record = businessService.getAgentDetailInfoByMid(id);
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            model.addAttribute("record", record);
            return "/WEB-INF/view/web/business/agent_detail_show";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

}
