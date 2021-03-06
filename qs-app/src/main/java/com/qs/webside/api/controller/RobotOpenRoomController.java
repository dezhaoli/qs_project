package com.qs.webside.api.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.HttpClientUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zun.wei on 2017/8/8 16:47.
 * Description:机器人待开房
 */
@Controller
@RequestMapping(value = "/api/robot/")
public class RobotOpenRoomController extends BaseController {

    Logger log = Logger.getLogger(RobotOpenRoomController.class);

    /**
     * @Author:zun.wei , @Date:2017/8/8 16:52
     * @Description:机器人待开房入口
     * @return
     */
    @RequestMapping(value = "openRoomViewUi.html", method = RequestMethod.GET)
    public String openRoomViewUi() {
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("goldNum", "goldNumvalue"));
        nameValuePairList.add(new BasicNameValuePair("sendType", "sendTypevalue"));
        nameValuePairList.add(new BasicNameValuePair("signCode", "signCodevalue"));
        String json = HttpClientUtil.httpClientByPost("http://127.0.0.1:5555/sendOpenRoomResult.html",nameValuePairList);
        System.out.println("json = " + json);
        return "/web/share/openRoom";
    }


}
