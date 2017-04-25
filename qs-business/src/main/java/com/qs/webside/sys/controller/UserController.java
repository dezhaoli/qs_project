package com.qs.webside.sys.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.exception.AjaxException;
import com.qs.pub.game.service.IBusinessService;
import com.qs.webside.util.BusinessDataSourceUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * //@Author:zun.wei, @Date:2017/4/19 10:09
 * 商务用户管理
 * Created by zun.wei on 2017/4/19.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController extends BaseController {

    @Resource
    private IBusinessService businessService;
    @Resource
    private BusinessDataSourceUtil businessDataSourceUtil;

    /**
     * //@Author:zun.wei, @Date:2017/4/19 9:46
     * 商务后台修改密码入口
     * @param model
     * @return
     */
    @RequestMapping(value = "editPasswordUI.html",method = RequestMethod.GET)
    public String editPasswordUI(Model model, String name, Integer id,String phone,String email) {
        String account = null;
        try{
            if (StringUtils.isBlank(phone)) {
                account = email;
            }
            if (StringUtils.isBlank(email)) {
                account = phone;
            }
            if (StringUtils.isBlank(email) && StringUtils.isBlank(phone)) {
                account = "";
            }
            model.addAttribute("id", id);
            model.addAttribute("account", account);
            model.addAttribute("name", name);
            return "/WEB-INF/view/user/password";
        }catch(Exception e){
            throw new AjaxException(e);
        }
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/19 11:11
     * 修改密码
     * @param id
     * @param confirmPassword
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editPassword.html",method = RequestMethod.POST)
    public Object editPassword(Integer id,String confirmPassword,String password) {
        businessDataSourceUtil.setWriteAllDataSourceType();
        Map<String, Object> result = new HashMap<>();
        if (password == null) {
            result.put(CommonContants.SUCCESS, false);
            result.put(CommonContants.MESSAGE, "要修改的密码不能为空！");
            return result;
        }
        if (!password.equals(confirmPassword)) {
            result.put(CommonContants.SUCCESS, false);
            result.put(CommonContants.MESSAGE, "确认密码有误，请重新输入！");
            return result;
        }
        int count = businessService.updatePassWordById(id, password, confirmPassword);
        if (count > 0){
            result.put(CommonContants.SUCCESS, true);
            result.put(CommonContants.MESSAGE, "修改密码成功！");
        }else {
            result.put(CommonContants.SUCCESS, false);
            result.put(CommonContants.MESSAGE, "系统异常,修改失败！");
        }
        return result;
    }

}
