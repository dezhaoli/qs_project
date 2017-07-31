package com.qs.webside.game.service.impl;

import com.qs.common.util.SocketUtils;
import org.apache.log4j.Logger;

/**
 * Created by zun.wei on 2017/7/28 10:20.
 * Description:切换分享链接进入房间，socket的构建。
 */
public class ShareLinkSwitchSocket {


    /**
     * @Author:zun.wei , @Date:2017/7/24 15:05
     * @Description:根据游戏类型切换不同的SocketUtils 构造方法
     * @param gameType 游戏类型
     * @param gp gp
     * @param sesskey session key
     * @param cIp socket ip
     * @param cPort socket port
     * @param mid 请求加入房间的mid
     * @return
     */
    public static SocketUtils switchSocketUtilByGameType(int gameType, int gp, String sesskey, String cIp, int cPort, int mid) {
        switch (gameType) {
            case 6:
                return createMajiangSocketUtils(gp, sesskey, cIp, cPort, mid);
            default:
                return createMajiangSocketUtils(gp, sesskey, cIp, cPort, mid);
        }
    }

    /**
     * @Author:zun.wei , @Date:2017/7/24 15:09
     * @Description:构造广东麻将SocketUtils的登录命令和端口
     * @param gp gp
     * @param sesskey session key
     * @param cIp socket ip
     * @param cPort socket port
     * @param mid 请求加入房间的mid
     * @return
     */
    private static SocketUtils createMajiangSocketUtils(int gp, String sesskey, String cIp, int cPort, int mid) {
        return new SocketUtils().createSocket(cIp, cPort).setCmd(1000).fromUserMid(mid).setStrParam(sesskey).setIntParam(gp).setIntParam(2400).build();
    }






    /**
     * @Author:zun.wei , @Date:2017/7/24 15:07
     * @Description:根据游戏类型切换构造不同的加入房间命令
     * @param gameType 游戏类型
     * @param roomid 房间号
     * @param mid 请求加入房间的mid
     * @param socketUtils SocketUtils
     * @return
     */
    public static boolean switchJoinRoomTypeByGameType(int gameType, int roomid, int mid, SocketUtils socketUtils) {
        switch (gameType) {
            case 6:
                return joinMajiangRoom(roomid, mid, socketUtils);
            default:
                return joinMajiangRoom(roomid, mid, socketUtils);
        }
    }

    /**
     * @Author:zun.wei , @Date:2017/7/24 15:08
     * @Description:加入广东麻将房间命令请求
     * @param roomid 房间号
     * @param mid 请求加入房间的mid
     * @param socketUtils SocketUtils
     * @return
     */
    private static boolean joinMajiangRoom(int roomid, int mid, SocketUtils socketUtils) {
        Logger log = Logger.getLogger(ShareLinkSwitchSocket.class);
        log.debug("user mid is " + mid + " join room " + roomid + ";" + "socket connect c++ server successful; ");
        boolean joinRoom = socketUtils.setCmd(1103).setIntParam(roomid).build().writeToServer();
        log.debug("user mid is " + mid + " join room " + roomid + ";" + "send join room request to c++ server,the server response result is -----------::" + joinRoom);
        return joinRoom;
    }

}
