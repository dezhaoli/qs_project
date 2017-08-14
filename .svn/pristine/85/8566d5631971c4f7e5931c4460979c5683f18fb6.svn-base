package com.qs.webside.robot.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CacheConstan;
import com.qs.common.constant.CommonContants;
import com.qs.webside.api.model.BaseRequest;
import com.qs.webside.robot.service.IRobotService;
import com.qs.webside.util.AccessToken;
import com.qs.webside.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zun.wei on 2017/8/11 15:56.
 * Description:机器人控制器
 */
@Controller
@RequestMapping(value = "/api/robot/")
public class RobotController extends BaseController{

    private Logger log = Logger.getLogger(RobotController.class);

    @Resource
    private IRobotService robotService;

    @Resource
    private RedisTemplate redisTemplate;

    @Value("${game.gametype}")
    private int gameType;

    @Value("${game.goldhost}")
    private String cIp;

    @Value("${game.goldport}")
    private int cPort;

    /**
     * @Author:zun.wei , @Date:2017/8/10 19:40
     * @Description:接收python发送过来的数据请求
     * @param data 请求参数
     * @param type 请求类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sendMsgToJava.html", method = RequestMethod.POST)
    public Object responseMsgToPython(String data,int type) {
        return robotService.handlePythonRequest(type, data,gameType,cIp,cPort);
    }


    /**
     * @Author:zun.wei , @Date:2017/8/14 18:24
     * @Description:存储上一局玩法参数
     * @param wanfa 中文玩法
     * @param dataTab int类型参数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveLastGameCfg.html", method = RequestMethod.POST)
    public Object saveLastGameCfg(BaseRequest baseRequest,String wanfa, String dataTab) throws IOException {
        if (StringUtils.isBlank(wanfa)) wanfa = "";
        wanfa = wanfa.replaceAll(" ", "+");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(wanfa);
        String wanfadeCode = new String(b, "utf-8");
        AccessToken token = ContextUtil.getAccessTokenInfo(baseRequest.getSesskey());
        int mid = token.getMid();
        Map<String, Object> values = new HashMap<>();
        values.put("wanfaIntJson", dataTab);
        values.put("wanfaStr", wanfadeCode);
        redisTemplate.opsForValue().set(CacheConstan.LAST_TIME_GAME_CFG_CACHE_NAME + mid,values);
        return null;
    }

}
