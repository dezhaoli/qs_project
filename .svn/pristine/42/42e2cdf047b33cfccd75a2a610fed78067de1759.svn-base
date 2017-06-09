package com.qs.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zun.wei on 2017/6/9 20:10.
 * Description:发送消息到C++服务器
 */
public class SendMsgToCServer {

    private static Log log = LogFactory.getLog(SendMsgToCServer.class);

    public static boolean sendMsgToCServer(int mid, int type, int msg,String goldHost,int goldPort) {
        //发通知给c++
        SocketPacketUtil socketUtil = new SocketPacketUtil(goldHost, goldPort);
        boolean socketFlag = false;
        Map<String, Object> jsonMsgMap = new HashMap<String, Object>();
        jsonMsgMap.put("type", type);
        jsonMsgMap.put("msg", msg);
        String jsonMsg = JSON.toJSONString(jsonMsgMap);
        if (null != socketUtil.getSocket()) {
            socketFlag = socketUtil.sendData(10008, mid, jsonMsg);
            log.debug("socketFlag===::" + socketFlag);
        } else {
            log.error("updateGold socket is null===::" + mid);
        }
        socketUtil.close();
        return socketFlag;
    }


}
