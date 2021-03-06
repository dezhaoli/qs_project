package com.qs.webside.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.service.IGoldLogService;
import com.qs.pub.game.model.AppGame;
import com.qs.pub.game.service.IAppGameService;
import com.qs.webside.util.BusinessDataSourceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  商务后台，查看用户金币来源
 * Created by zun.wei on 2017/3/22.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/business/")
public class UserGoldOriginController extends BaseController{

    @Resource
    private IGoldLogService goldLogService;
    @Resource
    private BusinessDataSourceUtil businessDataSourceUtil;
    @Resource
    private IAppGameService appGameService;

    /**
     *  商务后台查看用户金币来源
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "user/goldOriginUi.html",method = RequestMethod.GET)
    public String goldOriginUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            return "/WEB-INF/view/web/user/member_userGoldOrigin_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("user/goldOrigin.html")
    @ResponseBody
    public Object goldOrigin(String gridPager) throws Exception {
        businessDataSourceUtil.setReadAllDataSourceType();//切换数据源
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            //parameters.put("mid", null);
            return null;//如果没有mid传过来则不执行查询。
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        if (parameters.get("mid") == null || "".equals(parameters.get("mid")))
            return getReturnPage(pager, page, new ArrayList<Map<String,Object>>());//如果没有参数，返回一个空的list。
        String gameCode = appGameService.getGameCode(businessDataSourceUtil.getGameType());
        parameters.put("apksynchro", gameCode + ".apksynchro");
        List<Map<String, Object>> list = goldLogService.getUserGoldOriginPageByMid(parameters);
        return getReturnPage(pager, page, list);
    }

}
