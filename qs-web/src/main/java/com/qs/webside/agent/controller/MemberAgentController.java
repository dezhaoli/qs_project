package com.qs.webside.agent.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.sys.service.agent.service.IMemberAgentService;
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
 * 代理商授权
 *
 * Created by zun.wei on 2017/3/8.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/agent/authorization/")
public class MemberAgentController extends BaseController{

    @Resource
    private IMemberAgentService memberAgentService;


    /**
     * 代理商授权列表入口
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
            return "WEB-INF/view/web/agent/agent_authorization_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * 获取代理商授权列表
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
            parameters.put("mid", null);
        } else if ("".equals(parameters.get("mid"))){
            parameters.put("mid", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<MemberAgents> list = memberAgentService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * 充值测试ui
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "testPayUi.html", method = RequestMethod.GET)
    public String testPay(Model model, Integer id) {
        MemberAgents memberAgents = memberAgentService.selectByPrimaryKey(id);
        model.addAttribute("id", memberAgents.getId());
        model.addAttribute("realName", memberAgents.getRealname());
        model.addAttribute("mid", memberAgents.getMid());
        return "WEB-INF/view/web/agent/agent_auth_testPay_form";
    }

    /**
     * 充值测试
     * @param model
     * @param id
     * @param mid
     * @param money
     * @return
     */
    //TODO 这里是充值测试,待完成
    @RequestMapping(value = "testPay.html", method = RequestMethod.POST)
    public Object testPay(Model model, Integer id,Integer mid,Integer money) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("id = " + id);
        System.out.println("mid = " + mid);
        System.out.println("money = " + money);
        /*int result = businessService.udpatePhoneById(agentId, phone);
        if (result == -100) {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.DATA, null);
            map.put(CommonContants.MESSAGE, "手机号码已经绑定了,不能修改。");
        }
        executeUpdateRequestResult(result, map);*/
        return map;
    }

    /**
     * 修改返现比例ui
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "editUI.html", method = RequestMethod.GET)
    public String editUI(Model model, Integer id) {
        MemberAgents memberAgents = memberAgentService.selectByPrimaryKey(id);
        model.addAttribute("mid", memberAgents.getMid());
        model.addAttribute("id", memberAgents.getId());
        model.addAttribute("realName", memberAgents.getRealname());
        return "WEB-INF/view/web/agent/agent_auth_edit_form";
    }

    /**
     * 修改返现比例
     * @param model
     * @param id
     * @param mid
     * @param scale
     * @param remark
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "edit.html", method = RequestMethod.POST)
    public Object edit(Model model, Integer id,Integer mid,Byte scale,String remark) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = memberAgentService.editScale(id,mid,scale,remark);
        executeUpdateRequestResult(result, map);
        return map;
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "resetPwd.html", method = RequestMethod.POST)
    public Object resetPwd(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = memberAgentService.resetPwd(id);
        executeUpdateRequestResult(result, map);
        return map;
    }

}
