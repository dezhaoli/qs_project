package com.qs.webside.robot.controller;

import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.CommonContants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zun.wei on 2017/8/11 15:56.
 * Description:机器人控制器
 */
@Controller
@RequestMapping(value = "/api/robot/")
public class RobotController extends BaseController{

    Logger log = Logger.getLogger(RobotController.class);


    /**
     * @Author:zun.wei , @Date:2017/8/10 19:40
     * @Description:接收python发送过来的数据请求
     * @param data 请求参数
     * @param type 请求类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendMsgToJava.html", method = RequestMethod.POST)
    public Object responseMsgToPython(String data,int type) {
        System.out.println("data = " + data);
        System.out.println("type = " + type);
        Map<String, Object> map = new HashMap<>();
        if (type == 1) {
            map.put(CommonContants.SUCCESS, 1);
            map.put(CommonContants.ERROR, -111);
            map.put(CommonContants.MESSAGE, "成功加入");
        }
        return map;
    }


}
