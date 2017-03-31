package com.qs.webside.agent.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.Constants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.MajiangGameRecord;
import com.qs.log.game.service.IGoldLogService;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberFides;
import com.qs.webside.member.service.IMemberFidesService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代理商后台，查看用户金币来源控制器。//@Author:zun.wei, @Date:2017/3/31 10:45
 *
 * Created by zun.wei on 2017/3/31.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/agent/user/")
public class UserGoldOriginController extends BaseController {

    //http://127.0.0.1:8080/ag/agent/user/goldOriginUi.html

    @Resource
    private IGoldLogService goldLogService;

    @Resource
    private IMemberFidesService memberFidesService;

    /**
     *  代理商后台查看用户金币来源
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "goldOriginUi.html",method = RequestMethod.GET)
    public String goldOriginUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = new PageUtil(request);
            model.addAttribute("page", page);
            return "/WEB-INF/view/web/agent/user/member_userGoldOrigin_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("goldOrigin.html")
    @ResponseBody
    public Object goldOrigin(String gridPager) throws Exception {
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
        if (parameters.get("mid") != null && !"".equals(parameters.get("mid"))) {
            MemberAgents memberAgents = (MemberAgents) SecurityUtils.getSubject().getPrincipal();
            if (memberAgents != null) {
                MemberFides memberFides = memberFidesService.selectByPrimaryKey
                        (Integer.valueOf(parameters.get("mid") + ""));
                if (memberFides != null) {
                    int invite = memberFides.getInvite();
                    //invite = 50180; //@Author:zun.wei, @Date:2017/3/31 11:52 测试时，打开看效果。
                    int searchMid = Integer.valueOf(parameters.get("mid") + "");
                    if (invite != searchMid) {//如果不是直属下级用户不查询
                        //该用户不是您的直属会员
                        Map<String, Object> stringObjectMap = new HashMap<String, Object>();
                        List<Map<String, Object>> mapList = new ArrayList<>();
                        stringObjectMap.put(Constants.AgentMsg.AGENT_ERROR, "该用户不是您的直属会员");
                        mapList.add(stringObjectMap);
                        return getReturnPage(pager, page, mapList);
                    }
                    List<Map<String, Object>> list = goldLogService.getUserGoldOriginPageByMid(parameters);
                    return getReturnPage(pager, page, list);
                }
            }
        }
        return getReturnPage(pager, page, new ArrayList<Map<String,Object>>());//如果没有参数，返回一个空的list。
    }


}
