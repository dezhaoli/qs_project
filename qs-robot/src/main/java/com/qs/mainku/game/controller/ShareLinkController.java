package com.qs.mainku.game.controller;

import com.alibaba.fastjson.JSON;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.AppConstants;
import com.qs.mainku.game.model.ShareLinkRequest;
import com.qs.mainku.game.service.IBaseParamService;
import com.qs.mainku.game.service.IShareLinkService;
import com.qs.webside.robot.model.RobotRoomCfgDf;
import com.qs.webside.robot.model.RobotRoomConfig;
import com.qs.webside.robot.service.IRobotRoomCfgDfService;
import com.qs.webside.robot.service.IRobotRoomConfigService;
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
import sun.misc.BASE64Encoder;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    @Value("${game.goldhost}")
    private String cIp;

    @Value("${game.goldport}")
    private int cPort;

    @Value("${game.gametype}")
    private int gameType;

    @Resource
    private IShareLinkService shareLinkService;

    /*@Resource
    private GameService gameService;*/

    @Resource
    private IBaseParamService baseParamService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    IRobotRoomConfigService robotRoomConfigService;

    @Resource
    IRobotRoomCfgDfService robotRoomCfgDfService;

    /**
     * @param model
     * @return
     * @Author:zun.wei , @Date:2017/7/21 10:18
     * @Description:点击链接进入加入房间页面视图
     */
    @RequestMapping(value = "joinViewUi.html", method = RequestMethod.GET)
    public String joinRoomViewUi(Model model, ShareLinkRequest shareLinkRequest,
                                 int t,int d,int a) throws InterruptedException, MemcachedException, TimeoutException, IOException {
        String sesskey = shareLinkRequest.getSesskey();
        String roomid = shareLinkRequest.getRoomid();
        if (StringUtils.isBlank(roomid)) roomid = "0";
        String wanfaEncode = shareLinkRequest.getWanfa();
        if (StringUtils.isBlank(wanfaEncode)) wanfaEncode = "";
        wanfaEncode = wanfaEncode.replaceAll(" ", "+");
        String roomtitle = shareLinkRequest.getRoomtitle();
        if (StringUtils.isBlank(roomtitle)) roomtitle = "";
        roomtitle = roomtitle.replaceAll(" ", "+");
        int jushu = shareLinkRequest.getJushu();
        if (a != 0 && d != 0 && t >= 0) {
            CompatibleCode compatibleCode = new CompatibleCode(t, d, a, wanfaEncode, roomtitle, jushu).invoke();
            roomtitle = compatibleCode.getRoomtitle();
            jushu = compatibleCode.getJushu();
            wanfaEncode = compatibleCode.getWanfaEncode();
        }
        String state = sesskey + "_qs_" + roomid;
        Map<String, Object> roomInfoMap = new HashMap<>();
        roomInfoMap.put("roomtitle", roomtitle);
        roomInfoMap.put("jushu", jushu);
        roomInfoMap.put("wanfaEncode", wanfaEncode);
        shareLinkService.setRoomInfo(roomInfoMap, model, Integer.parseInt(roomid), gameType);
        String redirectUrl = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_REDIRECT_URL);
        redirectUrl += "/api/shareLink/joinRoom.html";
        String appId = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_ID);
        String url = SnsAPI.connectOauth2Authorize(appId, redirectUrl, true, state);
        model.addAttribute("url", url);
        model.addAttribute("roomid", roomid);
        model.addAttribute("state", state);
        model.addAttribute("gameType", gameType);
        return "/web/share/joinRoom";
    }

    /**
     * @param model spring mox
     * @param code  微信回调code
     * @param state 回调参数
     * @return 视图url
     * @Author:zun.wei , @Date:2017/7/21 11:26
     * @Description:微信授权回调加入房间
     */
    @RequestMapping(value = "joinRoom.html")
    public String joinRoomCallBack(Model model, String code, @RequestParam(name = "state", defaultValue = "") String state)
            throws IOException, InterruptedException, MemcachedException, TimeoutException {
        String[] params = state.split("_qs_");
        String sesskey = params[0];
        AccessToken token = ContextUtil.getAccessTokenInfo(sesskey);
        int gp = token.getGb();
        int roomid = Integer.parseInt(params[1]);
        String appId = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_ID);
        String unionid = null;
        String tokenCode = AppConstants.RedisKeyPrefix.SHARE_LINK_REFRESH_TOKEN + code;
        String refreshToken = (String) redisTemplate.opsForValue().get(tokenCode);
        if (StringUtils.isNotBlank(refreshToken)) {
            SnsToken snsToken = SnsAPI.oauth2RefreshToken(appId, refreshToken);
            User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
            unionid = user.getUnionid();
        } else {
            String secret = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_SECRET);
            SnsToken snsToken = SnsAPI.oauth2AccessToken(appId, secret, code);
            User user = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN");
            redisTemplate.opsForValue().set(tokenCode, snsToken.getRefresh_token(), 29, TimeUnit.DAYS);
            unionid = user.getUnionid();
        }
        shareLinkService.joinRoom(roomid, unionid, model, gp, sesskey, cIp, cPort, gameType);
        model.addAttribute("code", code);
        model.addAttribute("gameType", gameType);
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
        String appId = baseParamService.getBaseParamValueByCode(AppConstants.BaseParam.SHARE_LINK_JOIN_ROOM_APP_ID);
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
            return shareLinkService.getUserInfoByCookieCode(unionid, gameType);
        }
    }

    private class CompatibleCode {
        private int t;
        private int d;
        private int a;
        private String wanfaEncode;
        private String roomtitle;
        private int jushu;

        public CompatibleCode(int t, int d, int a, String wanfaEncode, String roomtitle, int jushu) {
            this.t = t;
            this.d = d;
            this.a = a;
            this.wanfaEncode = wanfaEncode;
            this.roomtitle = roomtitle;
            this.jushu = jushu;
        }

        public String getWanfaEncode() {
            return wanfaEncode;
        }

        public String getRoomtitle() {
            return roomtitle;
        }

        public int getJushu() {
            return jushu;
        }

        public CompatibleCode invoke() throws UnsupportedEncodingException {
            if (a != 0){
                //setRequestInfo(t,d,a,wanfaEncode,roomtitle,jushu);
                BASE64Encoder encoder = new BASE64Encoder();
                if (d == 1) {
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("mid", a);
                    parameters.put("roomType", t);
                    RobotRoomConfig robotRoomConfig = robotRoomConfigService.getRobotRoomCfgByMidAndType(parameters);
                    Map<String, Integer> cfg = JSON.parseObject(robotRoomConfig.getData(), Map.class);
                    String wf = robotRoomConfig.getWanfa();
                    wf = wf.replaceAll(",", "_");
                    wanfaEncode = encoder.encode(wf.getBytes("utf-8"));
                    jushu = cfg.get("jushu");
                    roomtitle = encoder.encode(robotRoomConfig.getRoomName().getBytes("utf-8"));
                } else if (d == 2) {
                    RobotRoomCfgDf robotRoomCfgDf = robotRoomCfgDfService.queryRobotConfigByType(t);
                    Map<String, Integer> cfg = JSON.parseObject(robotRoomCfgDf.getData(), Map.class);
                    String wf = robotRoomCfgDf.getWanfa();
                    wf = wf.replaceAll(",", "_");
                    wanfaEncode = encoder.encode(wf.getBytes("utf-8"));
                    jushu = cfg.get("jushu");
                    roomtitle = encoder.encode(robotRoomCfgDf.getRoomName().getBytes("utf-8"));
                } else {

                }
            }
            return this;
        }
    }
}
