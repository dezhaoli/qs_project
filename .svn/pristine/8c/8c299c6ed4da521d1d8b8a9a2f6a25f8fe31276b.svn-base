package com.qs.webside.robot.service.impl;

import com.alibaba.fastjson.JSON;
import com.qs.common.constant.CommonContants;
import com.qs.common.util.SocketUtils;
import com.qs.webside.robot.model.RobotRoomCfgDf;
import com.qs.webside.robot.model.RobotRoomConfig;
import com.qs.webside.robot.service.IRobotRoomCfgDfService;
import com.qs.webside.robot.service.IRobotRoomConfigService;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zun.wei on 2017/8/15 11:01.
 * Description:根据游戏类型，机器人开不同的房间
 */
public class RobotOpenRoomByGameType {


    /**
     * @Author:zun.wei , @Date:2017/8/15 11:04
     * @Description:根据游戏类型切换开房间的类型
     * @param gameType 游戏类型
     * @param socketUtils
     * @return
     */
    public static Map<String,Object> switchOpenRoomByGameType(int gameType, SocketUtils socketUtils, int amid, int roomType
            , IRobotRoomConfigService robotRoomConfigService, IRobotRoomCfgDfService robotRoomCfgDfService) throws UnsupportedEncodingException {
        switch (gameType) {
            case 6:
                return openGDMajiangRoom(socketUtils, amid,roomType, robotRoomConfigService,robotRoomCfgDfService);
            default:
                return openGDMajiangRoom(socketUtils, amid,roomType, robotRoomConfigService,robotRoomCfgDfService);
        }
    }

    private static Map<String,Object> openGDMajiangRoom(SocketUtils socketUtils, int amid,int roomType
            , IRobotRoomConfigService robotRoomConfigService, IRobotRoomCfgDfService robotRoomCfgDfService) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        Logger log = Logger.getLogger(RobotOpenRoomByGameType.class);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("mid", amid);
        parameters.put("roomType", roomType);
        RobotRoomConfig robotRoomConfig = robotRoomConfigService.getRobotRoomCfgByMidAndType(parameters);
        //BASE64Encoder encoder = new BASE64Encoder();
        if (robotRoomConfig != null) {
            //{"guipai":1,"roomType":0,"maType":5,"FZB":0,"gameType":1,"zhama":8,"jushu":8,"wanfa":1,"clubMid":0,"playerCount":4}
            Map<String, Integer> cfg = JSON.parseObject(robotRoomConfig.getData(), Map.class);
            boolean b = sendCfgGDMajiangServer(socketUtils, amid, cfg);
            map.put(CommonContants.SUCCESS,b);
            map.put("wanfa", robotRoomConfig.getWanfa());
            map.put("roomName", robotRoomConfig.getRoomName());
            map.put("jushu", cfg.get("jushu"));
            map.put("t", roomType);
            map.put("a", amid);
            map.put("d", 1);
           /* String wf = robotRoomConfig.getWanfa();
            wf = wf.replaceAll(",", "_");
            String s = encoder.encode(wf.getBytes("utf-8"));
            Object o = cfg.get("jushu");
            map.put("wanfa", s);
            map.put("jushu", o);
            map.put("roomName", encoder.encode(robotRoomConfig.getRoomName().getBytes("utf-8")));*/
            return map;
        } else {
            RobotRoomCfgDf robotRoomCfgDf = robotRoomCfgDfService.queryRobotConfigByType(roomType);
            if (robotRoomCfgDf != null) {
                Map<String, Integer> cfg = JSON.parseObject(robotRoomCfgDf.getData(), Map.class);
                boolean b = sendCfgGDMajiangServer(socketUtils, amid, cfg);
                map.put(CommonContants.SUCCESS, b);
                map.put("wanfa", robotRoomCfgDf.getWanfa());
                map.put("roomName", robotRoomCfgDf.getRoomName());
                map.put("jushu", cfg.get("jushu"));
                map.put("t", roomType);
                map.put("a", amid);
                map.put("d", 2);
                /*String wf = robotRoomCfgDf.getWanfa();
                wf = wf.replaceAll(",", "_");
                String s = encoder.encode(wf.getBytes("utf-8"));
                Object o = cfg.get("jushu");
                map.put("wanfa", s);
                map.put("jushu", o);
                map.put("roomName", encoder.encode(robotRoomCfgDf.getRoomName().getBytes("utf-8")));*/
                return map;
            } else {
                log.debug("-------::agent no had cfg robot room type cfg ,and default cfg is no exist!");
                map.put(CommonContants.SUCCESS, false);
                return map;
            }
        }
    }

    private static boolean sendCfgGDMajiangServer(SocketUtils socketUtils,int amid,Map<String, Integer> cfg) {
        boolean joinRoom = socketUtils.setCmd(1102)
                .setStrParam(cfg.get("gameType") + "")//1 房间类型
                .setIntParam(cfg.get("jushu"))//2
                .setIntParam(3)//3 待开房
                .setIntParam(cfg.get("playerCount"))//4
                .setIntParam(cfg.get("guipai"))//5
                .setIntParam(cfg.get("wanfa"))//6
                .setIntParam(cfg.get("zhama"))//7
                .setIntParam(cfg.get("maType"))//8
                .setIntParam(amid)//9//俱乐部id
                .setIntParam(cfg.get("FZB"))//10
                .build().writeToServer();
        return joinRoom;
    }


}
