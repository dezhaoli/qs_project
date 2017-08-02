package com.qs.webside.api.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.webside.api.model.ShareLinkRequest;
import com.qs.webside.game.service.GameService;
import com.qs.webside.game.service.IShareLinkService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * @Author:zun.wei , @Date:2017/7/21 10:18
     * @Description:点击链接进入加入房间页面视图
     * @param model
     * @return
     */
    @RequestMapping(value = "joinViewUi.html",method = RequestMethod.GET)
    public String joinRoomViewUi(Model model,ShareLinkRequest shareLinkRequest) throws InterruptedException, MemcachedException, TimeoutException, IOException {
        String sesskey = shareLinkRequest.getSesskey();
        String roomid = shareLinkRequest.getRoomid();
        String wanfaEncode = shareLinkRequest.getWanfa();
        wanfaEncode = wanfaEncode.replaceAll(" ", "+");
        String roomtitle = shareLinkRequest.getRoomtitle();
        roomtitle = roomtitle.replaceAll(" ", "+");
        int jushu = shareLinkRequest.getJushu();
        String state = sesskey + "_qs_" + roomid;
        Map<String, Object> roomInfoMap = new HashMap<>();
        roomInfoMap.put("roomtitle", roomtitle);
        roomInfoMap.put("jushu", jushu);
        roomInfoMap.put("wanfaEncode", wanfaEncode);
        shareLinkService.setRoomInfo(roomInfoMap,model, Integer.parseInt(roomid), gameType);
        String redirectUrl = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_REDIRECT_URL);
        redirectUrl += "/app/api/shareLink/joinRoom.html";
        String appId = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_ID);
        String url= SnsAPI.connectOauth2Authorize(appId, redirectUrl,true, state);
        model.addAttribute("url", url);
        model.addAttribute("roomid", roomid);
        model.addAttribute("state", state);
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
    public String joinRoomCallBack(Model model, String code, @RequestParam(name = "state",defaultValue = "") String state)
            throws IOException, InterruptedException, MemcachedException, TimeoutException {
        String[] params = state.split("_qs_");
        String sesskey = params[0];
        AccessToken token = ContextUtil.getAccessTokenInfo(sesskey);
        int gp = token.getGb();
        int roomid = Integer.parseInt(params[1]);
        String appId = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_ID);
        String unionid = null;
        String tokenCode = AppConstants.RedisKeyPrefix.SHARE_LINK_REFRESH_TOKEN + code;
        String refreshToken = (String) redisTemplate.opsForValue().get(tokenCode);
        if (StringUtils.isNotBlank(refreshToken)) {
            SnsToken snsToken = SnsAPI.oauth2RefreshToken(appId, refreshToken);
            User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
            unionid = user.getUnionid();
        } else {
            String secret = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_SECRET);
            SnsToken snsToken = SnsAPI.oauth2AccessToken(appId, secret, code);
            User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
            redisTemplate.opsForValue().set(tokenCode, snsToken.getRefresh_token(), 29, TimeUnit.DAYS);
            unionid = user.getUnionid();
        }
        shareLinkService.joinRoom(roomid, unionid, model, gp, sesskey, cIp, cPort, gameType);
        model.addAttribute("code", code);
        log.debug("the joinRoomCallBack code is ----------::" + code);
        return "/web/share/joinRoom";
    }

    /**
     * @param model
     * @param code
     * @param state 回调参数
     * @return 视图url
     * @Author:zun.wei , @Date:2017/7/21 11:26
     * @Description:微信授权回调加入房间
     */
    @ResponseBody
    @RequestMapping(value = "cookieJoinRoom.html", method = RequestMethod.POST)
    public Object cookieJoinRoomCallBack(Model model, @RequestParam(name = "code", defaultValue = "") String code
            , @RequestParam(name = "state", defaultValue = "") String state)
            throws IOException, InterruptedException, MemcachedException, TimeoutException {
        String appId = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_ID);
        //String secret = gameService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_SECRET);
        String unionid = null;
        if (StringUtils.isNotBlank(code)) {
            String refreshToken = (String) redisTemplate.opsForValue().get(AppConstants.RedisKeyPrefix.SHARE_LINK_REFRESH_TOKEN + code);
            SnsToken snsToken = SnsAPI.oauth2RefreshToken(appId, refreshToken);
            User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
            unionid = user.getUnionid();
        }
        log.debug("the cookieJoinRoomCallBack code is ----------::" + code);
        if (StringUtils.isNotBlank(state)) {
            String[] params = state.split("_qs_");
            String sesskey = params[0];
            AccessToken token = ContextUtil.getAccessTokenInfo(sesskey);
            int gp = token.getGb();
            int roomid = Integer.parseInt(params[1]);
            return shareLinkService.joinRoom(roomid, unionid, model, gp, sesskey, cIp, cPort, gameType);
        } else {
            return shareLinkService.getUserInfoByCookieCode(unionid,gameType);
        }
    }

}
