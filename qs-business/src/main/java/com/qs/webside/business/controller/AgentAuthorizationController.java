package com.qs.webside.business.controller;

import com.qs.pub.game.model.AppGame;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.service.IAppGameService;
import com.qs.pub.game.service.IBusinessService;
import com.qs.webside.shiro.ShiroAuthenticationManager;
import com.qs.webside.util.BusinessDataSourceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 牵手互动商务后台，代理商授权，商务控制器
 * Created by zun.wei on 2017/3/13.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/business/")
public class AgentAuthorizationController {

    @Resource
    private IBusinessService businessService;
    @Resource
    private IAppGameService appGameService;
    @Resource
    private BusinessDataSourceUtil businessDataSourceUtil;

    /**
     * 代理商授权Ui
     * @return
     */
    @RequestMapping(value = "authorizationUi.html", method = RequestMethod.GET)
    public String authorizationUi(Model model) {
        businessDataSourceUtil.readType();//@Author:zun.wei, @Date:2017/4/21 20:27 切换数据源
        //List<AppGame> appGameList = appGameService.queryListAll();
        //model.addAttribute("appGameList", appGameList);
        AppGame appGame = appGameService.selectByPrimaryKey(businessDataSourceUtil.getGameType());
        model.addAttribute("appGame", appGame);
        return "WEB-INF/view/web/business/agent_authorization_form";
    }

    /**
     * 代理商授权
     * @param userId mid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "authorization.html", method = RequestMethod.POST)
    public Object authorization(Integer userId) {
        businessDataSourceUtil.writeType();//@Author:zun.wei, @Date:2017/4/21 20:27 切换数据源
        Integer gameId = businessDataSourceUtil.getGameType();
        MemberBusiness memberBusiness = ShiroAuthenticationManager.getBusiness();
        if (memberBusiness != null) {
            return businessService.saveAuthorization(userId, gameId,memberBusiness,businessDataSourceUtil.getGameType());
        }
        return new HashMap<String, Object>();
    }

    @ResponseBody
    @RequestMapping(value = "searchUserInfo.html")
    public Object searchUserInfo(Integer userId) {
        businessDataSourceUtil.readType();//@Author:zun.wei, @Date:2017/4/21 20:27 切换数据源
        MemberBusiness memberBusiness = ShiroAuthenticationManager.getBusiness();
        Integer gameId = businessDataSourceUtil.getGameType();
        if (memberBusiness != null) {
            return businessService.queryUserInfo(userId, gameId,memberBusiness);
        }
        //return businessService.queryUserInfo(userId, gameId,session);
        return new HashMap<String, Object>();
    }


}
