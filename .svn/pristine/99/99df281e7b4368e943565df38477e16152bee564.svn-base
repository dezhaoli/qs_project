package com.qs.webside.activity.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.game.service.GameService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/26 15:17.
 * Description:活动控制器
 */
@Controller
@RequestMapping(value = "/api/acti/")
public class ActivityController extends BaseController {

    @Resource
    private GameService gameService;

    /**
     * @param request
     * @param baseRequest
     * @return
     * @Author:zun.wei , @Date:2017/6/26 17:15
     * @Description:活动中心发送金币接口
     */
    @ResponseBody
    @RequestMapping(value = "sendGold.html", method = RequestMethod.POST)
    public Object actiSendGold(HttpServletRequest request, BaseRequest baseRequest) {
        int goldNum = Integer.parseInt(request.getParameter("goldNum"));//金币数量
        int sendType = Integer.parseInt(request.getParameter("sendType"));//发送类型
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        return gameService.updateGold(token.getMid(), goldNum, sendType);
    }

}
