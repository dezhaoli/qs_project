package com.qs.webside.member.service.impl;

import com.qs.common.constant.CommonContants;
import com.qs.common.util.SocketUtils;
import com.qs.webside.member.model.Memberfides;
import com.qs.webside.member.model.Members;
import com.qs.webside.member.service.IShareLinkService;
import com.qs.webside.member.service.MemberService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zun.wei on 2017/7/21 11:16.
 * Description:
 */
@Service
public class ShareLinkServiceImpl implements IShareLinkService {

    Logger log = Logger.getLogger(ShareLinkServiceImpl.class);

    @Resource
    private MemberService memberService;


    @Override
    public String joinRoom(int roomid, String unionid, Model model, int gp, String sesskey, String cIp, int cPort, int gameType) throws IOException {
        Members members = memberService.findMembersBySitemid(unionid);
        Map<String, Object> map = new HashMap<>();
        //members = new Members();
        //members.setMid(54118);
        if (members != null) {
            Memberfides memberfides = memberService.findMemberfidesById(members.getMid());
            if (memberfides != null) {//用户存在
                getReadyJoin(roomid, gp, sesskey, cIp, cPort, memberfides, map, gameType);
            } else {//用户不存在，查不到数据
                log.debug("no checkout on db ;please confirm memberfides0 table ----------::");
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.ERROR, -2);//用户不存在
            }
        } else {//用户不存在，未注册过
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.ERROR, -1);//用户未注册
        }
        model.addAttribute("roomid", roomid);
        model.addAttribute("joinRoomResult", map);
        return "/web/share/joinRoom";
    }

    private void getReadyJoin(int roomid, int gp, String sesskey, String cIp, int cPort, Memberfides memberfides, Map<String, Object> map, int gameType) throws IOException {
        int mid = memberfides.getMid();
        SocketUtils socketUtils = switchSocketUtilByGameType(gameType, gp, sesskey, cIp, cPort, mid);
        boolean login = socketUtils.writeToServer();//登录c++ 服务器
        log.debug("user mid is " + mid + " join room " + roomid + ";" + "socket connect and write to c++ server result ----------::" + login);
        if (login && socketUtils.receviveInteger() == 0) {//服务器返回0表示登录成功
            boolean joinRoom = switchJoinRoomTypeByGameType(gameType, roomid, mid, socketUtils);
            if (joinRoom) {//发送请求到c++服务器成功
                executeJoinRoom(roomid, mid, socketUtils,map);
            } else {
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.ERROR, -3);//发送加入房间请求失败
                log.debug("user mid is " + mid + " join room " + roomid + ";send join room request fail.");
            }
        } else {
            map.put(CommonContants.SUCCESS, Boolean.FALSE);
            map.put(CommonContants.ERROR, -4);//发送登录请求失败
            log.debug("share link login fail ---------:: please confirm parameters !");
        }
        socketUtils.close();
    }


    private void executeJoinRoom(int roomid, int mid, SocketUtils socketUtils, Map<String, Object> map) throws IOException {
        long t1 = System.currentTimeMillis();
        while (true) {
            long t2 = System.currentTimeMillis();
            if (t2 - t1 > 5000) {//五秒钟循环如果未加入成功则跳出循环
                map.put(CommonContants.SUCCESS, Boolean.FALSE);
                map.put(CommonContants.ERROR, -5);//c++服务器返回加入房间超时
                log.debug("user mid is " + mid + " join room " + roomid + ";"
                        + "join room is timeout,the time equal -------::" + 5000);
                break;
            } else {
                boolean joinResult = socketUtils.recviceJoinRoomResult();
                if (joinResult) {
                    map.put(CommonContants.SUCCESS, Boolean.TRUE);
                    map.put(CommonContants.ERROR, 1);//成功加入房间
                    log.debug("user mid is " + mid + " join room " + roomid + " successful !");
                    break;//加入房间成功跳出循环
                }
            }
        }
    }

    private SocketUtils switchSocketUtilByGameType(int gameType, int gp, String sesskey, String cIp, int cPort, int mid) {
        switch (gameType) {
            case 6:
                return createMajiangSocketUtils(gp, sesskey, cIp, cPort, mid);
            default:
                return createMajiangSocketUtils(gp, sesskey, cIp, cPort, mid);
        }
    }

    private boolean switchJoinRoomTypeByGameType(int gameType, int roomid, int mid, SocketUtils socketUtils) {
        switch (gameType) {
            case 6:
                return joinMajiangRoom(roomid, mid, socketUtils);
            default:
                return joinMajiangRoom(roomid, mid, socketUtils);
        }
    }

    private boolean joinMajiangRoom(int roomid, int mid, SocketUtils socketUtils) {
        log.debug("user mid is " + mid + " join room " + roomid + ";" + "socket connect c++ server successful; ");
        boolean joinRoom = socketUtils.setCmd(1103).setIntParam(roomid).build().writeToServer();
        log.debug("user mid is " + mid + " join room " + roomid + ";" + "send join room request to c++ server,the server response result is -----------::" + joinRoom);
        return joinRoom;
    }

    private SocketUtils createMajiangSocketUtils(int gp, String sesskey, String cIp, int cPort, int mid) {
        return new SocketUtils().createSocket(cIp, cPort).setCmd(1000).fromUserMid(mid).setStrParam(sesskey).setIntParam(gp).setIntParam(2400).build();
    }

}
