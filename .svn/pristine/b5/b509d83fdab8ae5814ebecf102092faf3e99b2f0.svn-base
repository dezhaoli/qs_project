package com.qs.webside.robot.service.impl;

import com.qs.common.util.SocketUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

/**
 * Created by zun.wei on 2017/8/22 17:10.
 * Description:根据游戏类型解散房间
 */
public class RobotDiscoByGameType {


    //解散房间
    public static int dissolutionExistRoom(SocketUtils socketUtils, int gameType) throws IOException {
        long t1 = System.currentTimeMillis();
        //返回值：1为真，其他为假.
        switch (gameType) {
            case 6:
                return dissolutionGDMajiang(socketUtils, t1);
            default:
        }
        return 1;
    }

    //解散广东麻将房间
    private static int dissolutionGDMajiang(SocketUtils socketUtils,long starTime) throws IOException {
        while (true) {
            String data = socketUtils.receviveInteger1(1100,1001);//1100第一次登录，1001进入房间
            if (data != null) {//需要先把之前创建的房间解散
                String[] datas = data.split("_");
                if (Integer.parseInt(datas[2]) == 1100) {//第一次登录
                    return 1;
                }
                if (Integer.parseInt(datas[2]) == 1001) {//创建房间之后再登录
                    boolean dissRoom = socketUtils.setCmd(1008).build().writeToServer();
                    if (dissRoom) {
                        String dissResult = socketUtils.receviveInteger1(1008);
                        if (StringUtils.isNotBlank(dissResult)) {
                            String[] drs = dissResult.split("_");
                            if (Integer.parseInt(drs[2]) == 1008) {
                                return Integer.parseInt(drs[1]);
                            }
                        }
                    }
                }
            }
            long t2 = System.currentTimeMillis();
            if (t2 - starTime > 3000) {//五秒钟循环如果未加入成功则跳出循环
                return 0;
            }
        }
    }
}
