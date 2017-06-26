package com.qs.webside.activity.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.webside.activity.service.IActiCenterService;
import com.qs.webside.activity.service.IActiSendGoldService;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/5/31 14:18.
 * Description:活动中心控制器
 */
@Controller
@RequestMapping(value = "/api/activity/")
public class ActiCenterController extends BaseController {


    @Value("${game.gametype}")
    private int gameType;

    @Value("game.app.send.gold.url")
    private String sendGoldUrl;

    @Resource
    private IActiCenterService actiCenterService;

    @Resource
    private IActiSendGoldService actiSendGoldService;

    /**
     * @Author:zun.wei , @Date:2017/6/1 14:44
     * @Description:获取启用状态下的活动中心列表
     * @param model
     * @param request
     * @param baseRequest
     * @return
     */
    @RequestMapping("getActivityCenterData.do")
    @ResponseBody
    public Object getActivityCenterData(Model model, HttpServletRequest request, BaseRequest baseRequest) {
        List<Map<String,Object>> actiCenter = actiCenterService.queryListActivityByStatus();
        return this.getReturnData(actiCenter, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:19
     * @Description:查询用户是否评论过
     * @param model
     * @param request
     * @param baseRequest
     * @return
     */
    @RequestMapping("checkUserIsComment.do")
    @ResponseBody
    public Object checkUserIsComment(Model model, HttpServletRequest request, BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        Object map = actiSendGoldService.checkUserIsComment(token.getMid());
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:20
     * @Description:根据评论发送金币
     * @param model
     * @param request
     * @param baseRequest
     * @return
     */
    @RequestMapping("sendGoldByComment.do")
    @ResponseBody
    public Object sendGoldByComment(Model model, HttpServletRequest request, BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        Object map = actiSendGoldService.sendGoldByComment(token.getMid(),gameType,baseRequest.getSesskey(),sendGoldUrl);
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }

    /**
     * @Author:zun.wei , @Date:2017/6/1 19:20
     * @Description:根据分享链接发放金币
     * @param model
     * @param request
     * @param baseRequest
     * @return
     */
    @RequestMapping("sendGoldByShare.do")
    @ResponseBody
    public Object sendGoldByShare(Model model, HttpServletRequest request, BaseRequest baseRequest) {
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        Object map = actiSendGoldService.sendGoldByShare(token.getMid(),gameType,baseRequest.getSesskey(),sendGoldUrl);
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }


}
