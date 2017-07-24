package com.qs.webside.api.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.webside.api.model.ShareLinkRequest;
import com.qs.webside.game.service.GameService;
import com.qs.webside.member.service.IShareLinkService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zun.wei on 2017/7/21 9:20.
 * Description:分享链接登录房间控制器
 */
@Controller
@RequestMapping(value = "/api/shareLink/")
public class ShareLinkController extends BaseController {

    Logger log = Logger.getLogger(ShareLinkController.class);

    //@Value("${wepay.appid}")
    //private String appId = "wxdac78f942674b126";

    //@Value("${wepay.appSecret}")
    //private String secret = "b83ae895d39defe54a797f288c4ce2d1";

    @Value("${game.goldhost}")
    private String cIp;

    @Value("${game.goldport}")
    private int cPort;

    @Value("${game.gametype}")
    private int gameType;

    @Resource
    private IShareLinkService shareLinkService;

    @Resource
    private GameService gameService;

    /**
     * @Author:zun.wei , @Date:2017/7/21 10:18
     * @Description:点击链接进入加入房间页面视图
     * @param model
     * @return
     */
    @RequestMapping(value = "joinViewUi.html",method = RequestMethod.GET)
    public String joinRoomViewUi(Model model,ShareLinkRequest shareLinkRequest) {
        //TODO 点击链接进来要查询，分享的房间中的信息。
        String sesskey = shareLinkRequest.getSesskey();
        String roomid = shareLinkRequest.getRoomid();
        String state = sesskey + "_qs_" + roomid;
        //String sesskey = "54118-1500891206146-101-b8dda24685951c2b31f1c162251997db-0-6";
        //String roomid = "100009";
        //String redirectUrl = "http://saywewe.iask.in/app/api/shareLink/joinRoom.html";
        String redirectUrl = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_REDIRECT_URL);
        redirectUrl += "/app/api/shareLink/joinRoom.html";
        String appId = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_ID);
        String url= SnsAPI.connectOauth2Authorize(appId, redirectUrl,true, state);
        model.addAttribute("url", url);
        model.addAttribute("roomid", roomid);
        return "/web/share/joinRoom";
    }


    /**
     * @param model  spring mox
     * @param code   微信回调code
     * @param state 回调参数
     * @return 视图url
     * @Author:zun.wei , @Date:2017/7/21 11:26
     * @Description:微信授权回调加入房间
     */
    @RequestMapping(value = "joinRoom.html")
    public String joinRoomCallBack(Model model, String code, @RequestParam(name = "state",defaultValue = "") String state) throws IOException {
        String[] params = state.split("_qs_");
        String sesskey = params[0];
        AccessToken token = ContextUtil.getAccessTokenInfo(sesskey);
        int gp = token.getGb();
        int roomid = Integer.parseInt(params[1]);
        String appId = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_ID);
        String secret = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_SECRET);
        SnsToken snsToken = SnsAPI.oauth2AccessToken(appId, secret, code);
        User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
        String unionid = user.getUnionid();
        shareLinkService.joinRoom(roomid, unionid, model, gp, sesskey, cIp, cPort, gameType);
        return "/web/share/joinRoom";
    }

   /* *//**
     * @Author:zun.wei , @Date:2017/7/24 11:37
     * @Description:获取分享链接的url
     * @return
     *//*
    @ResponseBody
    @RequestMapping(value = "/getShareLinkUrl.do")
    public Object getShareLinkUrl() {
        String redirectUrl = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_REDIRECT_URL);
        redirectUrl += "/api/shareLink/getShareLinkUrl.do";
        Map<String, Object> map = new HashMap<>();
        map.put("redirectUrl", redirectUrl);
        return this.getReturnData(map, AppConstants.Result.SUCCESS);
    }*/

}
