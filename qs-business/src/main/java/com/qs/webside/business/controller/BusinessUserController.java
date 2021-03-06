package com.qs.webside.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.pub.game.service.IAppGameService;
import com.qs.pub.game.service.IBusinessService;
import com.qs.webside.util.BusinessDataSourceUtil;
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
    @Resource
    private BusinessDataSourceUtil businessDataSourceUtil;
    @Resource
    private IAppGameService appGameService;

    //@Resource
    //private IMemberFidesService memberFidesService;

    /**
     *  商务后台查看用户信息入口
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "showUserInfoUi.html",method = RequestMethod.GET)
    public String businessShowUserInfoUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            return "/WEB-INF/view/web/user/member_showUserInfo_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("showUserInfo.html")
    @ResponseBody
    public Object list(String gridPager) throws Exception {
        businessDataSourceUtil.setReadAllDataSourceType();//切换数据源
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("mid", null);
        }
        String gameCode = appGameService.getGameCode(businessDataSourceUtil.getGameType());
        parameters.put("dbName", gameCode + ".memberagents");
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> map = businessService.getUserAgentAndBuziInfoByUserMid(parameters);
        //List<MemberFides> list = memberFidesService.queryListByMid(parameters);
        return getReturnPage(pager, page, map);
    }

}
